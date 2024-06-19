package com.javaweb.service.impl;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.utils.StringUtil;
import com.javaweb.utils.UploadFileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements IBuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingConverter buildingConverter;

    @Override
    public int getBuildingCount(BuildingSearchRequest buildingSearchRequest) {
        return buildingRepository.count(buildingSearchRequest);
    }

    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest, Pageable pageable) {
        return buildingRepository.findAll(buildingSearchRequest, pageable).stream().map(buildingConverter::entityToResponse).collect(Collectors.toList());
    }

    @Override
    public BuildingDTO findById(Long id) {
        Optional<BuildingEntity> buildingEntity = buildingRepository.findById(id);
        if (!buildingEntity.isPresent()) {
            return new BuildingDTO();
        }
        return buildingConverter.entityToDto(buildingEntity.get());
    }

    @Override
    @Transactional
    public void addOrEditBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingConverter.dtoToEntity(buildingDTO);

        boolean isEditBuilding = !StringUtil.isEmpty(buildingDTO.getId());
        if (isEditBuilding) {
            BuildingEntity oldBuilding = buildingRepository.findById(buildingDTO.getId())
                    .orElseThrow(() -> new RuntimeException("no Building with id = " + buildingDTO.getId()));
            buildingEntity.setImage(oldBuilding.getImage());
            buildingEntity.setAssignedStaffs(oldBuilding.getAssignedStaffs());
        }

        saveThumbnail(buildingDTO, buildingEntity);
        buildingRepository.save(buildingEntity);
    }

    private void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        String path = "/building/" + buildingDTO.getImageName();
        if (null != buildingDTO.getImageBase64()) {
            if (null != buildingEntity.getImage()) {
                if (!path.equals(buildingEntity.getImage())) {
                    File file = new File(System.getProperty("catalina.base") + "/webapps" + buildingEntity.getImage());
                    file.delete();
                }
            }
            byte[] bytes = Base64.decodeBase64(buildingDTO.getImageBase64().getBytes());
            UploadFileUtils.writeOrUpdate(path, bytes);
            buildingEntity.setImage(path);
        }
    }

    @Override
    @Transactional
    public void removeBuilding(List<Long> ids) {
        buildingRepository.deleteByIdIn(ids);
    }
}