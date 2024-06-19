package com.javaweb.converter;

import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.utils.NumberUtils;
import org.springframework.stereotype.Component;

@Component
public class RentAreaConverter {
    public RentAreaEntity stringToRentArea(String rentArea, BuildingEntity buildingEntity) {
        if (!NumberUtils.isLong(rentArea.trim())) {
            return null;
        }
        RentAreaEntity rentAreaEntity = new RentAreaEntity();
        rentAreaEntity.setValue(Integer.valueOf(rentArea.trim()));
        rentAreaEntity.setBuildingEntity(buildingEntity);
        return rentAreaEntity;
    }
}
