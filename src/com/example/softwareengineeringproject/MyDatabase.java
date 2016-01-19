package com.example.softwareengineeringproject;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class MyDatabase extends SQLiteAssetHelper{

	public MyDatabase(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	public void deleteEvents(ArrayList<String> names) {
		SQLiteDatabase db = getWritableDatabase();
		for(int i = 0; i < names.size(); i++) {
			String[] sels = new String[]{names.get(i)};
			db.delete("Events", "events = ?", sels);
		}
		db.close();
	}
	
	public boolean updateEventTheater(String eventname, String name, String date, String attend, String type, String director) {
		SQLiteDatabase db1 = getReadableDatabase();
		String[] sel = new String[]{eventname};
		Cursor c = db1.rawQuery("SELECT events FROM Events WHERE events = ?", sel);
		if(c.getCount() != 0) {
			SQLiteDatabase db = getWritableDatabase();
			db.delete("Events", "events = ?", sel);
			ContentValues values = new ContentValues();
			values.put("events", name);
			values.put("date", date);
			values.put("status", attend);
			values.put("type", type);
			values.put("showName", director);
			db.insert("Events", null, values);
			db1.close();
			db.close();
			return true;
		} else {
			db1.close();
			return false;
		}
	}
	
	public boolean updateEventSports(String eventname, String name, String date, String attend, String type, String opp1, String opp2) {
		SQLiteDatabase db1 = getReadableDatabase();
		String[] sel = new String[]{eventname};
		Cursor c = db1.rawQuery("SELECT events FROM Events WHERE events = ?", sel);
		if(c.getCount() != 0) {
			SQLiteDatabase db = getWritableDatabase();
			db.delete("Events", "events = ?", sel);
			ContentValues values = new ContentValues();
			values.put("events", name);
			values.put("date", date);
			values.put("status", attend);
			values.put("type", type);
			values.put("opponent1", opp1);
			values.put("opponent2", opp2);
			db.insert("Events", null, values);
			db1.close();
			db.close();
			return true;
		} else {
			db1.close();
			return false;
		}
	}
	
	public boolean updateEventMusic(String eventname, String name, String date, String attend, String type, String artist, String genre) {
		SQLiteDatabase db1 = getReadableDatabase();
		String[] sel = new String[]{eventname};
		Cursor c = db1.rawQuery("SELECT events FROM Events WHERE events = ?", sel);
		if(c.getCount() != 0) {
			SQLiteDatabase db = getWritableDatabase();
			db.delete("Events", "events = ?", sel);
			ContentValues values = new ContentValues();
			values.put("events", name);
			values.put("date", date);
			values.put("status", attend);
			values.put("type", type);
			values.put("artist", artist);
			values.put("genre", genre);
			db.insert("Events", null, values);
			db1.close();
			db.close();
			return true;
		} else {
			db1.close();
			return false;
		}
	}
	
	
	public boolean addEventTheater(String name, String date, String attend, String type, String director) {
		SQLiteDatabase db1 = getReadableDatabase();
		String[] sel = new String[]{name};
		Cursor c = db1.rawQuery("SELECT events FROM Events WHERE events = ?", sel);
		if(c.getCount() == 0) {
			SQLiteDatabase db = getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("events", name);
			values.put("date", date);
			values.put("status", attend);
			values.put("type", type);
			values.put("showName", director);
			db.insert("Events", null, values);
			db1.close();
			db.close();
			return true;
		} else {
			db1.close();
			return false;
		}
	}
	
	public boolean addEventSports(String name, String date, String attend, String type, String opp1, String opp2) {
		SQLiteDatabase db1 = getReadableDatabase();
		String[] sel = new String[]{name};
		Cursor c = db1.rawQuery("SELECT events FROM Events WHERE events = ?", sel);
		if(c.getCount() == 0) {
			SQLiteDatabase db = getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("events", name);
			values.put("date", date);
			values.put("status", attend);
			values.put("type", type);
			values.put("opponent1", opp1);
			values.put("opponent2", opp2);
			db.insert("Events", null, values);
			db1.close();
			db.close();
			return true;
		} else {
			db1.close();
			return false;
		}
	}
	
	public boolean addEventMusic(String name, String date, String attend, String type, String artist, String genre) {
		SQLiteDatabase db1 = getReadableDatabase();
		String[] sel = new String[]{name};
		Cursor c = db1.rawQuery("SELECT events FROM Events WHERE events = ?", sel);
		if(c.getCount() == 0) {
			SQLiteDatabase db = getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("events", name);
			values.put("date", date);
			values.put("status", attend);
			values.put("type", type);
			values.put("artist", artist);
			values.put("genre", genre);
			db.insert("Events", null, values);
			db1.close();
			db.close();
			return true;
		} else {
			db1.close();
			return false;
		}
	}
	
	public Cursor getEventsAdmin() {
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM Events", null);
		c.moveToFirst();
		db.close();
		return c;
	}
	
	
}
