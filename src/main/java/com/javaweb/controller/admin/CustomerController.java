package com.javaweb.controller.admin;

import com.javaweb.enums.Status;
import com.javaweb.enums.TransactionType;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.CustomerService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private IUserService userService;

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/admin/customer-list")
    public ModelAndView customerList(@ModelAttribute("customerSearchRequest") CustomerSearchRequest customerSearchRequest,
                                     @RequestParam(value = "page", defaultValue = "1") Integer page,
                                     @RequestParam(value = "limit", defaultValue = "2") Integer limit) {

        customerSearchRequest.setPage(page);
        customerSearchRequest.setMaxPageItems(limit);
        customerSearchRequest.setTotalItems(customerService.getCustomerCountByParam(customerSearchRequest));

        List<CustomerSearchResponse> customerSearchResponses = customerService.getCustomerByParams(customerSearchRequest, PageRequest.of(page - 1, limit));
        return new ModelAndView("admin/customer/list").addObject("customerList", customerSearchResponses)
                .addObject("staffs", userService.getStaffs())
                .addObject("currentUserRoles", SecurityUtils.getPrincipal().getRoles())
                .addObject("statusMap", Status.statusMap());
    }

    @GetMapping(value = "/admin/customer-edit")
    public ModelAndView addCustomer(@ModelAttribute("customerEdit") CustomerDTO customerDTO) {
        return new ModelAndView("/admin/customer/edit")
                .addObject("customerEdit", customerDTO)
                .addObject("statusMap", Status.statusMap())
                .addObject("currentUserRoles", SecurityUtils.getPrincipal().getRoles());
    }


    @GetMapping(value = "/admin/customer-edit-{id}")
    public ModelAndView editCustomer(@PathVariable("id") Long id) {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(id);
        customerDTO.setCompanyName("company");
        customerDTO.setEmail("email");
        customerDTO.setPhone("phone");
        customerDTO.setFullName("full name");
        customerDTO.setStatus(Status.DA_XU_LY);

        List<TransactionDTO> transactionEntityList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.setId((long) i);
            transactionDTO.setCode(TransactionType.values()[i % 2]);
            transactionDTO.setNote("Note " + i);
            transactionDTO.setCreatedDate(new Date());
            transactionDTO.setCreatedBy("Creator " + i);
            transactionDTO.setModifiedDate(new Date());
            transactionDTO.setModifiedBy("Modifier " + i);
            transactionEntityList.add(transactionDTO);
        }
        List<TransactionDTO> transactionCSKH = new ArrayList<>();
        List<TransactionDTO> transactionDDX = new ArrayList<>();
        transactionEntityList.forEach(e -> {
            if (e.getCode().equals(TransactionType.CSKH)) {
                transactionCSKH.add(e);
            } else {
                transactionDDX.add(e);
            }
        });
        return new ModelAndView("/admin/customer/edit")
                .addObject("customerEdit", customerDTO)
                .addObject("statusMap", Status.statusMap())
                .addObject("transactionCSKH", transactionCSKH)
                .addObject("transactionDDX", transactionDDX)
                .addObject("transactionMap", TransactionType.transactionTypeMap())
                .addObject("currentUserRoles", SecurityUtils.getPrincipal().getRoles());
    }


}
