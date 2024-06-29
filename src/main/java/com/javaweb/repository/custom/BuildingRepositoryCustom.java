package com.javaweb.repository.custom;



import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BuildingRepositoryCustom {
	List<BuildingEntity> findAll(BuildingSearchRequest buildingSearch, Pageable pageable);
	Optional<BuildingEntity> findByIdAndAssignedStaff(Long buildingId, Long assignedStaffId);
	int count(BuildingSearchRequest buildingSearchRequest);
}
