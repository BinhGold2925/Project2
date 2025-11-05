package com.javaweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.javaweb.model.*;
import com.javaweb.service.BuildingService;


@RestController
public class BuildingAPI {
	@Autowired
    private BuildingService buildingService;
    @GetMapping(value="/api/buildinglist3")
    public List<BuildingDTO1> getBuildingList3(@RequestParam(name="name") String name){
    	List<BuildingDTO1> result = buildingService.fineAll(name);
    	return result;
    }
}

