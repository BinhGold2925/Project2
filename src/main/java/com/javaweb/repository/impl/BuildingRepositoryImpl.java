package com.javaweb.repository.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;



@Repository
public class BuildingRepositoryImpl implements BuildingRepository{
	static final String DB_URL="jdbc:mysql://localhost:3306/estatebasic";
	static final String USER ="root";
	static final String PASS = "binhaz091";
	
	public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
		Integer staffid = buildingSearchBuilder.getStaffid();
		if(staffid != null) {
			sql.append(" INNER JOIN assignmentbuilding ab ON b.id = ab.buildingid ");
		}
		List<String> renttypeid = buildingSearchBuilder.getRenttypeid();
		if(renttypeid != null && renttypeid.size() != 0) {
			sql.append(" INNER JOIN buildingrenttype br ON b.id = br.buildingid ");
			sql.append(" INNER JOIN renttype rt ON br.renttypeid = rt.id ");
		}
		Integer rentareafrom = buildingSearchBuilder.getRentareafrom();
		Integer rentareato = buildingSearchBuilder.getRentareato();
		if(rentareafrom!=null || rentareato!=null) {
			sql.append(" INNER JOIN rentarea ra ON b.id = ra.buildingid ");
		}
	}
	public static void queryNormal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for(Field item : fields) {
				item.setAccessible(true);
				String fieldName = item.getName();
				if(!fieldName.equals("staffid") && !fieldName.equals("renttypeid") &&
						!fieldName.startsWith("rentarea") && !fieldName.startsWith("rentprice")) {
					Object value = item.get(buildingSearchBuilder);
					if(value != null) {
						if(item.getType().getName().equals("java.lang.Integer")) {
							where.append(" AND b."+fieldName+" = "+value+" ");
						}else {
							where.append("AND b."+fieldName+" like '%"+value+"%' ");
						}
					}
				}
						
			}
 		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		Integer staffid = buildingSearchBuilder.getStaffid();
		if(staffid !=null) {
			where.append(" AND ab.staffid = "+staffid+" ");
		}
		Integer rentareafrom = buildingSearchBuilder.getRentareafrom();
		Integer rentareato = buildingSearchBuilder.getRentareato();
		if(rentareafrom!=null) {
			where.append(" AND ra.value >= "+ rentareafrom +" ");
		}
		if(rentareato!=null) {
			where.append(" AND ra.value <= "+ rentareato +" ");
		}
		Integer rentpricefrom = buildingSearchBuilder.getRentpricefrom();
		Integer rentpriceto = buildingSearchBuilder.getRentpriceto();
		if(rentpricefrom!=null) {
			where.append(" AND b.rentprice >= "+ rentpricefrom +" ");
		}
		if(rentpriceto != null) {
			where.append(" AND b.rentprice <= "+ rentpriceto +" ");
		}
		List<String> renttypeid = buildingSearchBuilder.getRenttypeid();
		if(renttypeid != null && renttypeid.size() != 0) {
			List<String> code = new ArrayList<>();
			for(String item : renttypeid) {
				code.add("'"+item+"'");
			}
			where.append(" AND rt.code IN("+String.join(",", code)+") ");
		}
	}
	
	@Override
	public List<BuildingEntity> fineAll(BuildingSearchBuilder buildingSearchBuilder) {
		
		StringBuilder sql = new StringBuilder(
			    "SELECT " +
			    // alias theo đúng nhãn bạn đang get trong ResultSet (chú ý backtick `...`)
			    "  b.id                      AS `b.id`, " +
			    "  b.name                    AS `b.name`, " +
			    "  b.floorarea               AS `b.floorarea`, " +
			    "  b.districtid              AS `b.districtid`, " +
			    "  b.ward                    AS `b.ward`, " +
			    "  b.street                  AS `b.street`, " +
			    "  b.numberofbasement        AS `b.numberofbasement`, " +
			    "  b.direction               AS `b.direction`, " +
			    "  b.level                   AS `b.level`, " +
			    "  b.rentprice               AS `b.rentprice`, " +
			    "  b.rentpricedescription    AS `b.rentpricedescription`, " +
			    "  b.managername             AS `b.managername`, " +
			    "  b.managerphonenumber      AS `b.managerphonenumber` " +
			    "FROM building b "
			);
		joinTable(buildingSearchBuilder, sql);
		StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
		queryNormal(buildingSearchBuilder, where);
		querySpecial(buildingSearchBuilder, where);
		sql.append(where);
		sql.append(" GROUP BY b.id ");

		
    	List<BuildingEntity> result = new ArrayList<>();
    	try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
    			Statement stmt = conn.createStatement();
    			ResultSet rs = stmt.executeQuery(sql.toString())){
    		while(rs.next()) {
    			BuildingEntity building = new BuildingEntity();
    			building.setId(rs.getInt("b.id"));
    			building.setName(rs.getString("b.name"));
    			building.setFloorarea(rs.getInt("b.floorarea"));
    			building.setDistrictid(rs.getInt("b.districtid"));
    			building.setWard(rs.getString("b.ward"));
    			building.setStreet(rs.getString("b.street"));
    			building.setNumberofbasement(rs.getInt("b.numberofbasement"));
    			building.setDirection(rs.getString("b.direction"));
    			building.setLevel(rs.getInt("b.level"));
    			building.setRentprice(rs.getInt("b.rentprice"));
    			building.setRentpricedescriprion(rs.getString("b.rentpricedescription"));
    			building.setManagername(rs.getString("b.managername"));
    			building.setManagerphonenumber(rs.getString("b.managerphonenumber"));
    			result.add(building);
    		}
    	}catch(SQLException e){
    		e.printStackTrace();
    		System.out.println("Connected database failed...");
    	}
    	return result;
	}
	
	
	
}
