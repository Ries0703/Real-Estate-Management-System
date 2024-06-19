package com.javaweb.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Table(name = "rentarea")
@Entity
public class RentAreaEntity extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "buildingid")
	private BuildingEntity buildingEntity;

	@Column(name = "value")
	private Integer value;
}
