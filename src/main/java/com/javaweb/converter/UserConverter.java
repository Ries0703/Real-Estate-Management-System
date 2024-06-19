package com.javaweb.converter;

import com.javaweb.model.dto.UserDTO;
import com.javaweb.repository.entity.UserEntity;
import com.javaweb.model.response.StaffResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO convertToDto (UserEntity entity){
        return modelMapper.map(entity, UserDTO.class);
    }

    public UserEntity convertToEntity (UserDTO dto){
        return modelMapper.map(dto, UserEntity.class);
    }

    public StaffResponseDTO toStaffResponseDTO(UserEntity user, String checked) {
        StaffResponseDTO staffResponseDTO = modelMapper.map(user, StaffResponseDTO.class);
        staffResponseDTO.setStaffId(user.getId());
        staffResponseDTO.setChecked(checked);
        return  staffResponseDTO;
    }
}
