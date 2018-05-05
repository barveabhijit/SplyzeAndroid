package com.splyze;

import java.io.IOException;
import java.net.MalformedURLException;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SplyzeActivity extends Activity {
	
	Facebook facebook = null;
	
	final int FACEBOOK_LOGIN = 1;
	final int LINKEDIN_LOGIN = 2;
	final int TWITTER_LOGIN = 3;
	final int EVENT_SELECTOR = 4;
	
	public static SplyzeUserProfile splyzeUserProfile = new SplyzeUserProfile();
	
	final String facebookAppId = "247623641931174";
	
	private DialogListener FacebookLoginListener = new DialogListener() {
    	public void onComplete(Bundle values) {
    		
    		String userInfo = null;	    		
    		
    		try {
    			userInfo = facebook.request("me");
    			
    			splyzeUserProfile.resetProfile();
    			
    			splyzeUserProfile.setProfileSource(getString(R.string.facebook));
    			splyzeUserProfile.setFacebookProfile(userInfo);
    			
    			gotoEventSelector();
    		} catch (MalformedURLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}            
    		try {
    			facebook.logout(getApplicationContext());
    			facebook = null;
    		} catch (MalformedURLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    	}
    	
    	public void onFacebookError(FacebookError error) {}
    	
    	public void onError(DialogError e) {}
    	
    	public void onCancel() {}
    			
	};
	
	private void handleFacebookLogin(){
		facebook = new Facebook(facebookAppId);		
	    facebook.authorize(this, FacebookLoginListener );
	}
	
	private void handleLinkedInLogin(){
		Intent linkedInLoginIntent = new Intent(this, LinkedInLoginActivity.class);
		startActivityForResult(linkedInLoginIntent, LINKEDIN_LOGIN);
	}
	
	private void handleTwitterLogin(){
		Intent twitterLoginIntent = new Intent( this, TwitterLoginActivity.class);
		startActivityForResult(twitterLoginIntent, TWITTER_LOGIN);
	}
	
	public void processLogin(View view) {
		switch (view.getId()){
			case R.id.facebook_button:
				handleFacebookLogin();
				break;
			case R.id.linkedin_button:
				handleLinkedInLogin();
				break;
			case R.id.twitter_button:
				handleTwitterLogin();
				break;	
			}
	}
	
	public void gotoEventSelector(){
		Intent eventSelectorIntent = new Intent(this, EventSelectorActivity.class);
//		Intent eventSelectorIntent = new Intent(this, EventDetailActivity.class);		
		startActivityForResult(eventSelectorIntent, EVENT_SELECTOR);
		
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                        
        setContentView(R.layout.welcome);
    }
    
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		
		switch (requestCode){
			case FACEBOOK_LOGIN:
				if ( facebook != null ){
					facebook.authorizeCallback(requestCode, resultCode, data);    				
				}
				break;
			case LINKEDIN_LOGIN:
				if (resultCode == RESULT_OK){
					gotoEventSelector();
				}
				break;
			case TWITTER_LOGIN:
				if (resultCode == RESULT_OK){
					gotoEventSelector();
				}
				break;
			case EVENT_SELECTOR:
				finish();
				break;
		}
	}    
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (facebook != null) {
			try {
				facebook.logout(getApplicationContext());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void onBackPressed(){
		super.onBackPressed();
		finish();
	}
}