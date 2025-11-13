package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.model.BuildingDTO1;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;

@Component
public class BuildingDTOConverter {

	@Autowired
	private ModelMapper modelMapper;

	public BuildingDTO1 toBuildingDTO(BuildingEntity item) {
		BuildingDTO1 building = modelMapper.map(item, BuildingDTO1.class);
		building.setAddress(item.getStreet() + "," + item.getWard() + "," + item.getDistrict().getName());
		List<RentAreaEntity> rentAreas = item.getItems();
		String rentarealist = rentAreas.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
		building.setRentarea(rentarealist);
//		building.setName(item.getName());
//		building.setNumberOfBasement(item.getNumberofbasement());
//		building.setManagername(item.getManagername());
//		building.setManagerphonenumber(item.getManagerphonenumber());
//		building.setFloorarea(item.getFloorarea());
//		building.setRentprice(item.getRentprice());
		building.setNullarea(null);
		building.setPriceservices(null);
		building.setPriceMG(2);
		return building;
	}
}
