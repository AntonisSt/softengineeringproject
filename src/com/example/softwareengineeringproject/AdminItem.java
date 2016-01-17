package com.example.softwareengineeringproject;

public class AdminItem {

	private String name;
	private boolean checked;
	
	public AdminItem(String name, boolean checked) {
		this.name = name;
		this.checked = checked;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean getChecked() {
		return checked;
	}
}
