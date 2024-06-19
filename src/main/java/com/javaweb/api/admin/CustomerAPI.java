package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.impl.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/customers")
public class CustomerAPI {

    private final UserService userService;

    public CustomerAPI(UserService userService) {
        this.userService = userService;
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> addCustomer(@RequestBody CustomerDTO customerDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Success");
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> editCustomer(@RequestBody CustomerDTO customerDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Success");
        return ResponseEntity.ok(responseDTO);
    }


    @DeleteMapping(value = "/{ids}")
    public ResponseEntity<ResponseDTO> deleteCustomer(@PathVariable("ids") List<Long> ids) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Success");
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}/staffs")
    public ResponseDTO getAssignedStaffs(@PathVariable Long id) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Success");
        responseDTO.setData(userService.getAssignedStaffBuilding(id));
        return responseDTO;
    }

    @PutMapping("staffs")
    public ResponseEntity<ResponseDTO> assignStaffsToCustomer(@RequestBody AssignmentCustomerDTO assignmentCustomerDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Success");
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("transactions")
    public ResponseEntity<ResponseDTO> assignTransactionsToCustomer(@RequestBody AssignmentCustomerDTO assignmentCustomerDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Success");
        return ResponseEntity.ok(responseDTO);
    }
}
