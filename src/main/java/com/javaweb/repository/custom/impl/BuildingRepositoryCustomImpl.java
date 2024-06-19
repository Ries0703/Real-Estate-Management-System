package com.javaweb.repository.custom.impl;


import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.utils.StringUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

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
                    where.append(" AND b.").append(key).append(" LIKE '%").append(value.toString().trim()).append("%' ");
                } else if (EQUAL_FIELDS.contains(key)) {
                    where.append(" AND b.").append(key).append(" = ").append(value);
                } else if (STAFF_ID_FIELD.equals(key)) {
                    where.append(" AND asb.staffid = ").append(value);
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    private void sqlWhereComplex(BuildingSearchRequest buildingSearch, StringBuilder where) {
        if (!StringUtil.isEmpty(buildingSearch.getRentPriceFrom())) {
            where.append(" AND b.rentprice >= ").append(buildingSearch.getRentPriceFrom());
        }

        if (!StringUtil.isEmpty(buildingSearch.getRentPriceTo())) {
            where.append(" AND b.rentprice <= ").append(buildingSearch.getRentPriceTo());
        }

        if (!StringUtil.isEmpty(buildingSearch.getAreaFrom())) {
            where.append(" AND ra.value >= ").append(buildingSearch.getAreaFrom());
        }

        if (!StringUtil.isEmpty(buildingSearch.getAreaTo())) {
            where.append(" AND ra.value <= ").append(buildingSearch.getAreaTo());
        }
        List<TypeCode> typeCode = buildingSearch.getTypeCode();
        if (StringUtil.usableTypeCode(typeCode)) {
            where.append(" AND (b.type LIKE '%").append(buildingSearch.getTypeCode().get(0)).append("%'");
            for (int i = 1; i < typeCode.size(); i++) {
                where.append(" OR b.type LIKE '%").append(typeCode.get(i)).append("%' ");
            }
            where.append(")");
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
}
