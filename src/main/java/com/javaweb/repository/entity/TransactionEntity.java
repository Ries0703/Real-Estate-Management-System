package com.javaweb.repository.entity;

import com.javaweb.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
@Getter
@Setter
public class TransactionEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "code")
    private TransactionType code;

    @Column(name = "note")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerid", nullable = false)
    private CustomerEntity customerEntity;
}
