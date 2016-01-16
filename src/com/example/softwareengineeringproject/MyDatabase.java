package com.example.softwareengineeringproject;

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

	public Cursor getUser(String username, String password){
		SQLiteDatabase db = getReadableDatabase();
		String[] sel = new String[] {username, password};
		Cursor c = db.rawQuery("SELECT username, type FROM Users WHERE username = ? AND password = ?", sel);
		c.moveToFirst();
		db.close();
		return c;
	}
	
	public void setAttend(String sel, String attend) {
		String sels[] =  new String[]{sel};
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("status", attend);
		db.update("Events", values, "events = ?", sels);
		db.close();
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
	
	public boolean addUser(String username, String password, String type) {
		SQLiteDatabase db1 = getReadableDatabase();
		String[] sel = new String[]{username};
		Cursor c = db1.rawQuery("SELECT username FROM Users WHERE username = ?", sel);
		if(c.getCount() == 0) {
			SQLiteDatabase db = getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("username", username);
			values.put("password", password);
			values.put("type", type);
			db.insert("Users", null, values);
			db1.close();
			db.close();
			return true;
		} else {
			db1.close();
			return false;
		}
	}
}
