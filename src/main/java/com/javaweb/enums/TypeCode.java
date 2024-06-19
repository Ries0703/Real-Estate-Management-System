package com.javaweb.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public enum TypeCode {
    NOI_THAT("Nội thất"),
    TANG_TRET("Tầng trệt"),
    NGUYEN_CAN("Nguyên căn"),
    ;
    private final String typeCodeName;
    TypeCode(String typeCodeName) {
        this.typeCodeName = typeCodeName;
    }
    public static Map<String, String> typeCodeMap() {
        return Arrays.stream(TypeCode.values())
                .collect(Collectors.toMap(TypeCode::toString, TypeCode::getTypeCodeName,
                        (oldItem, newItem) -> oldItem, LinkedHashMap::new));
    }
}
