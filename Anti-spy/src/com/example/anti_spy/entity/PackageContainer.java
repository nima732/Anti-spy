package com.example.anti_spy.entity;

public class PackageContainer {
	String completePackageName;
	String packageName;
	int IdNumber;
	
	public PackageContainer(String packageName, int idNumber) {
		super();
		this.packageName = packageName;
		IdNumber = idNumber;
	}
	
	public PackageContainer(){
		
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public int getIdNumber() {
		return IdNumber;
	}
	public void setIdNumber(int idNumber) {
		IdNumber = idNumber;
	}
	public String getCompletePackageName() {
		return completePackageName;
	}

	public void setCompletePackageName(String completePackageName) {
		this.completePackageName = completePackageName;
	}

}
