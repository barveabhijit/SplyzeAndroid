package com.splyze;

import android.app.Activity;
import android.os.Bundle;

public class EventDetailActivity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Bundle eventDetailInfo = this.getIntent().getExtras();
        String eventId = eventDetailInfo.getString("eventId");
        String eventCode = eventDetailInfo.getString("eventCode");
        
        EventDetailView webview = new EventDetailView(this);
        
        webview.getSettings().setJavaScriptEnabled(true);
        webview.addJavascriptInterface(new JavaScriptInterface(this), "nativeSide");
        
        setContentView(webview);
        
        webview.loadUrl("file:///android_asset/www/index.html?id="+ eventId + "&code=" + eventCode);               
    }
	
	@Override
	public void onBackPressed(){
		super.onBackPressed();
		
		finish();
	}
}
