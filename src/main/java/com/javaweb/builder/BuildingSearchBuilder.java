package com.javaweb.builder;

import java.util.ArrayList;
import java.util.List;

public class BuildingSearchBuilder {
	private String name;
	private Integer floorarea;
	private Integer districtid;
	private String ward;
	private String street;
	private List<String> renttypeid = new ArrayList<>();
	private Integer numberofbasement;
	private String direction;
	private Integer level;
	private Integer rentpricefrom;
	private Integer rentpriceto;
	private String managername;
	private String managerphonenumber;
	private Integer rentareafrom;
	private Integer rentareato;
	private Integer staffid;

	private BuildingSearchBuilder(Builder builder) {
		this.name = builder.name;
		this.floorarea = builder.floorarea;
		this.districtid = builder.districtid;
		this.ward = builder.ward;
		this.street = builder.street;
		this.renttypeid = builder.renttypeid;
		this.numberofbasement = builder.numberofbasement;
		this.direction = builder.direction;
		this.level = builder.level;
		this.rentpricefrom = builder.rentpricefrom;
		this.rentpriceto = builder.rentpriceto;
		this.managername = builder.managername;
		this.managerphonenumber = builder.managerphonenumber;
		this.rentareafrom = builder.rentareafrom;
		this.rentareato = builder.rentareato;
		this.staffid = builder.staffid;
	}

	public String getName() {
		return name;
	}

	public Integer getFloorarea() {
		return floorarea;
	}

	public Integer getDistrictid() {
		return districtid;
	}

	public String getWard() {
		return ward;
	}

	public String getStreet() {
		return street;
	}

	public List<String> getRenttypeid() {
		return renttypeid;
	}

	public Integer getNumberofbasement() {
		return numberofbasement;
	}

	public String getDirection() {
		return direction;
	}

	public Integer getLevel() {
		return level;
	}

	public Integer getRentpricefrom() {
		return rentpricefrom;
	}

	public Integer getRentpriceto() {
		return rentpriceto;
	}

	public String getManagername() {
		return managername;
	}

	public String getManagerphonenumber() {
		return managerphonenumber;
	}

	public Integer getRentareafrom() {
		return rentareafrom;
	}

	public Integer getRentareato() {
		return rentareato;
	}

	public Integer getStaffid() {
		return staffid;
	}

	public static class Builder {
		private String name;
		private Integer floorarea;
		private Integer districtid;
		private String ward;
		private String street;
		private List<String> renttypeid = new ArrayList<>();
		private Integer numberofbasement;
		private String direction;
		private Integer level;
		private Integer rentpricefrom;
		private Integer rentpriceto;
		private String managername;
		private String managerphonenumber;
		private Integer rentareafrom;
		private Integer rentareato;
		private Integer staffid;

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setFloorarea(Integer floorarea) {
			this.floorarea = floorarea;
			return this;
		}

		public Builder setDistrictid(Integer districtid) {
			this.districtid = districtid;
			return this;
		}

		public Builder setWard(String ward) {
			this.ward = ward;
			return this;
		}

		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}

		public Builder setRenttypeid(List<String> renttypeid) {
			this.renttypeid = renttypeid;
			return this;
		}

		public Builder setNumberofbasement(Integer numberofbasement) {
			this.numberofbasement = numberofbasement;
			return this;
		}

		public Builder setDirection(String direction) {
			this.direction = direction;
			return this;
		}

		public Builder setLevel(Integer level) {
			this.level = level;
			return this;
		}

		public Builder setRentpricefrom(Integer rentpricefrom) {
			this.rentpricefrom = rentpricefrom;
			return this;
		}

		public Builder setRentpriceto(Integer rentpriceto) {
			this.rentpriceto = rentpriceto;
			return this;
		}

		public Builder setRentareafrom(Integer rentareafrom) {
			this.rentareafrom = rentareafrom;
			return this;
		}

		public Builder setRentareato(Integer rentareato) {
			this.rentareato = rentareato;
			return this;
		}

		public Builder setManagername(String managername) {
			this.managername = managername;
			return this;
		}

		public Builder setManagerphonenumber(String managerphonenumber) {
			this.managerphonenumber = managerphonenumber;
			return this;
		}

		public Builder setStaffid(Integer staffid) {
			this.staffid = staffid;
			return this;
		}

		public BuildingSearchBuilder build() {
			return new BuildingSearchBuilder(this);
		}
	}

}
