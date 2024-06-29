package com.javaweb.repository;

import com.javaweb.repository.custom.CustomerRepositoryCustom;
import com.javaweb.repository.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>, CustomerRepositoryCustom {

    @Modifying
    @Query(value = "update customer set is_active = 0 where id IN :ids", nativeQuery = true)
    void deactivateCustomers(List<Long> ids);
}
