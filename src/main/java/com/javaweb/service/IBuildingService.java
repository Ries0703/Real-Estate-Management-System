package com.javaweb.service;




import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IBuildingService {
	List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest, Pageable pageable);
	BuildingDTO findById(Long id);
	void addOrEditBuilding(BuildingDTO buildingDTO);
	void removeBuilding(List<Long> id);
	int getBuildingCount(BuildingSearchRequest buildingSearchRequest);
}
