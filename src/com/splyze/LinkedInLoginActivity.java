package com.splyze;

import com.google.code.linkedinapi.schema.Person;

import android.app.Activity;
//import android.content.Intent;
import android.os.Bundle;

public class LinkedInLoginActivity extends Activity {	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LinkedInWebView webview = new LinkedInWebView(this);
        setContentView(webview);
        
        webview.processLinkedInLogin();               
    }
	
	@Override
	public void onBackPressed(){
		super.onBackPressed();
		
		finish();
	}
	
	public void linkedInUserProfile(int resultCode, Person userProfile){
		
		if (resultCode == RESULT_OK){			
	
			SplyzeActivity.splyzeUserProfile.resetProfile();
			SplyzeActivity.splyzeUserProfile.setProfileSource(getString(R.string.linkedin));
			SplyzeActivity.splyzeUserProfile.setLinkedInProfile(userProfile);
			
			setResult(RESULT_OK);
		}
		
   		finish();
	}
	
}
