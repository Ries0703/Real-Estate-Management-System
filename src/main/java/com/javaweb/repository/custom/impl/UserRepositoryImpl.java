package com.javaweb.repository.custom.impl;

import com.javaweb.repository.custom.UserRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public int countTotalItem() {
		String sql = buildQueryFilter();
		Query query = entityManager.createNativeQuery(sql);
		return query.getResultList().size();
	}

	private String buildQueryFilter() {
        return "SELECT * FROM user u WHERE u.status = 1";
	}
}
