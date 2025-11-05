package com.javaweb.service;

import java.util.List;

import com.javaweb.model.BuildingDTO1;

public interface BuildingService {
	List<BuildingDTO1> fineAll(String name);
}
