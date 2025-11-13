package com.javaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.javaweb.model.*;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.service.BuildingService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
	@PersistenceContext
	private EntityManager entityManager;
	
	@PostMapping(value="/api/buildinglist3")
	public void createBuilding(@RequestBody BuildingRequestDTO buildingRequestDTO) {
		BuildingEntity buildingEntity = new BuildingEntity();
		buildingEntity.setName(buildingRequestDTO.getName());
		DistrictEntity districtEntity = new DistrictEntity();
		districtEntity.setId(buildingRequestDTO.getDistrictid());
		buildingEntity.setDistrict(districtEntity);
		entityManager.persist(buildingEntity);
	}// đúng ra phải xử lý dưới tầng repository
}
