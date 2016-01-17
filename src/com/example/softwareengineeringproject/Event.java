package com.example.softwareengineeringproject;

public interface Event {
	
	public void setEventName(String name);
	public void setEventDate(String date);
	public void setAttend(boolean attend);
	public void setType(String type);
	
	public String getEventName();
	public String getEventDate();
	public Boolean getAttend();
	public String getType();
}
