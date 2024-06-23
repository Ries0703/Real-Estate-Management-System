package com.javaweb.service.impl;

import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.repository.entity.CustomerEntity;
import com.javaweb.service.IAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;

@Service
public class AssignmentBuildingServiceImpl implements IAssignmentService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void assignStaffsToBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
        BuildingEntity buildingEntity = buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();
        buildingEntity.setAssignedStaffs(new HashSet<>(userRepository.findByIdIn(assignmentBuildingDTO.getStaffIds())));
        buildingRepository.save(buildingEntity);
    }

    @Override
    public void assignStaffsToCustomer(AssignmentCustomerDTO assignmentCustomerDTO) {
        CustomerEntity customerEntity = customerRepository.findById(assignmentCustomerDTO.getCustomerId()).get();
        customerEntity.setAssignedStaffs(userRepository.findByIdIn(assignmentCustomerDTO.getStaffIds()));
        customerRepository.save(customerEntity);
    }
}