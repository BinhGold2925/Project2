package com.javaweb.repository.impl;

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

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;



@Repository
public class BuildingRepositoryImpl implements BuildingRepository{
	static final String DB_URL="jdbc:mysql://localhost:3306/estatebasic";
	static final String USER ="root";
	static final String PASS = "binhaz091";
	
	public static void joinTable(Map<String, Object> params, List<String> renttypeid, StringBuilder sql) {
		String staffid = (String)params.get("staffid");
		if(StringUtil.checkString(staffid)) {
			sql.append(" INNER JOIN assignmentbuilding ab ON b.id = ab.buildingid ");
		}
		if(renttypeid != null && renttypeid.size() != 0) {
			sql.append(" INNER JOIN buildingrenttype br ON b.id = br.buildingid ");
			sql.append(" INNER JOIN renttype rt ON br.renttypeid = rt.id ");
		}
		String rentareafrom = (String)params.get("rentareafrom");
		String rentareato = (String)params.get("rentareato");
		if(StringUtil.checkString(rentareafrom) || StringUtil.checkString(rentareato)) {
			sql.append(" INNER JOIN rentarea ra ON b.id = ra.buildingid ");
		}
	}
	public static void queryNormal(Map<String, Object> params, List<String> renttypeid, StringBuilder where) {
		for(Entry<String, Object> it : params.entrySet()) {
			if(!it.getKey().equals("staffid") && !it.getKey().equals("renttypeid") &&
					!it.getKey().startsWith("rentarea") && !it.getKey().startsWith("rentprice")) {
				String value = it.getValue().toString();
				if(StringUtil.checkString(value)) {
					if(NumberUtil.isNumber(value)) {
						where.append(" AND b."+it.getKey()+" = "+value+" ");
					}else {
						where.append("AND b."+it.getKey()+" like '%"+value+"%' ");
					}
				}
			}
		}
	}
	public static void querySpecial(Map<String, Object> params, List<String> renttypeid, StringBuilder where) {
		String staffid = (String)params.get("staffid");
		if(StringUtil.checkString(staffid)) {
			where.append(" AND ab.staffid = "+staffid+" ");
		}
		String rentareafrom = (String)params.get("rentareafrom");
		String rentareato = (String)params.get("rentareato");
		if(StringUtil.checkString(rentareafrom)) {
			where.append(" AND ra.value >= "+ rentareafrom +" ");
		}
		if(StringUtil.checkString(rentareato)) {
			where.append(" AND ra.value <= "+ rentareato +" ");
		}
		String rentpricefrom = (String)params.get("rentpricefrom");
		String rentpriceto = (String)params.get("rentpriceto");
		if(StringUtil.checkString(rentpricefrom)) {
			where.append(" AND b.rentprice >= "+ rentpricefrom +" ");
		}
		if(StringUtil.checkString(rentpriceto)) {
			where.append(" AND b.rentprice <= "+ rentpriceto +" ");
		}
		if(renttypeid != null && renttypeid.size() != 0) {
			List<String> code = new ArrayList<>();
			for(String item : renttypeid) {
				code.add("'"+item+"'");
			}
			where.append(" AND rt.code IN("+String.join(",", code)+") ");
		}
	}
	
	@Override
	public List<BuildingEntity> fineAll(Map<String, Object> params, List<String> renttypeid) {
		
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
		joinTable(params,renttypeid, sql);
		StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
		queryNormal(params,renttypeid, where);
		querySpecial(params,renttypeid, where);
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
