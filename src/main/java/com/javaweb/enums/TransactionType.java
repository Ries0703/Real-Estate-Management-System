package com.javaweb.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public enum TransactionType {
    CSKH("Chăm sóc khách hàng"),
    DDX("Dẫn đi xem");

    private final String transactionTypeName;

    TransactionType(String transactionTypeName) {
        this.transactionTypeName = transactionTypeName;
    }

    public static Map<String, String> transactionTypeMap() {
        return Arrays.stream(TransactionType.values())
                .collect(Collectors.toMap(TransactionType::toString, TransactionType::getTransactionTypeName,
                        (oldItem, newItem) -> oldItem, LinkedHashMap::new));

    }
}
