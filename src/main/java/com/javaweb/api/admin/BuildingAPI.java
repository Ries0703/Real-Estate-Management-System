package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.IAssignmentBuildingService;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/buildings")
public class BuildingAPI {

    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private IAssignmentBuildingService assignmentBuildingService;

    @Autowired
    private IUserService userService;

    @PutMapping
    public ResponseEntity<String> addBuilding(@RequestBody BuildingDTO buildingDTO) {
        buildingService.addOrEditBuilding(buildingDTO);
        return ResponseEntity.ok("Building created");
    }

    @PostMapping
    public ResponseEntity<String> editBuilding(@RequestBody BuildingDTO building) {
        buildingService.addOrEditBuilding(building);
        return ResponseEntity.ok("Building edited");
    }

    @DeleteMapping(value = "/{ids}")
    public ResponseEntity<String> deleteBuilding(@PathVariable List<Long> ids) {
        buildingService.removeBuilding(ids);
        return ResponseEntity.ok("Deleted " + ids.size() + " buildings");
    }

    @GetMapping(value = "/{id}/staffs")
    public ResponseDTO getBuildingStaffAssignment(@PathVariable("id") Long buildingId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(userService.getAssignedStaff(buildingId));
        responseDTO.setMessage("succeeded");
        return responseDTO;
    }

    @PutMapping("/staffs")
    public ResponseEntity<String> assignStaffToBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO) {
        assignmentBuildingService.assignStaffsToBuilding(assignmentBuildingDTO);
        return ResponseEntity.ok("Staff assigned");
    }
}


