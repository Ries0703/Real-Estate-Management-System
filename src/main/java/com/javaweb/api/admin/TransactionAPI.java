package com.javaweb.api.admin;

import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/customers/transactions")
public class TransactionAPI {

    @Autowired
    private TransactionService transactionService;

    @PutMapping
    public ResponseEntity<ResponseDTO> addTransaction(@RequestBody TransactionDTO transactionDTO) {
        transactionService.addOrEditTransaction(transactionDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Transaction added successfully");
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> editTransaction(@RequestBody TransactionDTO transactionDTO) {
        transactionService.addOrEditTransaction(transactionDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Transaction edit successfully");
        return ResponseEntity.ok(responseDTO);
    }
}
