package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.model.BuildingDTO1;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService{
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private RentAreaRepository rentAreaRepository;
	
	@Autowired
	private BuildingDTOConverter buildingDTOConverter;

	@Override
	public List<BuildingDTO1> fineAll(Map<String, Object> params, List<String> renttypeid) {
		List<BuildingEntity> buildingEntities = buildingRepository.fineAll(params, renttypeid);
		List<BuildingDTO1> result = new ArrayList<BuildingDTO1>();
		for(BuildingEntity item : buildingEntities) {
			BuildingDTO1 building = buildingDTOConverter.toBuildingDTO(item);
			result.add(building);
		}
		return result;
	}
	
	
	
}
