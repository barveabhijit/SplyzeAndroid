package com.splyze;

import twitter4j.TwitterException;
import twitter4j.User;
import android.app.Activity;
//import android.content.Intent;
import android.os.Bundle;

public class TwitterLoginActivity extends Activity {	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        TwitterWebView webview = new TwitterWebView(this);
        setContentView(webview);
        
        try {
			webview.processLogin();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}               
    }
	
	@Override
	public void onBackPressed(){
		super.onBackPressed();
		
		finish();
	}
	
	public void TwitterUserProfile(int resultCode, User twitterUser){
		
		if (resultCode == RESULT_OK){			
//			Intent linkedInUser = new Intent();
//			linkedInUser.putExtra ("full name", userProfile.getFirstName() + " " + userProfile.getLastName());
	
			SplyzeActivity.splyzeUserProfile.resetProfile();
			SplyzeActivity.splyzeUserProfile.setProfileSource(getString(R.string.twitter));
			SplyzeActivity.splyzeUserProfile.setTwitterProfile(twitterUser);
			
			setResult(RESULT_OK);
//			setResult(RESULT_OK, linkedInUser);
		}
		
   		finish();
	}

}
