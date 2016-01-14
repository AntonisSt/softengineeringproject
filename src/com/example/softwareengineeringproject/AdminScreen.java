package com.example.softwareengineeringproject;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class AdminScreen extends Activity {

	private ListView list;
	private Button addEvent;
	private MyDatabase db;
	private ArrayAdapter<String> listAdapter;
	private static final String DATABASE_NAME = "softeng.db";
    private static final int DATABASE_VERSION = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_admin_screen);
		
		db = new MyDatabase(this, DATABASE_NAME, null, DATABASE_VERSION);
		list = (ListView) findViewById(R.id.eventsList);
		addEvent = (Button) findViewById(R.id.addEvent);
		
		addEvent.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(AdminScreen.this, AddEventScreen.class);
				startActivity(i);
			}
			
		});
		
		Cursor c = db.getEventsAdmin();
		ArrayList<String> events = new ArrayList<String>();
		if(c.getCount()>0) {
			while(!c.isAfterLast()) {
				events.add(c.getString(c.getColumnIndex("events")));
				c.moveToNext();
			}
			listAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, events);
			list.setAdapter(listAdapter);
		} else {
			Toast.makeText(AdminScreen.this, "Δεν υπάρχει κάποια εκδήλωση", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin_screen, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
