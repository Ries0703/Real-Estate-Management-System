package com.javaweb.model.dto;

import com.javaweb.enums.DistrictCode;
import com.javaweb.enums.TypeCode;
import lombok.Getter;
import lombok.Setter;


import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@Setter
@Getter
public class BuildingDTO extends AbstractDTO<BuildingDTO> {

    @NotBlank(message = "must enter building name")
    private String name;

    private String street;
    private String ward;

    @NotBlank(message = "must enter district code")
    private DistrictCode district;

    private Long numberOfBasement;
    private Long floorArea;
    private Long level;

    @NotEmpty(message = "must have at least 1 type code")
    private List<@Valid TypeCode> typeCode;

    private Long overtimeFee;
    private Long electricityFee;
    private Long deposit;
    private Long payment;
    private String rentTime;
    private String decorationTime;
    private String rentPriceDescription;
    private Long carFee;
    private Long motoFee;
    private String structure;
    private String direction;
    private String note;
    private String rentArea;
    private String managerName;
    private String managerPhone;

    @NotBlank(message = "must have rent price")
    private Long rentPrice;

    private Long serviceFee;
    private Double brokerageFee;
    private String image;
    private String imageBase64;
    private String imageName;

    public String getImageBase64() {
        if (imageBase64 != null) {
            return imageBase64.split(",")[1];
        }
        return null;
    }
}