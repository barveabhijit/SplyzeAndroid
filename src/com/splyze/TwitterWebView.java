package com.splyze;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.RequestToken;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.view.Window;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TwitterWebView extends WebView {
	
	private final String apiKey = "7aW4c2W8ANkOVyJ1fayIw";
	private final String secretKey = "jkhzfr4sL7zfyVoWMC8pScE0nbYYx5C7e5zl7wwM";
	
	private Twitter twitter = null;
	private RequestToken requestToken;
	
	private String callBackURL = "x-oauth-splyze://localhost/oauth_callback";
		
	private String authURL = null;
		
	private ProgressDialog mSpinner;
	
	private WebViewClient twitterWebViewClient = new WebViewClient() {
		
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {			
			try {
				return ( ((TwitterWebView) view).processCallback(url));
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		
		@Override
		public void onReceivedSslError (WebView view, SslErrorHandler handler, SslError error){
//    		For API Level 8	    			
			super.onReceivedSslError(view, handler, error);
			
  			handler.proceed();
		}
		
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
	
	public TwitterWebView(Context context) {
		super(context);
				
        mSpinner = new ProgressDialog(context);
        mSpinner.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mSpinner.setMessage("Loading...");
        
		// TODO Auto-generated constructor stub		
		setWebViewClient(twitterWebViewClient);
		
		setHorizontalScrollBarEnabled(true);	
				
	}

	public void processLogin() throws TwitterException{
		
		twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(apiKey, secretKey);
		
		requestToken = twitter.getOAuthRequestToken(callBackURL);		
		authURL = requestToken.getAuthorizationURL();
			        		
        loadUrl(authURL);   
        
	}
	
	public boolean processCallback( String url ) throws TwitterException{
		if ( url.startsWith(getCallBackURL()) ){
			
			int oauth_verifier_index = url.indexOf("oauth_verifier=");
			
			if( oauth_verifier_index >= 0){
				String oauth_response = url.substring(oauth_verifier_index+1);
				String oauth_verifier = oauth_response.substring(oauth_response.indexOf('=')+1);
				
				twitter.getOAuthAccessToken(requestToken, oauth_verifier);

				User user = twitter.verifyCredentials();
												
				((TwitterLoginActivity) getContext()).TwitterUserProfile(Activity.RESULT_OK, user);				
			}			
			else
				((TwitterLoginActivity) getContext()).TwitterUserProfile(Activity.RESULT_CANCELED, null);								
			return true;
		}
		else
			return false;
	}


	public String getCallBackURL() {
		return callBackURL;
	}

	public void setCallBackURL(String callBackURL) {
		this.callBackURL = callBackURL;
	}

	
}
