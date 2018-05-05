package com.splyze;

import org.json.JSONException;

import com.facebook.android.FacebookError;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class MyProfileActivity extends Activity {
	
	private String[][] profileInfo;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                
        ListView list = new ListView(this);
        setContentView(list);

        try {
			profileInfo = populateProfileInfo();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FacebookError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        UserProfileDataAdapter adapter = new UserProfileDataAdapter(this, 
        		R.layout.myprofile, 
        		R.id.value, 
        		profileInfo);
                
        list.setAdapter(adapter);
    }

	private String[][] populateProfileInfo() throws JSONException, FacebookError {
		return SplyzeActivity.splyzeUserProfile.populateProfileInfo();
		}
		
	

	@Override
	public void onBackPressed(){
		super.onBackPressed();
		
		finish();
	}
}
