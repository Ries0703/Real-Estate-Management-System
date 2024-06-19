package com.javaweb.repository.entity;

import com.javaweb.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class CustomerEntity extends BaseEntity {

    @Column(name = "fullname", nullable = false)
    private String fullName;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "companyname")
    private String companyName;

    @Column(name = "demand")
    private String demand;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @OneToMany(mappedBy = "customerEntity")
    private List<TransactionEntity> transactionEntityList;

    @ManyToMany(mappedBy = "customerEntityList")
    private List<UserEntity> staffList;
}
