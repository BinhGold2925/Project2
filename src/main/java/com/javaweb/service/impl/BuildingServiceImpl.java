package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.model.BuildingDTO1;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService{
	@Autowired
	private BuildingRepository buildingRepository;
	@Override
	public List<BuildingDTO1> fineAll(String name) {
		List<BuildingEntity> buildingEntities = buildingRepository.fineAll(name);
		List<BuildingDTO1> result = new ArrayList<BuildingDTO1>();
		for(BuildingEntity item : buildingEntities) {
			BuildingDTO1 building = new BuildingDTO1();
			building.setName(item.getName());
			building.setAddress(item.getStreet()+","+item.getWard());
			building.setNumberOfBasement(item.getNumber());
			result.add(building);
		}
		return result;
	}
	
}
