package com.example.softwareengineeringproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class AddEventScreen extends Activity {

	private Button confirm;
	private EditText name, opp1, opp2, genre, artist, performance;
	private RadioButton theaterRB, sportsRB, concertRB;
	private DatePicker dPicker;
	private MyDatabase db;
	private static final String DATABASE_NAME = "softeng.db";
    private static final int DATABASE_VERSION = 1;
    private String eventname = "";
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_add_event_screen);
		
		Intent i = getIntent();
		try {
			eventname = i.getStringExtra("name");
			Log.d("event name", eventname);
		} catch(NullPointerException e) {
			
		}
		
		db = new MyDatabase(this, DATABASE_NAME, null, DATABASE_VERSION);
		
		confirm = (Button) findViewById(R.id.confirm);
		name = (EditText) findViewById(R.id.eventname);
		dPicker = (DatePicker) findViewById(R.id.eventdate);
		
		theaterRB = (RadioButton) findViewById(R.id.theaterRB);
		sportsRB = (RadioButton) findViewById(R.id.sportsRB);
		concertRB = (RadioButton) findViewById(R.id.concertRB);

		opp1 = (EditText) findViewById(R.id.opponent1);
		opp2 = (EditText) findViewById(R.id.opponent2);
		genre = (EditText) findViewById(R.id.genre);
		artist = (EditText) findViewById(R.id.artist);
		performance = (EditText) findViewById(R.id.performance);
		
		theaterRB.setChecked(true);
		opp1.setVisibility(View.INVISIBLE);
		opp2.setVisibility(View.INVISIBLE);
		genre.setVisibility(View.INVISIBLE);
		artist.setVisibility(View.INVISIBLE);
		
		theaterRB.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				performance.setVisibility(View.VISIBLE);
				opp1.setVisibility(View.INVISIBLE);
				opp2.setVisibility(View.INVISIBLE);
				genre.setVisibility(View.INVISIBLE);
				artist.setVisibility(View.INVISIBLE);
			}
			
		});
		
		concertRB.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				performance.setVisibility(View.INVISIBLE);
				opp1.setVisibility(View.INVISIBLE);
				opp2.setVisibility(View.INVISIBLE);
				genre.setVisibility(View.VISIBLE);
				artist.setVisibility(View.VISIBLE);
			}
			
		});
		
		sportsRB.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				performance.setVisibility(View.INVISIBLE);
				opp1.setVisibility(View.VISIBLE);
				opp2.setVisibility(View.VISIBLE);
				genre.setVisibility(View.INVISIBLE);
				artist.setVisibility(View.INVISIBLE);
			}
			
		});
		
		confirm.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String date = dPicker.getDayOfMonth() + "/" + dPicker.getMonth() + "/" + dPicker.getYear();
				if(eventname==null)
					if(theaterRB.isChecked()) {
						db.addEventTheater(name.getText().toString(), date, "false", "theater", performance.getText().toString());
						Intent i = new Intent(AddEventScreen.this, AdminScreen.class);
						startActivity(i);
					} else if(sportsRB.isChecked()) {
						db.addEventSports(name.getText().toString(), date, "false", "sports", opp1.getText().toString(), opp2.getText().toString());
						Log.d("opp1", opp1.getText().toString());
						Intent i = new Intent(AddEventScreen.this, AdminScreen.class);
						startActivity(i);
					} else if(concertRB.isChecked()) {
						db.addEventMusic(name.getText().toString(), date, "false", "music", artist.getText().toString(), genre.getText().toString());
						Intent i = new Intent(AddEventScreen.this, AdminScreen.class);
						startActivity(i);
					} else
						Toast.makeText(AddEventScreen.this, "Συμπληρώστε όλα τα στοιχεία", Toast.LENGTH_SHORT).show();
				else {
					if(theaterRB.isChecked()) {
						db.updateEventTheater(eventname, name.getText().toString(), date, "false", "theater", performance.getText().toString());
						Intent i = new Intent(AddEventScreen.this, AdminScreen.class);
						startActivity(i);
					} else if(sportsRB.isChecked()) {
						db.updateEventSports(eventname, name.getText().toString(), date, "false", "sports", opp1.getText().toString(), opp2.getText().toString());
						Log.d("opp1", opp1.getText().toString());
						Intent i = new Intent(AddEventScreen.this, AdminScreen.class);
						startActivity(i);
					} else if(concertRB.isChecked()) {
						db.updateEventMusic(eventname, name.getText().toString(), date, "false", "music", artist.getText().toString(), genre.getText().toString());
						Intent i = new Intent(AddEventScreen.this, AdminScreen.class);
						startActivity(i);
					} else
						Toast.makeText(AddEventScreen.this, "Συμπληρώστε όλα τα στοιχεία", Toast.LENGTH_SHORT).show();
				}
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_event_screen, menu);
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
