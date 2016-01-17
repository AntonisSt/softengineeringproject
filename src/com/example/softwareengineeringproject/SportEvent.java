package com.example.softwareengineeringproject;

public class SportEvent implements Event{

	private String name;
	private String date;
	private boolean attend;
	private String type;
	private String opponent1;
	private String opponent2;
	
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
	
	public void setOpponents(String opp1, String opp2) {
		opponent1 = opp1;
		opponent2 = opp2;
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
	
	public String getOpp1() {
		return opponent1;
	}
	
	public String getOpp2() {
		return opponent2;
	}

}
