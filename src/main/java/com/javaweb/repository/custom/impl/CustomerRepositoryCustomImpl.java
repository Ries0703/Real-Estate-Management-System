package com.javaweb.repository.custom.impl;

import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import com.javaweb.repository.entity.CustomerEntity;
import com.javaweb.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepositoryCustomImpl implements CustomerRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<CustomerEntity> findCustomerByParams(CustomerSearchRequest customerSearchRequest, Pageable pageable) {
        StringBuilder queryString = queryBuilder(customerSearchRequest);
        queryString.append(" LIMIT ").append(pageable.getPageSize())
                .append(" OFFSET ").append(pageable.getOffset());
        Query query = entityManager.createNativeQuery(queryString.toString(), CustomerEntity.class);
        return query.getResultList();
    }

    @Override
    public Optional<CustomerEntity> findCustomerByIdAndAssignedStaff(Long customerId, Long staffId) {
        String queryString = String.format("SELECT * FROM customer cus JOIN assignmentcustomer ascm ON cus.id = ascm.customerid WHERE cus.id = %s AND cus.is_active = 1 AND ascm.staffid = %s", customerId, staffId);
        Query query = entityManager.createNativeQuery(queryString, CustomerEntity.class);
        try {
            CustomerEntity customer = (CustomerEntity) query.getSingleResult();
            return Optional.of(customer);
        } catch (NoResultException nre) {
            return Optional.empty();
        }
    }

    @Override
    public int getCustomerCount(CustomerSearchRequest customerSearchRequest) {
        String queryString = "SELECT COUNT(*) FROM (" + queryBuilder(customerSearchRequest) + ") AS total";
        Query query = entityManager.createNativeQuery(queryString);
        Number count = (Number) query.getSingleResult();
        return count.intValue();
    }


    private StringBuilder queryBuilder(CustomerSearchRequest customerSearchRequest) {
        Field[] fields = customerSearchRequest.getClass().getDeclaredFields();
        StringBuilder queryBuilder = new StringBuilder("SELECT DISTINCT cus.* FROM customer cus ");
        if (!StringUtil.isEmpty(customerSearchRequest.getStaffId())) {
            queryBuilder.append(" JOIN assignmentcustomer ascm ON cus.id = ascm.customerid ");
        }
        queryBuilder.append("  WHERE cus.is_active = 1 ");
        for (Field field : fields) {

            try {
                field.setAccessible(true);
                String key = field.getName();
                Object value = field.get(customerSearchRequest);
                if (StringUtil.isEmpty(value)) {
                    continue;
                }
                if (key.equals("staffId")) {
                    queryBuilder.append(" AND ascm.staffid = ").append(value);
                } else {
                    queryBuilder.append(" AND cus.").append(key).append(" LIKE '%").append(value).append("%' ");
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return queryBuilder;
    }
}
