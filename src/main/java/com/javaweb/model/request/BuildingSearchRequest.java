package com.javaweb.model.request;

import com.javaweb.enums.DistrictCode;
import com.javaweb.enums.TypeCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BuildingSearchRequest {
    private String name;
    private String street;
    private String ward;
    private DistrictCode district;
    private Long numberOfBasement;
    private Long floorArea;
    private String direction;
    private Long level;
    private Long areaFrom;
    private Long areaTo;
    private Long rentPriceFrom;
    private Long rentPriceTo;
    private String managerName;
    private String managerPhone;
    private Long staffId;
    private List<TypeCode> typeCode;
    private int maxPageItems = 4;
    private int page = 1;
    private int totalItems = 0;
    public int getTotalPages() {
        return (int) Math.ceil((double) this.getTotalItems() / this.getMaxPageItems());
    }
}
