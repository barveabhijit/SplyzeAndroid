package com.splyze;

import android.content.Context;
import android.widget.Toast;

public class JavaScriptInterface {
    Context mContext;

    String EventDetails = 
    		"<eventDetails>"+
    				"    <eventEndDate>2011-07-24</eventEndDate>"+
    				"    <eventEndTime>1700</eventEndTime>"+
    				"    <eventDescription>Comic-Con 2011</eventDescription>"+
    				"    <eventID>945629469</eventID>"+
    				"    <eventTrackList>"+
    				"        <endDate>2011-07-21</endDate>"+
    				"        <eventItemList>"+
    				"            <areaId>765270132</areaId>"+
    				"            <areaName>Main</areaName>"+
    				"            <endTime>1100</endTime>"+
    				"            <hotSpot>false</hotSpot>"+
    				"            <itemDescription> Planet of the Apes in Comics Present and Past</itemDescription>"+
    				"            <itemId>796210528</itemId>"+
    				"            <roomId>562544499</roomId>"+
    				"            <roomName>Room 5AB</roomName>"+
    				"            <speakerList>"+
    				"                <speakerFirstName>Ian</speakerFirstName>"+
    				"                <speakerId>679263272</speakerId>"+
    				"                <speakerLastName>Brill</speakerLastName>"+
    				"                <speakerProfile> Current Planet of the Apes comics editor </speakerProfile>"+
    				"            </speakerList>"+
    				"            <speakerList>"+
    				"                <speakerFirstName>Daryl</speakerFirstName>"+
    				"                <speakerId>679263272</speakerId>"+
    				"                <speakerLastName>Gregory</speakerLastName>"+
    				"                <speakerProfile> Current Planet of the Apes comics writer </speakerProfile>"+
    				"            </speakerList>"+
    				"            <speakerList>"+
    				"                <speakerFirstName>Tom</speakerFirstName>"+
    				"                <speakerId>679263272</speakerId>"+
    				"                <speakerLastName>Mason</speakerLastName>"+
    				"                <speakerProfile>Served as Malibu Comics' creative director and oversaw the last big Apes explosion.</speakerProfile>"+
    				"            </speakerList>"+
    				"            <startTime>1000</startTime>"+
    				"        </eventItemList>"+
    				"        <eventItemList>"+
    				"            <areaId>765270132</areaId>"+
    				"            <areaName>Main</areaName>"+
    				"            <endTime>1100</endTime>"+
    				"            <hotSpot>false</hotSpot>"+
    				"            <itemDescription>TheOneRing.net: Hobbit Talk</itemDescription>"+
    				"            <itemId>796210528</itemId>"+
    				"            <roomId>562544499</roomId>"+
    				"            <roomName>Room 7AB</roomName>"+
    				"            <speakerList>"+
    				"                <speakerFirstName>Clifford</speakerFirstName>"+
    				"                <speakerId>679263272</speakerId>"+
    				"                <speakerLastName>Broadway</speakerLastName>"+
    				"                <speakerProfile>(Quickbeam)</speakerProfile>"+
    				"            </speakerList>"+
    				"            <speakerList>"+
    				"                <speakerFirstName>Larry D.</speakerFirstName>"+
    				"                <speakerId>679263272</speakerId>"+
    				"                <speakerLastName>Curtis</speakerLastName>"+
    				"                <speakerProfile>(MrCere)</speakerProfile>"+
    				"            </speakerList>"+
    				"            <startTime>1000</startTime>"+
    				"        </eventItemList>"+
    				"        <numItems>0</numItems>"+
    				"        <startDate>2011-07-21</startDate>"+
    				"        <trackId>986010963</trackId>"+
    				"        <trackName>Thursday</trackName>"+
    				"    </eventTrackList>"+
    				"    <eventTrackList>"+
    				"        <endDate>2011-07-22</endDate>"+
    				"        <eventItemList>"+
    				"            <areaId>765270132</areaId>"+
    				"            <areaName>Main</areaName>"+
    				"            <endTime>1020</endTime>"+
    				"            <hotSpot>false</hotSpot>"+
    				"            <itemDescription>ComicsPRO Fresh Start</itemDescription>"+
    				"            <itemId>796210528</itemId>"+
    				"            <roomId>562544499</roomId>"+
    				"            <roomName>Room 19</roomName>"+
    				"            <speakerList>"+
    				"                <speakerFirstName></speakerFirstName>"+
    				"                <speakerId>679263272</speakerId>"+
    				"                <speakerLastName></speakerLastName>"+
    				"                <speakerProfile></speakerProfile>"+
    				"            </speakerList>"+
    				"            <startTime>0930</startTime>"+
    				"        </eventItemList>"+
    				"        <eventItemList>"+
    				"            <areaId>765270132</areaId>"+
    				"            <areaName>Main</areaName>"+
    				"            <endTime>1100</endTime>"+
    				"            <hotSpot>false</hotSpot>"+
    				"            <itemDescription> DC: Talent Search</itemDescription>"+
    				"            <itemId>796210528</itemId>"+
    				"            <roomId>562544499</roomId>"+
    				"            <roomName>Room 4</roomName>"+
    				"            <speakerList>"+
    				"                <speakerFirstName>Mark</speakerFirstName>"+
    				"                <speakerId>679263272</speakerId>"+
    				"                <speakerLastName>Chiarello</speakerLastName>"+
    				"                <speakerProfile>presents an informative orientation session to explain how DC's Talent Search works and discuss the different needs of DC Comics, Vertigo, and MAD magazine</speakerProfile>"+
    				"            </speakerList>"+
    				"            <startTime>1000</startTime>"+
    				"        </eventItemList>"+
    				"        <numItems>0</numItems>"+
    				"        <startDate>2011-07-22</startDate>"+
    				"        <trackId>986010963</trackId>"+
    				"        <trackName>Friday</trackName>"+
    				"    </eventTrackList>"+
    				"    <eventTrackList>"+
    				"        <endDate>2011-07-23</endDate>"+
    				"        <eventItemList>"+
    				"            <areaId>765270132</areaId>"+
    				"            <areaName>Main</areaName>"+
    				"            <endTime>1100</endTime>"+
    				"            <hotSpot>false</hotSpot>"+
    				"            <itemDescription> DC: Talent Search</itemDescription>"+
    				"            <itemId>796210528</itemId>"+
    				"            <roomId>562544499</roomId>"+
    				"            <roomName>Room 4</roomName>"+
    				"            <speakerList>"+
    				"                <speakerFirstName>Mark</speakerFirstName>"+
    				"                <speakerId>679263272</speakerId>"+
    				"                <speakerLastName>Chiarello</speakerLastName>"+
    				"                <speakerProfile>presents an informative orientation session to explain how DC's Talent Search works and discuss the different needs of DC Comics, Vertigo, and MAD magazine</speakerProfile>"+
    				"            </speakerList>"+
    				"            <startTime>1000</startTime>"+
    				"        </eventItemList>"+
    				"        <numItems>0</numItems>"+
    				"        <startDate>2011-07-23</startDate>"+
    				"        <trackId>986010963</trackId>"+
    				"        <trackName>Saturday</trackName>"+
    				"    </eventTrackList>"+
    				"    <eventTrackList>"+
    				"        <endDate>2011-07-24</endDate>"+
    				"        <eventItemList>"+
    				"            <areaId>780499020</areaId>"+
    				"            <areaName>Main</areaName>"+
    				"            <endTime>1100</endTime>"+
    				"            <hotSpot>true</hotSpot>"+
    				"            <itemDescription>Archaia Entertainment and The Jim Henson Company: The Early Works of Jim Henson Screening with Special Tale of Sand Discussion</itemDescription>"+
    				"            <itemId>1213673112</itemId>"+
    				"            <roomId>1279236598</roomId>"+
    				"            <roomName>Room 4</roomName>"+
    				"            <speakerList>"+
    				"                <speakerFirstName>Karen</speakerFirstName>"+
    				"                <speakerId>1609373970</speakerId>"+
    				"                <speakerLastName>Falk</speakerLastName>"+
    				"                <speakerProfile>Henson archivist</speakerProfile>"+
    				"            </speakerList>"+
    				"            <speakerList>"+
    				"                <speakerFirstName>Stephen</speakerFirstName>"+
    				"                <speakerId>1609373970</speakerId>"+
    				"                <speakerLastName>Christy</speakerLastName>"+
    				"                <speakerProfile>Archaia editor-in-chief</speakerProfile>"+
    				"            </speakerList>"+
    				"            <speakerList>"+
    				"                <speakerFirstName>Ramon</speakerFirstName>"+
    				"                <speakerId>1609373970</speakerId>"+
    				"                <speakerLastName>Perez</speakerLastName>"+
    				"                <speakerProfile>A Tale of Sand artist</speakerProfile>"+
    				"            </speakerList>"+
    				"            <speakerList>"+
    				"                <speakerFirstName>P. J.</speakerFirstName>"+
    				"                <speakerId>1609373970</speakerId>"+
    				"                <speakerLastName>Bickett</speakerLastName>"+
    				"                <speakerProfile>Archaia CEO and Moderator</speakerProfile>"+
    				"            </speakerList>"+
    				"            <startTime>1000</startTime>"+
    				"        </eventItemList>"+
    				"        <numItems>0</numItems>"+
    				"        <startDate>2011-07-24</startDate>"+
    				"        <trackId>986010963</trackId>"+
    				"        <trackName>Sunday</trackName>"+
    				"    </eventTrackList>"+
    				"    <locationAddressLine1>111 W. Harbor Drive</locationAddressLine1>"+
    				"    <locationCity>San Diego</locationCity>"+
    				"    <locationCountry>USA</locationCountry>"+
    				"    <locationId>491314143</locationId>"+
    				"    <locationName>San Diego Convention Center</locationName>"+
    				"    <locationState>CA</locationState>"+
    				"    <numTracks>1</numTracks>"+
    				"    <eventStartDate>2011-07-21</eventStartDate>"+
    				"    <eventStartTime>1000</eventStartTime>"+
    				"</eventDetails>";

    
    /** Instantiate the interface and set the context */
    JavaScriptInterface(Context c) {
        mContext = c;
    }
    
    public String getEventDetails(){
    	return "EventDetails";
    }
    
    public void showEventDetails(){
    	Toast.makeText(mContext, EventDetails, Toast.LENGTH_LONG).show();
    }

}
