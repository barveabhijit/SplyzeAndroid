package com.splyze;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class EventDetailView extends WebView {
	
	private ProgressDialog mSpinner;
	
	private WebViewClient eventDetailWebViewClient = new WebViewClient() {
		@Override
		public void onPageStarted (WebView view, String url, Bitmap favicon){
			super.onPageStarted(view, url, favicon);
			
			mSpinner.show();
		}
		
		@Override 
		public void onPageFinished (WebView view, String url){
			super.onPageFinished(view, url);
			mSpinner.dismiss();
		}
	};

	public EventDetailView(Context context) {
		super(context);
		
        mSpinner = new ProgressDialog(context);
        mSpinner.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mSpinner.setMessage("Loading...");
        
		// TODO Auto-generated constructor stub		
		setWebViewClient(eventDetailWebViewClient);
		
		setHorizontalScrollBarEnabled(true);	
		
	}

}
