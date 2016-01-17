package com.example.softwareengineeringproject;

public class MusicEvent implements Event{
	
	private String name;
	private String date;
	private boolean attend;
	private String type;
	private String artist;
	private String genre;
	
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
	
	public void setArtistGenre(String artist, String genre) {
		this.artist = artist;
		this.genre = genre;
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
	
	public String getArtist() {
		return artist;
	}
	
	public String getGenre() {
		return genre;
	}
}
