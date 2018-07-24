package com.stackroute.modeldemo.model;

public class Vendor {

	private String vendorCode;
	private String vendorName;
	private String vendorCity;
	
	public Vendor() {
		
	}
	
	public Vendor(String vendorCode, String vendorName, String vendorCity) {
		super();
		this.vendorCode = vendorCode;
		this.vendorName = vendorName;
		this.vendorCity = vendorCity;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorCity() {
		return vendorCity;
	}

	public void setVendorCity(String vendorCity) {
		this.vendorCity = vendorCity;
	}
	
	
	

}
