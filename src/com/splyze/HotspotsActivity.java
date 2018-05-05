package com.splyze;

import android.app.Activity;
import android.os.Bundle;

public class HotspotsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                        
        setContentView(R.layout.hotspots);
    }

	@Override
	public void onBackPressed(){
		super.onBackPressed();
		
		finish();
	}
    
}
