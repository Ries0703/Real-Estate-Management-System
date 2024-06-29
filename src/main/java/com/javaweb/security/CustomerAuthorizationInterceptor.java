package com.javaweb.security;

import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CustomerAuthorizationInterceptor implements HandlerInterceptor {

    private static final Pattern CUSTOMER_EDIT_PATTERN = Pattern.compile("/admin/customer-edit-(\\d+)");

    @Autowired
    private CustomerService customerService;

    private static Optional<Long> extractCustomerIdFromRequest(String requestURI) {
        Matcher matcher = CUSTOMER_EDIT_PATTERN.matcher(requestURI);
        if (matcher.find()) {
            return Optional.of(Long.parseLong(matcher.group(1)));
        }
        return Optional.empty();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Long customerId = extractCustomerIdFromRequest(request.getRequestURI()).orElseThrow(() -> new RuntimeException("URI is wrong"));

        if (SecurityUtils.getAuthorities().contains("ROLE_MANAGER")) {
            request.setAttribute("customer", customerService.getCustomerById(customerId));
            return true;
        }

        Long staffId = SecurityUtils.getPrincipal().getId();
        CustomerDTO customerDTO = customerService.getCustomerByIdAndAssignedStaff(customerId, staffId);


        if (customerDTO == null) {
            response.sendRedirect("/access-denied");
            return false;
        }
        request.setAttribute("customer", customerDTO);
        return true;
    }

}
