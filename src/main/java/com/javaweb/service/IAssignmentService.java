package com.javaweb.service;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.AssignmentCustomerDTO;


public interface IAssignmentService {
    void assignStaffsToBuilding(AssignmentBuildingDTO assignmentBuildingDTO);
    void assignStaffsToCustomer(AssignmentCustomerDTO assignmentCustomerDTO);
}
