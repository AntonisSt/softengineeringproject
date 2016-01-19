package com.example.softwareengineeringproject;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class MyDatabaseClient extends SQLiteAssetHelper{

	public MyDatabaseClient(Context context, String name, CursorFactory factory,
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
	
	public void deleteEvents(ArrayList<String> names) {
		SQLiteDatabase db = getWritableDatabase();
		for(int i = 0; i < names.size(); i++) {
			String[] sels = new String[]{names.get(i)};
			db.delete("Events", "events = ?", sels);
		}
		db.close();
	}
	
	public void setAttend(String sel, String attend) {
		String sels[] =  new String[]{sel};
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("status", attend);
		db.update("Events", values, "events = ?", sels);
		db.close();
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
