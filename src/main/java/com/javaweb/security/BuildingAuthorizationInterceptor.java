package com.javaweb.security;

import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class BuildingAuthorizationInterceptor implements HandlerInterceptor {
    private static final Pattern BUILDING_EDIT_PATTERN = Pattern.compile("/admin/building-edit-(\\d+)");

    @Autowired
    private IBuildingService buildingService;

    private static Optional<Long> extractBuildingIdFromRequest(String requestURI) {
        Matcher matcher = BUILDING_EDIT_PATTERN.matcher(requestURI);
        if (matcher.find()) {
            return Optional.of(Long.parseLong(matcher.group(1)));
        }
        return Optional.empty();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Long buildingId = extractBuildingIdFromRequest(request.getRequestURI()).orElseThrow(() -> new RuntimeException("URI is wrong"));
        if (SecurityUtils.getAuthorities().contains("ROLE_MANAGER")) {
            request.setAttribute("building", buildingService.findById(buildingId));
            return true;
        }

        Long staffId = SecurityUtils.getPrincipal().getId();
        BuildingDTO buildingDTO = buildingService.findByIdAndAssignedStaff(buildingId, staffId);


        if (buildingDTO == null) {
            response.sendRedirect("/access-denied");
            return false;
        }
        request.setAttribute("building", buildingDTO);
        return true;
    }

}
