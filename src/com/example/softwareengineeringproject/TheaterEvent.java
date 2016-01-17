package com.example.softwareengineeringproject;

public class TheaterEvent implements Event{

	private String name;
	private String date;
	private boolean attend;
	private String type;
	private String director;
	
	@Override
	public void setEventName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	@Override
	public void setEventDate(String date) {
		// TODO Auto-generated method stub
		this.date = date;
	}

	@Override
	public void setAttend(boolean attend) {
		// TODO Auto-generated method stub
		this.attend = attend;
	}

	@Override
	public void setType(String type) {
		// TODO Auto-generated method stub
		this.type = type;
	}
	
	public void setDirector(String director) {
		this.director = director;
	}

	@Override
	public String getEventName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getEventDate() {
		// TODO Auto-generated method stub
		return date;
	}

	@Override
	public Boolean getAttend() {
		// TODO Auto-generated method stub
		return attend;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}
	
	public String getDirector() {
		return director;
	}

}
