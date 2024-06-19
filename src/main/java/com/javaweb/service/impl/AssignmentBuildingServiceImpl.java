package com.javaweb.service.impl;

import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IAssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;

@Service
public class AssignmentBuildingServiceImpl implements IAssignmentBuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void assignStaffsToBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
        BuildingEntity buildingEntity = buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();
        buildingEntity.setAssignedStaffs(new HashSet<>(userRepository.findByIdIn(assignmentBuildingDTO.getStaffIds())));
        buildingRepository.save(buildingEntity);
    }
}