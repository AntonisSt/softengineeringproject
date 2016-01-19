package com.example.softwareengineeringproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class LoginScreen extends Activity {

	private EditText username, password;
	private Button login, register;
	private MyDatabaseClient db;
	private RadioButton user, admin;
	private static final String DATABASE_NAME = "softeng.db";
    private static final int DATABASE_VERSION = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login_screen);
		
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		
		login = (Button) findViewById(R.id.login);
		register = (Button) findViewById(R.id.signup);
		
		user = (RadioButton) findViewById(R.id.userRadioButton);
		admin = (RadioButton) findViewById(R.id.adminRadioButton);
		
		db = new MyDatabaseClient(this, DATABASE_NAME, null, DATABASE_VERSION);
		
		login.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty())
					Toast.makeText(LoginScreen.this, "Συμπληρώστε όλα τα πεδία", Toast.LENGTH_SHORT).show();
				else {
					try {
						Cursor c = db.getUser(username.getText().toString(), password.getText().toString());
						String userDB  = c.getString(c.getColumnIndex("username"));
						if(username.getText().toString().equals(userDB)){
							if(c.getString(c.getColumnIndex("type")).equals("admin")) {
								Intent i = new Intent(LoginScreen.this, AdminScreen.class);
								startActivity(i);
							} else {
								Intent i = new Intent(LoginScreen.this, UserScreen.class);
								startActivity(i);
							}
						} else {
							Toast.makeText(LoginScreen.this, "Το όνομα χρήστη δεν ταιριάζει με τον κωδικό", Toast.LENGTH_SHORT).show();
						}
					} catch(IndexOutOfBoundsException e) {
						Toast.makeText(LoginScreen.this, "Το όνομα χρήστη δεν υπάρχει", Toast.LENGTH_SHORT).show();
					}
				}
			}
			
		});
		
		register.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty())
					Toast.makeText(LoginScreen.this, "Συμπληρώστε όλα τα πεδία", Toast.LENGTH_SHORT).show();
				else {
					if(admin.isChecked()) {
						if(db.addUser(username.getText().toString(), password.getText().toString(), "admin")) {
							Intent i = new Intent(LoginScreen.this, AdminScreen.class);
							startActivity(i);
						} else {
							Toast.makeText(LoginScreen.this, "Το όνομα χρήστη υπάρχει ήδη.", Toast.LENGTH_SHORT).show();
						}
					} else if(user.isChecked()){
						if(db.addUser(username.getText().toString(), password.getText().toString(), "user")) {
							Intent i = new Intent(LoginScreen.this, UserScreen.class);
							startActivity(i);
						} else {
							Toast.makeText(LoginScreen.this, "Το όνομα χρήστη υπάρχει ήδη.", Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(LoginScreen.this, "Επιλέξτε κάποια ιδιότητα.", Toast.LENGTH_SHORT).show();
					}
				}
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_screen, menu);
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
