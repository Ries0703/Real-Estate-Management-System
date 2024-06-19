package com.javaweb.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public enum Status {
    CHUA_XU_LY("Chưa xử lý"),

    DANG_XU_LY("Đang xử lý"),

    DA_XU_LY("Đã xử lý");

    private final String statusName;

    Status(String statusName) {
        this.statusName = statusName;
    }

    public static Map<String, String> statusMap() {
        return Arrays.stream(Status.values())
                .collect(Collectors.toMap(Status::toString, Status::getStatusName,
                        (oldItem, newItem) -> oldItem, LinkedHashMap::new));

    }
}
