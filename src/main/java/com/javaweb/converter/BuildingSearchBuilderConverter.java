package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.utils.MapUtil;

@Component
public class BuildingSearchBuilderConverter {
	public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> params, List<String> renttypeid) {
		BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
				.setName(MapUtil.getObject(params, "name", String.class))
				.setFloorarea(MapUtil.getObject(params, "floorarea", Integer.class))
				.setWard(MapUtil.getObject(params, "ward", String.class))
				.setDirection(MapUtil.getObject(params, "direction", String.class))
				.setDistrictid(MapUtil.getObject(params, "districtid", Integer.class))
				.setManagername(MapUtil.getObject(params, "managername", String.class))
				.setManagerphonenumber(MapUtil.getObject(params, "managerphonenumber", String.class))
				.setStreet(MapUtil.getObject(params, "street", String.class))
				.setLevel(MapUtil.getObject(params, "level", Integer.class))
				.setNumberofbasement(MapUtil.getObject(params, "numberofbasement", Integer.class))
				.setRentareafrom(MapUtil.getObject(params, "rentareafrom", Integer.class))
				.setRentareato(MapUtil.getObject(params, "rentareato", Integer.class))
				.setRentpricefrom(MapUtil.getObject(params, "rentpricefrom", Integer.class))
				.setRentpriceto(MapUtil.getObject(params, "rentpriceto", Integer.class)).setRenttypeid(renttypeid)
				.setStaffid(MapUtil.getObject(params, "staffid", Integer.class)).build();

		return buildingSearchBuilder;
	}
}
