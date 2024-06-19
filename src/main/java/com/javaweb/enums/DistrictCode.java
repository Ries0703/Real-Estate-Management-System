package com.javaweb.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public enum DistrictCode {
    QUAN_1("Quận 1"),
    QUAN_2("Quận 2"),
    QUAN_3("Quận 3"),
    QUAN_4("Quận 4"),
    QUAN_5("Quận 5"),
    QUAN_6("Quận 6"),
    QUAN_7("Quận 7"),
    QUAN_TB("Quận Tân Bình"),
    QUAN_10("Quận 10"),
    QUAN_11("Quận 11"),
    ;
    private final String districtName;

    DistrictCode(String districtName) {
        this.districtName = districtName;
    }

    public static Map<String, String> districtMap() {
        return Arrays.stream(DistrictCode.values())
                .collect(Collectors.toMap(DistrictCode::toString, DistrictCode::getDistrictName,
                        (oldItem, newItem) -> oldItem, LinkedHashMap::new));
    }
}

