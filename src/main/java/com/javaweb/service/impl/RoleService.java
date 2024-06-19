package com.javaweb.service.impl;

import com.javaweb.converter.RoleConverter;
import com.javaweb.model.dto.RoleDTO;
import com.javaweb.repository.RoleRepository;
import com.javaweb.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleService implements IRoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleConverter roleConverter;

	@Override
	public Map<String, String> getRoles() {
		return roleRepository.findAll().stream()
				.map(roleConverter::convertToDto)
				.collect(Collectors.toMap(RoleDTO::getCode, RoleDTO::getName));
	}
}
