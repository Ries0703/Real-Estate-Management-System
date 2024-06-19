package com.javaweb.repository.custom.impl;

import com.javaweb.repository.entity.RoleEntity;
import com.javaweb.repository.custom.RoleRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Repository
public class RoleRepositoryImpl implements RoleRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public RoleEntity findOneByCode(String code) {
		String sql = "select * FROM role as r where r.code = '" + code + "'";
		Query query = entityManager.createNativeQuery(sql, RoleEntity.class);
		return (RoleEntity) query.getSingleResult();
	}
}
