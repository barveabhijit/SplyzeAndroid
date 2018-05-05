package com.splyze;

import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.client.LinkedInApiClientFactory;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthService;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthServiceFactory;
import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;
import com.google.code.linkedinapi.schema.Person;
import com.splyze.LinkedInLoginActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.view.Window;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LinkedInWebView extends WebView {
	
	private String linkedinApiKey = "77sfSq9K3JtNCIW8GHajgfPLwvyw9PDbmOoxXQngWcsgE2L0yfYoZkLPqNQbZBgQ";
	private String linkedinSecretKey = "OjKK2OHoPZhlo7mOHIrHWsarnmY1ttNo7ncIN6Kmlpe0eD0cvyqoFCLTW6H1yc_k";
	
	private String callBackURL = "x-oauth-splyze://localhost/oauth_callback";
	
	private String authURL = null;
	
	private LinkedInRequestToken requestToken = null;
	
	private LinkedInOAuthService oauthService = null;
		
	private LinkedInApiClientFactory baseFactory = null;
	
	private ProgressDialog mSpinner;
	
	private WebViewClient linkedInWebViewClient = new WebViewClient() {
		
		public boolean shouldOverrideUrlLoading(WebView view, String url) {			
			return ( ((LinkedInWebView) view).processCallback(url));
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
	
	public LinkedInWebView(Context context) {
		super(context);
				
        mSpinner = new ProgressDialog(context);
        mSpinner.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mSpinner.setMessage("Loading...");
        
		// TODO Auto-generated constructor stub		
		setWebViewClient(linkedInWebViewClient);
		
		setHorizontalScrollBarEnabled(true);	
				
	}

	public void processLinkedInLogin(){
		
		final LinkedInApiClientFactory factory = LinkedInApiClientFactory.newInstance(linkedinApiKey, linkedinSecretKey);		
		final LinkedInOAuthService oauthService = LinkedInOAuthServiceFactory.getInstance().createLinkedInOAuthService(linkedinApiKey, linkedinSecretKey);
        
		setBaseFactory(factory);
		setOauthService(oauthService);
		
		requestToken = getOauthService().getOAuthRequestToken(getCallBackURL());
		authURL = requestToken.getAuthorizationUrl();
		        		
        loadUrl(authURL);   
        
	}
	
	public boolean processCallback( String url ){
		if ( url.startsWith(getCallBackURL()) ){
			
			int oauth_verifier_index = url.indexOf("oauth_verifier=");
			
			if( oauth_verifier_index >= 0){
				String oauth_response = url.substring(oauth_verifier_index+1);
				String oauth_verifier = oauth_response.substring(oauth_response.indexOf('=')+1);
				
				LinkedInAccessToken accessToken = getOauthService().getOAuthAccessToken(getRequestToken(), oauth_verifier);
				final LinkedInApiClient client = getBaseFactory().createLinkedInApiClient(accessToken);
				
				Person profile = client.getProfileForCurrentUser();
								
				((LinkedInLoginActivity) getContext()).linkedInUserProfile(Activity.RESULT_OK, profile);				
			}			
			else
				((LinkedInLoginActivity) getContext()).linkedInUserProfile(Activity.RESULT_CANCELED, null);								
			return true;
		}
		else
			return false;
	}

	public LinkedInRequestToken getRequestToken() {
		return requestToken;
	}

	public void setRequestToken(LinkedInRequestToken requestToken) {
		this.requestToken = requestToken;
	}

	public String getCallBackURL() {
		return callBackURL;
	}

	public void setCallBackURL(String callBackURL) {
		this.callBackURL = callBackURL;
	}

	public LinkedInOAuthService getOauthService() {
		return oauthService;
	}

	public void setOauthService(LinkedInOAuthService oauthService) {
		this.oauthService = oauthService;
	}

	public LinkedInApiClientFactory getBaseFactory() {
		return baseFactory;
	}

	public void setBaseFactory(LinkedInApiClientFactory baseFactory) {
		this.baseFactory = baseFactory;
	}
	
}
