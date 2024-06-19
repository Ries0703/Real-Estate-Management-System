package com.javaweb.repository.custom;

import com.javaweb.repository.entity.RoleEntity;


public interface RoleRepositoryCustom {
    RoleEntity findOneByCode(String code);
}
