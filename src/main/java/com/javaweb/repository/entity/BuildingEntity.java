package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "building")
public class BuildingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	@Column(name = "floorarea")
	private Integer floorarea;

	@Column(name = "ward")
	private String ward;
	@Column(name = "street")
	private String street;
	@Column(name = "numberofbasement")
	private Integer numberofbasement;
	@Column(name = "direction")
	private String direction;
	@Column(name = "level")
	private Integer level;
	@Column(name = "rentprice")
	private Integer rentprice;
	@Column(name = "rentpricedescriprion")
	private String rentpricedescriprion;
	@Column(name = "managername")
	private String managername;
	@Column(name = "managerphonenumber")
	private String managerphonenumber;
	
	@ManyToOne
	@JoinColumn(name = "districtid")
	private DistrictEntity district;
	
	@OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
	private List<RentAreaEntity> items = new ArrayList<>();
	
	
	public List<RentAreaEntity> getItems() {
		return items;
	}

	public void setItems(List<RentAreaEntity> items) {
		this.items = items;
	}

	public DistrictEntity getDistrict() {
		return district;
	}

	public void setDistrict(DistrictEntity district) {
		this.district = district;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFloorarea() {
		return floorarea;
	}

	public void setFloorarea(Integer floorarea) {
		this.floorarea = floorarea;
	}


	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNumberofbasement() {
		return numberofbasement;
	}

	public void setNumberofbasement(Integer numberofbasement) {
		this.numberofbasement = numberofbasement;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getRentprice() {
		return rentprice;
	}

	public void setRentprice(Integer rentprice) {
		this.rentprice = rentprice;
	}

	public String getRentpricedescriprion() {
		return rentpricedescriprion;
	}

	public void setRentpricedescriprion(String rentpricedescriprion) {
		this.rentpricedescriprion = rentpricedescriprion;
	}

	public String getManagername() {
		return managername;
	}

	public void setManagername(String managername) {
		this.managername = managername;
	}

	public String getManagerphonenumber() {
		return managerphonenumber;
	}

	public void setManagerphonenumber(String managerphonenumber) {
		this.managerphonenumber = managerphonenumber;
	}

}
