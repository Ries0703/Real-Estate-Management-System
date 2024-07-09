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
import com.javaweb.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
public class CustomerController {

    @Autowired
    private IUserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping(value = "/admin/customer-list")
    public ModelAndView customerList(@ModelAttribute("customerSearchRequest") CustomerSearchRequest customerSearchRequest,
                                     @RequestParam(value = "page", defaultValue = "1") Integer page,
                                     @RequestParam(value = "limit", defaultValue = "2") Integer limit) {

        Map<Long, String> staffs = new TreeMap<>();

        if (SecurityUtils.getAuthorities().contains("ROLE_MANAGER")) {
            staffs = userService.getStaffs();
        } else {
            staffs.put(SecurityUtils.getPrincipal().getId(), SecurityUtils.getPrincipal().getFullName());
            customerSearchRequest.setStaffId(SecurityUtils.getPrincipal().getId());
        }

        customerSearchRequest.setPage(page);
        customerSearchRequest.setMaxPageItems(limit);
        customerSearchRequest.setTotalItems(customerService.getCustomerCountByParam(customerSearchRequest));

        List<CustomerSearchResponse> customerSearchResponses = customerService.getCustomerByParams(customerSearchRequest, PageRequest.of(page - 1, limit));
        return new ModelAndView("admin/customer/list").addObject("customerList", customerSearchResponses)
                .addObject("staffs", staffs)
                .addObject("currentUserRoles", SecurityUtils.getPrincipal().getRoles())
                .addObject("statusMap", Status.statusMap());
    }

    @GetMapping(value = "/admin/customer-edit")
    public ModelAndView addCustomer(@ModelAttribute("customerEdit") CustomerDTO customerDTO) {
        return new ModelAndView("/admin/customer/edit")
                .addObject("customerEdit", customerDTO)
                .addObject("statusMap", Status.statusMap());
    }

    @GetMapping(value = "/admin/customer-edit-{id}")
    public ModelAndView editCustomer(@PathVariable("id") Long id, HttpServletRequest request) {

        CustomerDTO customerDTO = (CustomerDTO) request.getAttribute("customer");
        List<TransactionDTO> transactionCSKH = transactionService.getTransactionsByCodeAndCustomerId(TransactionType.CSKH, id);
        List<TransactionDTO> transactionDDX = transactionService.getTransactionsByCodeAndCustomerId(TransactionType.DDX, id);

        return new ModelAndView("/admin/customer/edit")
                .addObject("customerEdit", customerDTO)
                .addObject("statusMap", Status.statusMap())
                .addObject("transactionCSKH", transactionCSKH)
                .addObject("transactionDDX", transactionDDX)
                .addObject("transactionMap", TransactionType.transactionTypeMap());
    }
}
