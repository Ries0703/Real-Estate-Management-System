package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.CustomerService;
import com.javaweb.service.IAssignmentService;
import com.javaweb.service.IUserService;
import com.javaweb.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/customers")
public class CustomerAPI {

    @Autowired
    private IUserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private IAssignmentService assignmentService;

    @PutMapping
    public ResponseEntity<ResponseDTO> addCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.addOrEditCustomer(customerDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Add customer successfully");
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> editCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.addOrEditCustomer(customerDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Edit customer successfully");
        return ResponseEntity.ok(responseDTO);
    }


    @PostMapping(value = "/{ids}")
    public ResponseEntity<ResponseDTO> deleteCustomer(@PathVariable("ids") List<Long> ids) {
        customerService.softDeleteCustomer(ids);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Delete Customer successfully");
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}/staffs")
    public ResponseDTO getAssignedStaffs(@PathVariable Long id) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(userService.getAssignedStaffCustomer(id));
        return responseDTO;
    }

    @PutMapping("/staffs")
    public ResponseEntity<ResponseDTO> assignStaffsToCustomer(@RequestBody AssignmentCustomerDTO assignmentCustomerDTO) {
        assignmentService.assignStaffsToCustomer(assignmentCustomerDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Assign Customer Successfully");
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/transactions")
    public ResponseEntity<ResponseDTO> addTransaction(@RequestBody TransactionDTO transactionDTO) {
        transactionService.addOrEditTransaction(transactionDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Transaction added successfully");
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/transactions")
    public ResponseEntity<ResponseDTO> editTransaction(@RequestBody TransactionDTO transactionDTO) {
        transactionService.addOrEditTransaction(transactionDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Transaction edit successfully");
        return ResponseEntity.ok(responseDTO);
    }
}
