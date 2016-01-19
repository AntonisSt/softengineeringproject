package com.example.softwareengineeringproject;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;

public class UserScreen extends Activity {

	private Button confirm;
	private ListView list;
	private MyDatabaseClient db;
	private ArrayList<Event> eventsList = new ArrayList<Event>();
	private CustomAdapter cAdapter;
	private static final String DATABASE_NAME = "softeng.db";
    private static final int DATABASE_VERSION = 1;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_user_screen);
		
		db = new MyDatabaseClient(this, DATABASE_NAME, null, DATABASE_VERSION);
		
		confirm = (Button) findViewById(R.id.confirm);
		list = (ListView) findViewById(R.id.eventsList);
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				getEvents();
			}
			
		});
		
		thread.start();
		
		
		cAdapter = new CustomAdapter(this, R.layout.listview_layout, eventsList);
		list.setAdapter(cAdapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Event event = (Event) arg0.getItemAtPosition(arg2);
				if(event.getAttend())
					event.setAttend(false);
				else
					event.setAttend(true);
				cAdapter.notifyDataSetChanged();
			}
			
		});
		
		list.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				Event event = (Event) arg0.getItemAtPosition(arg2);
				if(event.getType().equals("sports")) {
					SportEvent sEvent = (SportEvent) event;
					new AlertDialog.Builder(UserScreen.this)
					.setTitle(sEvent.getEventName())
					.setMessage(sEvent.getOpp1() + " vs " + sEvent.getOpp2())
					.setNeutralButton("OK", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
							}
						

					})
					.show();
				} else if(event.getType().equals("music")) {
					MusicEvent mEvent = (MusicEvent) event;
					new AlertDialog.Builder(UserScreen.this)
					.setTitle(mEvent.getEventName())
					.setMessage("Artist: " + mEvent.getArtist() +"\n" + "Genre: " + mEvent.getGenre())
					.setNeutralButton("OK", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
							}
						

					})
					.show();
				} else {
					TheaterEvent tEvent = (TheaterEvent) event;
					new AlertDialog.Builder(UserScreen.this)
					.setTitle(tEvent.getEventName())
					.setMessage("Directed by: " + tEvent.getDirector())
					.setNeutralButton("OK", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
							}
						

					})
					.show();
				}
				return true;
			}
			
		});
		
		confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				for(int i = 0; i < list.getChildCount(); i++) {
					Event event = (Event) list.getItemAtPosition(i);
					if(event.getAttend())
						db.setAttend(event.getEventName(), "true");
					else
						db.setAttend(event.getEventName(), "false");
				}
				
				cAdapter.notifyDataSetChanged();
				recreate();
			}
			
		});
	}
	
	@SuppressWarnings("deprecation")
	public void getEvents() {
		Cursor c = db.getEventsAdmin();
		while(!c.isAfterLast()) {
			String date = c.getString(c.getColumnIndex("date"));
			String[] dateParts = date.split("\\/");
			Date eventDate = new Date();
			eventDate.setDate(Integer.parseInt(dateParts[0]));
			eventDate.setMonth(Integer.parseInt(dateParts[1]));
			eventDate.setYear(Integer.parseInt(dateParts[2]));
			Date todayDate = new Date();
			if(!todayDate.after(eventDate)) {
				ArrayList<String> name = new ArrayList<String>();
				name.add(c.getString(c.getColumnIndex("events")));
				db.deleteEvents(name);
			}else {
				if(c.getString(c.getColumnIndex("type")).equals("sports")) {
					SportEvent event = new SportEvent();
					event.setEventName(c.getString(c.getColumnIndex("events")));
					event.setType(c.getString(c.getColumnIndex("type")));
					event.setEventDate(c.getString(c.getColumnIndex("date")));
					event.setOpponents(c.getString(c.getColumnIndex("opponent1")), c.getString(c.getColumnIndex("opponent2")));
					if(c.getString(c.getColumnIndex("status")).equals("true"))
						event.setAttend(true);
					else
						event.setAttend(false);
					eventsList.add(event);
				} else if(c.getString(c.getColumnIndex("type")).equals("music")) {
					MusicEvent event = new MusicEvent();
					event.setEventName(c.getString(c.getColumnIndex("events")));
					event.setType(c.getString(c.getColumnIndex("type")));
					event.setEventDate(c.getString(c.getColumnIndex("date")));
					event.setArtistGenre(c.getString(c.getColumnIndex("artist")), c.getString(c.getColumnIndex("genre")));
					if(c.getString(c.getColumnIndex("status")).equals("true"))
						event.setAttend(true);
					else
						event.setAttend(false);
					eventsList.add(event);
				} else {
					TheaterEvent event = new TheaterEvent();
					event.setEventName(c.getString(c.getColumnIndex("events")));
					event.setType(c.getString(c.getColumnIndex("type")));
					event.setEventDate(c.getString(c.getColumnIndex("date")));
					event.setDirector(c.getString(c.getColumnIndex("showName")));
					if(c.getString(c.getColumnIndex("status")).equals("true"))
						event.setAttend(true);
					else
						event.setAttend(false);
					eventsList.add(event);
				}
			}	
			c.moveToNext();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_screen, menu);
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
