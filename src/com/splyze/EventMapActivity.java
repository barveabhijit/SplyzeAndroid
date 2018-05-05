package com.splyze;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.os.Bundle;
import android.widget.TextView;

public class EventMapActivity extends MapActivity {
	
	private MapView map;
	private MapController controller;
	private String eventAddress = "515 Arbor Creek Ct, Roswell GA 30076";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                        
        setContentView(R.layout.map);
        
        TextView eventAddressView = (TextView)findViewById(R.id.event_address);
        eventAddressView.setText(eventAddress);
        
        map = (MapView)findViewById(R.id.map);
        controller = map.getController();
                
		try {
	        GeoPoint mapCenter;
			mapCenter = getGeoPointFromAddress(eventAddress);
			controller.setCenter(mapCenter);
			controller.setZoom(18);
			
	 		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
   }

	private GeoPoint getGeoPointFromAddress(String address) throws JSONException, IOException {
		
		GeoPoint geoPoint = null;
		
		HttpGet httpGet = new HttpGet("http://maps.google.com/maps/api/geocode/json?address=" + 
										address.replaceAll(" ", "%20") +
										"ka&sensor=false");
		HttpClient client = new DefaultHttpClient();
		HttpResponse response;
		StringBuilder stringBuilder = new StringBuilder();
		
		try {
			response = client.execute(httpGet);
			HttpEntity entity = response.getEntity();
			InputStream stream = entity.getContent();
			int b;
			while ((b=stream.read()) != -1) {
				stringBuilder.append((char)b);
			}
		}
		catch (ClientProtocolException e) {	
			throw e;
		}
		catch (IOException e) {			
			throw e;
		}
		
		JSONObject jsonObject = new JSONObject();
		
		try{
			jsonObject = new JSONObject(stringBuilder.toString());
			
			Double lon = new Double(0);
			Double lat = new Double(0);
			
			try{
				lon = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lng");
				lat = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lat");
				
				geoPoint = new GeoPoint((int)(lat*1E6), (int) (lon*1E6));
			}
			catch (JSONException e){
				throw e;
			}
		}
		catch (JSONException e){
			throw e;
		}
		
		return geoPoint;
	}

	@Override
	public void onBackPressed(){
		super.onBackPressed();
		
		finish();
	}  
	
	@Override
	protected boolean isRouteDisplayed(){
		return false;		
	}

}
