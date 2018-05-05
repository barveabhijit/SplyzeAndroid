package com.splyze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EventSelectorActivity extends Activity {

	private int selectedEventId;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                        
        setContentView(R.layout.eventselector);
        
        //get event list
        ArrayList<EventBriefInfo> eventList = null;
		try {
			eventList = getEventList();
			
			Spinner eventListSelector = (Spinner) findViewById(R.id.eventSelector);
			
			EventSelectorAdapter eventSelectorAdapter = new EventSelectorAdapter( this,  getEventList());
			eventListSelector.setAdapter(eventSelectorAdapter);
			
			eventListSelector.setOnItemSelectedListener( new OnItemSelectedListener() {

				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					selectedEventId = ((EventBriefInfo) parent.getItemAtPosition(position)).getEventID();					
				}

				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			
//			StringBuilder eventNamesBuilder = new StringBuilder();
			//build eventname list
//			for (int i = 0; i < eventList.size(); i++){
//				eventNamesBuilder.append(((EventBriefInfo)eventList.get(i)).getEventName() + "\n");
//			}
//			Toast.makeText(getApplicationContext(), eventNamesBuilder.toString(), Toast.LENGTH_LONG).show();
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private ArrayList<EventBriefInfo> getEventList() throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
    	String envelope = 	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
    						"<env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"http://SplyzeServices/\">" +
    							"<env:Header/>" +
    							"<env:Body>" +
    								"<ns1:returnEventList/>" +
    							"</env:Body>" +
    						"</env:Envelope>";
    	
    	String url = "http://192.168.1.66:7101/Project1/SplyzeCloudPort";
    	String SOAPAction = "";
    			
    	String wsResponse = callWebService(envelope, url, SOAPAction);
    	
    	return parseWSResponse(wsResponse);
	}

	private ArrayList<EventBriefInfo> parseWSResponse(String wsResponse) throws ParserConfigurationException, SAXException, IOException {
		
		if( wsResponse != null){
			BufferedReader br = new BufferedReader(new StringReader(wsResponse));
			InputSource is = new InputSource(br);
			EventListParser eventParser = new EventListParser();
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser sp = factory.newSAXParser();
			XMLReader reader = sp.getXMLReader();
			reader.setContentHandler(eventParser);
			reader.parse(is);
			ArrayList<EventBriefInfo> eventList = eventParser.getEventList();
			
			return eventList;
		}
		return null;
	}

	private String callWebService(String envelope, String url, String soapAction) {
		final DefaultHttpClient httpClient=new DefaultHttpClient();
		  
		// request parameters		  
	    HttpParams params = httpClient.getParams();
	    HttpConnectionParams.setConnectionTimeout(params, 10000);
	    HttpConnectionParams.setSoTimeout(params, 15000);
		  
		// set parameter
		HttpProtocolParams.setUseExpectContinue(httpClient.getParams(), true);
		  
	    // POST the envelope		  
	    HttpPost httppost = new HttpPost(url);
		  
	    // add headers
	    httppost.setHeader("SOAPAction", soapAction);  
	    httppost.setHeader("Content-Type", "text/xml; charset=UTF-8");
		  
	    String responseString="";
		  
	    try {
	    	// the entity holds the request
	    	HttpEntity entity = new StringEntity(envelope);
	    	httppost.setEntity(entity);
		  
	    	// Response handler
	    	ResponseHandler<String> rh=new ResponseHandler<String>() {
		  
		    	// invoked when client receives response
		    	public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
			  
			       // get response entity
			       HttpEntity entity = response.getEntity();
			  
			       // read the response as byte array
			       StringBuffer out = new StringBuffer();
			  
			       byte[] b = EntityUtils.toByteArray(entity);
			  
			       // write the response byte array to a string buffer
			       out.append(new String(b, 0, b.length));       
			  
			       return out.toString();			  
		    	}
	    	};
		  
	    responseString=httpClient.execute(httppost, rh);	  
	    }
		  
	    catch (Exception e) {		  
	    	Log.v("exception", e.toString());		  
	    }
		  
	    // close the connection 
	    httpClient.getConnectionManager().shutdown();
	    return responseString;
	}

	public void processGo(View view){
		EditText eventCodeField = (EditText) findViewById(R.id.eventCode);
		CharSequence eventCode = eventCodeField.getText();
		
		Bundle eventDetailInfo = new Bundle();
		eventDetailInfo.putString("eventId", Integer.toString(selectedEventId));
		eventDetailInfo.putString("eventCode", eventCode.toString());
		
		Intent eventDetailIntent = new Intent(this, EventDetailActivity.class);
		eventDetailIntent.putExtras(eventDetailInfo);
		startActivity(eventDetailIntent);
    }
    
	@Override
	public void onBackPressed(){
		super.onBackPressed();
		
		setResult(RESULT_OK);
		finish();
	}

}
