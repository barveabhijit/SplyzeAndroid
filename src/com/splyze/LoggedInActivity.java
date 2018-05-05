package com.splyze;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LoggedInActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                        
        setContentView(R.layout.loggedin);
    }
    
    public void buttonClicked(View view){    	
		switch (view.getId()){
			case R.id.hotspot_button:
				Intent hotspotsIntent = new Intent(this, HotspotsActivity.class);
				startActivity(hotspotsIntent);
				break;
			case R.id.users_button:
				Toast.makeText(getApplicationContext(),"Clicked on Users", Toast.LENGTH_LONG).show();
				break;
			case R.id.schedule_button:
				Intent scheduleIntent = new Intent(this, ScheduleActivity.class);
				startActivity(scheduleIntent);
				break;
			case  R.id.map_button:
				Intent eventMapIntent = new Intent(this, EventMapActivity.class);
				startActivity(eventMapIntent);
				break;
			case R.id.my_schedule_button:
				Toast.makeText(getApplicationContext(),"Clicked on My Schedule", Toast.LENGTH_LONG).show();
				break;
			case R.id.my_profile_button:
				Intent myProfileIntent = new Intent(this, MyProfileActivity.class);
				startActivity(myProfileIntent);
				break;
		}
    }
   
	@Override
	public void onBackPressed(){
		super.onBackPressed();
		
		finish();
	}
	
}
