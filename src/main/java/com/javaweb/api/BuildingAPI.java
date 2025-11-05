package com.javaweb.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.Beans.BuildingDTO;
import com.javaweb.model.*;
import com.javaweb.Beans.ErrorResponseDTO;
import com.javaweb.customexception.FieldRequiredException;
import com.javaweb.service.BuildingService;


@RestController
public class BuildingAPI {
	static final String DB_URL="jdbc:mysql://localhost:3306/estatebasic";
	static final String USER ="root";
	static final String PASS = "binhaz091";
	@ResponseBody
    @GetMapping(value ="/api/building")
    public String getBuilding(@RequestParam(value = "name", required = false) String name,
    						  @RequestParam(value = "number", required = false) String number) {
        System.out.println(name+" "+number);
        return "OK: " + name;
    }
    
    @RequestMapping(value ="/api/building", method = RequestMethod.POST)
    public String getBuilding2(@RequestBody Map<String,String> body) {
        System.out.println("oke1");
        return "OK: ";
    }
    @RequestMapping(value ="/api/buildingx", method = RequestMethod.POST)
    public BuildingDTO getBuilding3(@RequestBody BuildingDTO buildingDTO) {
        System.out.println("oke2");
        return buildingDTO;
    }
    @DeleteMapping(value="/api/buildingd/{id}")
    public void deleteBuilding(@PathVariable Integer id) {
    	System.out.println("da xoa");
    }
    
    
    //buoi7
    @GetMapping(value="/api/building/buoi7")
    public Object getBuilding4(@RequestBody BuildingDTO building) {
//    	try {
//    		valiDate(building);
//    	}catch(FieldRequiredException e) {
//    		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
//    		errorResponseDTO.setError(e.getMessage());
//    		List<String> details = new ArrayList<>();
//    		details.add("check lại name hoặc number");
//    		errorResponseDTO.setDetail(details);
//    		return errorResponseDTO;
//    	}
    	valiDate(building);
    	return null;
    }
    public void valiDate(BuildingDTO buildingDTO){
    	if(buildingDTO.getName()==null || buildingDTO.getName().equals("") || buildingDTO.getNumber()==null ) {
    		throw new FieldRequiredException("name or number is null");
    	}
    }
    
    //buoi9
    //lay tat ca
    @GetMapping(value="/api/buildinglist")
    public List<BuildingDTO> getBuildingList(){
    	String sql = "SELECT * FROM building";
    	List<BuildingDTO> result = new ArrayList<>();
    	try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
    			Statement stmt = conn.createStatement();
    			ResultSet rs = stmt.executeQuery(sql)){
    		while(rs.next()) {
    			BuildingDTO building = new BuildingDTO();
    			building.setName(rs.getString("name"));
    			building.setStreet(rs.getString("street"));
    			building.setWard(rs.getString("ward"));
    			building.setNumber(rs.getInt("numberofbasement"));
    			result.add(building);
    		}
    	}catch(SQLException e){
    		e.printStackTrace();
    		System.out.println("Connected database failed...");
    	}
    	return result;
    }
    // lấy theo điều kiện
    @GetMapping(value="/api/buildinglist2")
    public List<BuildingDTO> getBuildingList2(@RequestParam(name="name") String name){
    	String sql = "SELECT * FROM building WHERE name like '%"+name+"%'";
    	List<BuildingDTO> result = new ArrayList<>();
    	try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
    			Statement stmt = conn.createStatement();
    			ResultSet rs = stmt.executeQuery(sql)){
    		while(rs.next()) {
    			BuildingDTO building = new BuildingDTO();
    			building.setName(rs.getString("name"));
    			building.setStreet(rs.getString("street"));
    			building.setWard(rs.getString("ward"));
    			building.setNumber(rs.getInt("numberofbasement"));
    			result.add(building);
    		}
    	}catch(SQLException e){
    		e.printStackTrace();
    		System.out.println("Connected database failed...");
    	}
    	return result;
    }
    //
    @Autowired
    private BuildingService buildingService;
    @GetMapping(value="/api/buildinglist3")
    public List<BuildingDTO1> getBuildingList3(@RequestParam(name="name") String name){
    	List<BuildingDTO1> result = buildingService.fineAll(name);
    	return result;
    }
}

