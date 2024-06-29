package com.javaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class AssignmentBuildingDTO {
    @NotBlank(message = "must have building id to assign staff to building")
    private Long buildingId;

    private List<Long> staffIds;
}
