package com.javaweb.converter;

import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.enums.DistrictCode;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.utils.StringUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RentAreaConverter rentAreaConverter;

	public BuildingDTO entityToDto(BuildingEntity building) {
		List<TypeCode> typeCode = Arrays.stream(
				building.getType().split("[ ,]+"))
				.map(TypeCode::valueOf)
				.collect(Collectors.toList()
				);
		String rentArea = building.getRentAreaEntities().stream().map(area -> area.getValue().toString()).collect(Collectors.joining(", "));
		BuildingDTO buildingDTO = modelMapper.map(building, BuildingDTO.class);

		buildingDTO.setTypeCode(typeCode);
		buildingDTO.setRentArea(rentArea);
		return buildingDTO;
	}


	public BuildingSearchResponse entityToResponse(BuildingEntity building) {
		String address = String.join(", ",
				building.getStreet(),
				building.getWard(),
				DistrictCode.valueOf(building.getDistrictCode()).getDistrictName());
		String rentArea = building.getRentAreaEntities()
		    .stream()
		    .map(o -> o.getValue().toString())
		    .collect(Collectors.joining(", "));
		BuildingSearchResponse dto = modelMapper.map(building, BuildingSearchResponse.class);
		dto.setAddress(address);
		dto.setRentArea(rentArea);
		return dto;
	}

	public BuildingEntity dtoToEntity(BuildingDTO buildingDTO) {
		BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);

		String type = buildingDTO.getTypeCode().stream()
				.map(TypeCode::toString)
				.collect(Collectors.joining(", "));

		List<String> rentAreas = Arrays.asList(buildingDTO.getRentArea().trim().split("[ ,]+"));

		buildingEntity.setType(type);
		buildingEntity.setDistrictCode(buildingDTO.getDistrict().toString());

        buildingEntity.setRentAreaEntities(
				rentAreas.stream()
							.filter(area -> !StringUtil.isEmpty(area))
							.map(area -> rentAreaConverter.stringToRentArea(area, buildingEntity))
							.collect(Collectors.toSet()));

		return buildingEntity;
	}
}
