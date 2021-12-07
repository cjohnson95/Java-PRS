package com.PRS.Capstone.product;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.PRS.Capstone.vendor.Vendor;

@Entity(name="products")
@Table(uniqueConstraints=@UniqueConstraint(name="UIDX_partnbr", columnNames={"partnbr"}))
public class Product {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(length=30, nullable=false)
	private String partnbr;
	@Column(length=30, nullable=false)
	private String name;
	@Column(columnDefinition="decimal(11, 2) NOT NULL DEFAULT 0.0")
	private double price;  
	@Column(length=30, nullable=false)																// Ask tomorrow if we're supposed to use double or decimal 
	private String unit;
	@Column(length=255)
	private String photopath;
	

	
	@ManyToOne(optional=false)
	@JoinColumn(name="vendorid")
	private Vendor vendor;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPartnbr() {
		return partnbr;
	}

	public void setPartnbr(String partnbr) {
		this.partnbr = partnbr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPhotopath() {
		return photopath;
	}

	public void setPhotopath(String photopath) {
		this.photopath = photopath;
	}


	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	

}
