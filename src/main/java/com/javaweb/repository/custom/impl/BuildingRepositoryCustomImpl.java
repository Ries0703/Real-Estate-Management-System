package com.javaweb.repository.custom.impl;


import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.utils.StringUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryCustomImpl implements BuildingRepositoryCustom {
    private static final List<String> LIKE_FIELDS = Arrays.asList("name", "ward", "street", "district", "level", "managerName", "managerPhone");
    private static final List<String> EQUAL_FIELDS = Arrays.asList("floorArea", "numberOfBasement", "direction");
    private static final String STAFF_ID_FIELD = "staffId";


    @PersistenceContext
    private EntityManager entityManager;



    @SuppressWarnings("unchecked")
    @Override
    public List<BuildingEntity> findAll(BuildingSearchRequest buildingSearch, Pageable pageable) {
        StringBuilder sql = searchQueryBuilder(buildingSearch);

        String limit = String.format(" LIMIT %s ", pageable.getPageSize());
        String offset = String.format(" OFFSET %s ", pageable.getOffset());
        sql.append(limit).append(offset);

        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    @Override
    public Optional<BuildingEntity> findByIdAndAssignedStaff(Long buildingId, Long assignedStaffId) {
        String queryString = String.format("SELECT * FROM building b JOIN assignmentbuilding asb ON b.id = asb.buildingid WHERE b.id = %s AND asb.staffid = %s", buildingId, assignedStaffId);
        Query query = entityManager.createNativeQuery(queryString, BuildingEntity.class);
        try {
            BuildingEntity buildingEntity = (BuildingEntity) query.getSingleResult();
            return Optional.of(buildingEntity);
        } catch (NoResultException nre) {
            return Optional.empty();
        }
    }

    @Override
    public int count(BuildingSearchRequest buildingSearchRequest) {
        StringBuilder sql = searchQueryBuilder(buildingSearchRequest);

        // Create a query to count the total number of records
        String countSql = "SELECT COUNT(*) FROM (" + sql + ") AS total";
        Query countQuery = entityManager.createNativeQuery(countSql);
        Number total = (Number) countQuery.getSingleResult();

        return total.intValue();
    }


    private void sqlWhereSimple(BuildingSearchRequest buildingSearch, StringBuilder where) {
        try {
            for (Field field : BuildingSearchRequest.class.getDeclaredFields()) {
                field.setAccessible(true);
                String key = field.getName();
                Object value = field.get(buildingSearch);
                if (StringUtil.isEmpty(value)) {
                    continue;
                }
                if (LIKE_FIELDS.contains(key)) {
                    where.append(String.format(" AND b.%s LIKE '%%%s%%' ", key, value.toString().trim()));
                } else if (EQUAL_FIELDS.contains(key)) {
                    where.append(String.format(" AND b.%s = %s", key, value));
                } else if (STAFF_ID_FIELD.equals(key)) {
                    where.append(String.format(" AND asb.staffid = %s", value));
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void sqlWhereComplex(BuildingSearchRequest buildingSearch, StringBuilder where) {
        if (!StringUtil.isEmpty(buildingSearch.getRentPriceFrom())) {
            where.append(String.format(" AND b.rentprice >= %s", buildingSearch.getRentPriceFrom()));
        }

        if (!StringUtil.isEmpty(buildingSearch.getRentPriceTo())) {
            where.append(String.format(" AND b.rentprice <= %s", buildingSearch.getRentPriceTo()));
        }

        if (!StringUtil.isEmpty(buildingSearch.getAreaFrom())) {
            where.append(String.format(" AND ra.value >= %s", buildingSearch.getAreaFrom()));
        }

        if (!StringUtil.isEmpty(buildingSearch.getAreaTo())) {
            where.append(String.format(" AND ra.value <= %s", buildingSearch.getAreaTo()));
        }

        List<TypeCode> typeCode = buildingSearch.getTypeCode();
        if (usableTypeCode(typeCode)) {
            String typeConditions = typeCode.stream()
                    .map(code -> String.format("b.type LIKE '%%%s%%'", code))
                    .collect(Collectors.joining(" OR ", " AND (", ")"));
            where.append(typeConditions);
        }
    }

    private void sqlJoin(BuildingSearchRequest buildingSearch, StringBuilder join) {
        if (!StringUtil.isEmpty(buildingSearch.getStaffId())) {
            join.append(" JOIN assignmentbuilding asb ON b.id = asb.buildingid");
        }
        if (!StringUtil.isEmpty(buildingSearch.getAreaFrom()) || !StringUtil.isEmpty(buildingSearch.getAreaTo())) {
            join.append(" JOIN rentarea ra ON b.id = ra.buildingid");
        }
    }

    private StringBuilder searchQueryBuilder(BuildingSearchRequest buildingSearch) {
        StringBuilder select = new StringBuilder("SELECT ");
        StringBuilder distinct = new StringBuilder();
        String columns = "  b.* FROM building b ";
        StringBuilder join = new StringBuilder();
        StringBuilder where = new StringBuilder(" WHERE 1 = 1");
        sqlWhereSimple(buildingSearch, where);
        sqlWhereComplex(buildingSearch, where);
        sqlJoin(buildingSearch, join);
        if (!StringUtil.isEmpty(join)) {
            distinct.append(" DISTINCT ");
        }
        return select.append(distinct).append(columns).append(join).append(where);
    }

    private boolean usableTypeCode(List<TypeCode> typeCodes) {
        if (typeCodes == null)
            return false;
        return typeCodes.stream().anyMatch(str -> !StringUtil.isEmpty(str));
    }
}
