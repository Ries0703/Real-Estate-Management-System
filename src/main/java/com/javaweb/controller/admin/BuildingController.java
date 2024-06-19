package com.javaweb.controller.admin;


import com.javaweb.enums.DistrictCode;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IBuildingService buildingService;

    @GetMapping(value = "/admin/building-list")
    public ModelAndView buildingList(@ModelAttribute("buildingSearchRequest") BuildingSearchRequest buildingSearchRequest,
                                     @RequestParam(value = "page", defaultValue = "1") Integer page,
                                     @RequestParam(value = "limit", defaultValue = "2") Integer limit) {
        buildingSearchRequest.setPage(page);
        buildingSearchRequest.setMaxPageItems(limit);
        buildingSearchRequest.setTotalItems(buildingService.getBuildingCount(buildingSearchRequest));

        return new ModelAndView("admin/building/list")
                .addObject("buildingList", buildingService.findAll(buildingSearchRequest, PageRequest.of(page - 1, limit)))
                .addObject("staffs", userService.getStaffs())
                .addObject("districtCodes", DistrictCode.districtMap())
                .addObject("typeCodes", TypeCode.typeCodeMap())
                .addObject("currentUserRoles", SecurityUtils.getPrincipal().getRoles());
    }

    @GetMapping(value = "/admin/building-edit")
    public ModelAndView addBuilding(@ModelAttribute(value = "buildingEdit") BuildingDTO buildingDTO) {
        return new ModelAndView("admin/building/edit")
                .addObject("buildingEdit", buildingDTO)
                .addObject("districtCodes", DistrictCode.districtMap())
                .addObject("typeCodes", TypeCode.typeCodeMap());
    }

    @GetMapping(value = "/admin/building-edit-{id}")
    public ModelAndView editBuilding(@PathVariable(value = "id") Long id) {
        return new ModelAndView("admin/building/edit")
                .addObject("buildingEdit", buildingService.findById(id))
                .addObject("districtCodes", DistrictCode.districtMap())
                .addObject("typeCodes", TypeCode.typeCodeMap())
                .addObject("currentUserRoles", SecurityUtils.getPrincipal().getRoles());
    }
}
