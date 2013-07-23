package com.example.anti_spy.entity;

public class Resource {
	private long id;
	private String resource_name;
	private String resource_selected;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getResource_name() {
		return resource_name;
	}
	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
	}
	public String getResource_selected() {
		return resource_selected;
	}
	public void setResource_selected(String resource_selected) {
		this.resource_selected = resource_selected;
	}
}
