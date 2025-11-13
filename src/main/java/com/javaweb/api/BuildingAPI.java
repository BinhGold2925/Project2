package com.javaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.javaweb.model.*;
import com.javaweb.service.BuildingService;

import jakarta.transaction.Transactional;

@RestController
@PropertySource("classpath:application.properties")
@Transactional
public class BuildingAPI {
	@Autowired
	private BuildingService buildingService;
	
	@Value("${dev.nguyen}")
	private String data;
	
	@GetMapping(value = "/api/buildinglist3")
	public List<BuildingDTO1> getBuildingList3(@RequestParam Map<String, Object> params,
			@RequestParam(name = "renttypeid", required = false) List<String> renttypeid) {
		List<BuildingDTO1> result = buildingService.fineAll(params, renttypeid);
		System.out.print(data);
		return result;
	}
	@PostMapping(value="/api/buildinglist3")
	public void createBuilding() {
		
	}
}
