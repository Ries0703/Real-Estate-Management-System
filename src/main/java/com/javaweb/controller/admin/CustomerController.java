package com.javaweb.controller.admin;

import com.javaweb.enums.Status;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return new ModelAndView("/admin/customer/edit")
                .addObject("customerEdit", customerDTO)
                .addObject("statusMap", Status.statusMap())
                .addObject("currentUserRoles", SecurityUtils.getPrincipal().getRoles());
    }

    @GetMapping(value = "/admin/customer-list")
    public ModelAndView customerList(@ModelAttribute("customerSearchRequest") CustomerSearchRequest customerSearchRequest,
                                     @RequestParam(value = "page", defaultValue = "1") Integer page,
                                     @RequestParam(value = "limit", defaultValue = "2") Integer limit) {
        ModelAndView view = new ModelAndView("admin/customer/list");
        List<CustomerSearchResponse> mockCustomerEntityList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CustomerSearchResponse customer = new CustomerSearchResponse();
            customer.setId((long) i);
            customer.setFullName("Full Name " + i);
            customer.setPhone("1234567890" + i);
            customer.setEmail("email" + i + "@example.com");
            customer.setDemand("Demand " + i);
            customer.setCreatedBy("Staff " + i);
            customer.setCreatedDate(new Date());
            customer.setStatus(Status.DA_XU_LY);
            mockCustomerEntityList.add(customer);
        }
        return view.addObject("customerList", mockCustomerEntityList)
                .addObject("staffs", userService.getStaffs())
                .addObject("currentUserRoles", SecurityUtils.getPrincipal().getRoles())
                .addObject("statusMap", Status.statusMap())
                ;

    }


}
