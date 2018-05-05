package com.splyze;

import android.app.Activity;
import android.os.Bundle;

public class ScheduleActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                        
        setContentView(R.layout.schedule);
    }

	@Override
	public void onBackPressed(){
		super.onBackPressed();
		
		finish();
	}
	
}
