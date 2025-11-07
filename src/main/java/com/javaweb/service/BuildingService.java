package com.javaweb.service;

import java.util.List;
import java.util.Map;

import com.javaweb.model.BuildingDTO1;

public interface BuildingService {
	List<BuildingDTO1> fineAll(Map<String, Object> params, List<String> renttypeid);
}
