package com.splyze;

import java.io.Serializable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import twitter4j.User;

import com.facebook.android.FacebookError;
import com.facebook.android.Util;
import com.google.code.linkedinapi.schema.Person;

public class SplyzeUserProfile extends Object implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5390441629695833483L;

	private String profileSource = null;
	private String facebookProfile = null;
	private Person linkedInProfile = null;
	private User twitterProfile = null;
	
	private final int ATTRIBUTE_INDEX = 0;
	private final int VALUE_INDEX = 1;
	
	private final char UNDERSCORE = '_';
	private final char SPACE = ' ';
	
	private final String PROFILE_SOURCE = "PROFILE SOURCE";
	private final String FIRST_NAME = "FIRST NAME";
	private final String LAST_NAME = "LAST NAME";
	private final String HEADLINE = "HEADLINE";
	private final String LINKEDIN_LOCATION = "LOCATION";
	private final String INDUSTRY = "INDUSTRY";
	private final String TOTAL_CONNECTIONS = "TOTAL CONNECTIONS";
	private final String CURRENT_STATUS = "CURRENT STATUS";
	private final String DISTANCE = "DISTANCE";
	private final String CURRENT_STATUS_TS = "CURRENT STATUS LAST UPDATED";
	private final String NUM_RECOMMENDERS = "NUMBER OF RECOMMENDERS";
	private final String SUMMARY = "SUMMARY";
	private final String INTERESTS = "INTERESTS";
	private final String ASSOCIATIONS = "ASSOCIATIONS";
	private final String HONORS = "HONORS";
	private final String SPECIALTIES = "SPECIALTIES";
	private final String NUM_POSITIONS = "NUMBER OF POSITIONS";
	private final String NUM_EDUCATIONS = "NUMBER OF EDUCATIONS";
	private final String PICTURE_URL = "PICTURE URL";
	private final String NUM_RECS_GIVEN = "NUMBER OF RECOMMENDATIONS GIVEN";
	private final String NUM_RECS_RECEIVED = "NUMBER OF RECOMMENDATIONS RECEIVED";
	private final String TOTAL_MEM_GROUPS = "TOTAL MEMBER GROUPS";
	private final String TOTAL_PERSON_ACTIVITIES = "TOTAL ACTIVITIES";

	private final String NAME = "NAME";
	private final String SCREEN_NAME = "SCREEN NAME";
	private final String TWITTER_LOCATION = "LOCATION";
	private final String DESCRIPTION = "DESCRIPTION";
	private final String CONTRIBUTORS_ENABLED = "CONTRIBUTORS ENABLED";
	private final String PROFILE_IMAGE_URL = "PROFILE IMAGE URL";
	private final String PROFILE_IMAGE_URL_HTTPS = "PROFILE IMAGE URL- HTTPS";
	private final String URL = "URL";
	private final String USER_STATUS_PROTECTED = "USER STATUS PROTECTED";
	private final String FOLLOWERS_COUNT = "FOLLOWERS COUNT";
	private final String STATUS = "STATUS";
	private final String PROFILE_BKG_COLOR = "PROFILE BACKGROUND COLOR";
	private final String PROFILE_TEXT_COLOR = "PROFILE TEXT COLOR";
	private final String PROFILE_LINK_COLOR = "PROFILE LINK COLOR";
	private final String PROFILE_SIDEBAR_FILL_COLOR = "PROFILE SIDEBAR FILL COLOR";
	private final String PROFILE_SIDEBAR_BORDER_COLOR = "PROFILE SIDEBAR BORDER COLOR";
	private final String PROFILE_USE_BKG_IMAGE = "PROFILE USING BACKGROUND IMAGE";
	private final String SHOW_ALL_INLINE_MEDIA = "SHOW ALL INLINE MEDIA";
	private final String FRIENDS_COUNT = "FRIENDS COUNT";
	private final String CREATED_AT = "CREATED AT";
	private final String FAVORITES_COUNT = "FAVORITES COUNT";
	private final String UTC_OFFSET = "UTC_OFFSET";
	private final String TIME_ZONE = "TIME ZONE";
	private final String PROFILE_BKG_IMAGE_URL = "PROFILE BACKGROUND IMAGE URL";
	private final String PROFILE_BKG_IMAGE_URL_HTTPS = "PROFILE BACKGROUND IMAGE URL HTTPS";
	private final String PROFILE_BKG_TILED = "PROFILE BACKGROUND TILED";
	private final String LANGUAGE = "LANGUAGE";
	private final String STATUSES_COUNT = "STATUSES COUNT";
	private final String GEO_ENABLED = "GEO ENABLED";
	private final String VERIFIED = "VERIFIED";
	private final String TRANSLATOR = "TRANSLATOR";
	private final String PUBLIC_LISTS_LISTED_ON = "PUBLIC LISTS LISTED ON";
	
	private String[][] profileInfo = null;
	
	public String getProfileSource() {
		return profileSource;
	}
	public void setProfileSource(String profileSource) {
		this.profileSource = profileSource;
	}
	public String getFacebookProfile() {
		return facebookProfile;
	}
	public void setFacebookProfile(String facebookProfile) {
		this.facebookProfile = facebookProfile;
	}
	public Person getLinkedInProfile() {
		return linkedInProfile;
	}
	public void setLinkedInProfile(Person linkedInProfile) {
		this.linkedInProfile = linkedInProfile;
	}
	
	public void resetProfile(){
		profileSource = null;
		facebookProfile = null;
		linkedInProfile = null;
		twitterProfile = null;
		profileInfo = null;
	}
	
	public User getTwitterProfile(){
		return twitterProfile;
	}
	
	public void setTwitterProfile(User twitterUser) {
		// TODO Auto-generated method stub
		this.twitterProfile = twitterUser;

	}	
	
	public String[][] populateProfileInfo() throws JSONException, FacebookError{
			
	
		if (profileSource.equals("Facebook")){
			return populateFacebookProfile();
		}
		
		if (profileSource.equals("LinkedIn")){
			return populateLinkedInProfile();
		}
		
		if( profileSource.equals("Twitter")){
			return populateTwitterProfile();
		}
		
		return profileInfo;
		
	}
	
	private String[][] populateTwitterProfile() {
		profileInfo = new String[determineTwitterValidRows()+1][2];
		
		int index = 0;
		index = addProfileInfo(index, PROFILE_SOURCE, getProfileSource());
		index = addProfileInfo(index, NAME, twitterProfile.getName());
		index = addProfileInfo(index, SCREEN_NAME, twitterProfile.getScreenName());
		index = addProfileInfo(index, TWITTER_LOCATION, twitterProfile.getLocation());
		index = addProfileInfo(index, DESCRIPTION, twitterProfile.getDescription());
		index = addProfileInfo(index, CONTRIBUTORS_ENABLED, (twitterProfile.isContributorsEnabled() ? "YES" : "NO"));
		index = addProfileInfo(index, PROFILE_IMAGE_URL, 
				(twitterProfile.getProfileImageURL() != null ? twitterProfile.getProfileImageURL().toString() : null ));
		index = addProfileInfo(index, PROFILE_IMAGE_URL_HTTPS, 
				(twitterProfile.getProfileImageUrlHttps() != null ? twitterProfile.getProfileImageUrlHttps().toString() : null));
		index = addProfileInfo(index, URL, 
				(twitterProfile.getURL() != null ? twitterProfile.getURL().toString() : null));
		index = addProfileInfo(index, USER_STATUS_PROTECTED, (twitterProfile.isProtected() ? "YES" : "NO"));
		index = addProfileInfo(index, FOLLOWERS_COUNT, String.valueOf(twitterProfile.getFollowersCount()));
		index = addProfileInfo(index, STATUS, 
				(twitterProfile.getStatus() != null ? twitterProfile.getStatus().toString() : null ));
		index = addProfileInfo(index, PROFILE_BKG_COLOR, twitterProfile.getProfileBackgroundColor());
		index = addProfileInfo(index, PROFILE_TEXT_COLOR, twitterProfile.getProfileTextColor());
		index = addProfileInfo(index, PROFILE_LINK_COLOR, twitterProfile.getProfileLinkColor());
		index = addProfileInfo(index, PROFILE_SIDEBAR_FILL_COLOR, twitterProfile.getProfileSidebarFillColor());
		index = addProfileInfo(index, PROFILE_SIDEBAR_BORDER_COLOR, twitterProfile.getProfileSidebarBorderColor());
		index = addProfileInfo(index, PROFILE_USE_BKG_IMAGE, (twitterProfile.isProfileUseBackgroundImage() ? "YES" : "NO"));
		index = addProfileInfo(index, SHOW_ALL_INLINE_MEDIA, (twitterProfile.isShowAllInlineMedia() ? "YES" : "NO"));
		index = addProfileInfo(index, FRIENDS_COUNT, String.valueOf(twitterProfile.getFriendsCount()));
		index = addProfileInfo(index, CREATED_AT, 
				(twitterProfile.getCreatedAt()!= null ? twitterProfile.getCreatedAt().toString() : null ));
		index = addProfileInfo(index, FAVORITES_COUNT, String.valueOf(twitterProfile.getFavouritesCount()));
		index = addProfileInfo(index, UTC_OFFSET, String.valueOf(twitterProfile.getUtcOffset()));
		index = addProfileInfo(index, TIME_ZONE, twitterProfile.getTimeZone());
		index = addProfileInfo(index, PROFILE_BKG_IMAGE_URL, twitterProfile.getProfileBackgroundImageUrl());
		index = addProfileInfo(index, PROFILE_BKG_IMAGE_URL_HTTPS, twitterProfile.getProfileBackgroundImageUrlHttps());
		index = addProfileInfo(index, PROFILE_BKG_TILED, (twitterProfile.isProfileBackgroundTiled() ? "YES" : "NO"));
		index = addProfileInfo(index, LANGUAGE, twitterProfile.getLang());
		index = addProfileInfo(index, STATUSES_COUNT, String.valueOf(twitterProfile.getStatusesCount()));
		index = addProfileInfo(index, GEO_ENABLED, (twitterProfile.isGeoEnabled() ? "YES" : "NO"));
		index = addProfileInfo(index, VERIFIED, (twitterProfile.isVerified() ? "YES" : "NO"));
		index = addProfileInfo(index, TRANSLATOR, (twitterProfile.isTranslator() ? "YES" : "NO"));
		index = addProfileInfo(index, PUBLIC_LISTS_LISTED_ON, String.valueOf(twitterProfile.getListedCount()));

		return profileInfo;
	}
	
	private int determineTwitterValidRows() {
		// TODO Auto-generated method stub
		int nTotalRows = 0;
		
		if (twitterProfile.getName() != null)
			nTotalRows++;
		
		if( twitterProfile.getScreenName() != null)
			nTotalRows++;
		
		if( twitterProfile.getLocation() != null)
			nTotalRows++;
		
		if( twitterProfile.getDescription() != null)
			nTotalRows++;
		
		if( twitterProfile.getScreenName() != null)
			nTotalRows++;
		
		//Contributors enabled is boolean so never null
		nTotalRows++;
		
		if( twitterProfile.getProfileImageURL() != null)
			nTotalRows++;
		
		if( twitterProfile.getProfileImageUrlHttps() != null)
			nTotalRows++;
		
		if( twitterProfile.getURL() != null)
			nTotalRows++;
		
		if( twitterProfile.getScreenName() != null)
			nTotalRows++;
		
		//Protected status is boolean so never null
		nTotalRows++;
		
		//Followers Count is int so never null
		nTotalRows++;
		
		if( twitterProfile.getStatus() != null)
			nTotalRows++;
		
		if( twitterProfile.getProfileBackgroundColor() != null)
			nTotalRows++;
		
		if( twitterProfile.getProfileTextColor() != null)
			nTotalRows++;
		
		if( twitterProfile.getProfileLinkColor() != null)
			nTotalRows++;
		
		if( twitterProfile.getProfileSidebarFillColor()!= null)
			nTotalRows++;
		
		if( twitterProfile.getProfileSidebarBorderColor() != null)
			nTotalRows++;
		
		//Profile using background image is boolean so never null
		nTotalRows++;
		
		//Show all inline media is boolean so never null
		nTotalRows++;
		
		//Friends count is int so never null
		nTotalRows++;
		
		if( twitterProfile.getCreatedAt() != null)
			nTotalRows++;
		
		//Favorites count is int so never null
		nTotalRows++;
		
		// UTC Offset is int so never null
		nTotalRows++;
		
		if( twitterProfile.getProfileBackgroundImageUrl() != null)
			nTotalRows++;
		
		if( twitterProfile.getProfileBackgroundImageUrlHttps() != null)
			nTotalRows++;
		
		// Background tiled is boolean so never null
		nTotalRows++;

		if( twitterProfile.getLang() != null)
			nTotalRows++;
		
		//Statuses count is int so never null
		nTotalRows++;
		
		//Geo enabled is boolean so never null
		nTotalRows++;
		
		//Verified is boolean so never null
		nTotalRows++;
		
		//Translator status is boolean so never null
		nTotalRows++;
		
		if( twitterProfile.getListedCount() > 0)
			nTotalRows++;
		
		return nTotalRows;
	}
	
	
	private String[][] populateLinkedInProfile() {
		// TODO Auto-generated method stub
	
		profileInfo = new String[determineLinkedInValidRows()+1][2];
		
		int index = 0;
		index = addProfileInfo(index, PROFILE_SOURCE, getProfileSource());
		index = addProfileInfo(index, FIRST_NAME, linkedInProfile.getFirstName());
		index = addProfileInfo(index, LAST_NAME, linkedInProfile.getLastName());
		index = addProfileInfo(index, HEADLINE, linkedInProfile.getHeadline());
		index = addProfileInfo(index, LINKEDIN_LOCATION, 
					(linkedInProfile.getLocation() != null ? linkedInProfile.getLocation().toString() : null));
		index = addProfileInfo(index, INDUSTRY, linkedInProfile.getIndustry());
		index = addProfileInfo(index, TOTAL_CONNECTIONS, 
					(linkedInProfile.getConnections() != null ? linkedInProfile.getConnections().getTotal().toString() : null));
		index = addProfileInfo(index, CURRENT_STATUS, linkedInProfile.getCurrentStatus());
		index = addProfileInfo(index, DISTANCE, 
					(linkedInProfile.getDistance() != null ? linkedInProfile.getDistance().toString() : null));
		index = addProfileInfo(index, CURRENT_STATUS_TS, 
					(linkedInProfile.getCurrentStatusTimestamp() != null ? linkedInProfile.getCurrentStatusTimestamp().toString() : null));
		index = addProfileInfo(index, NUM_RECOMMENDERS, 
					(linkedInProfile.getNumRecommenders() != null ? linkedInProfile.getNumRecommenders().toString() : null));
		index = addProfileInfo(index, SUMMARY, linkedInProfile.getSummary());
		index = addProfileInfo(index, INTERESTS, linkedInProfile.getInterests());
		index = addProfileInfo(index, ASSOCIATIONS, linkedInProfile.getAssociations());
		index = addProfileInfo(index, HONORS, linkedInProfile.getHonors());
		index = addProfileInfo(index, SPECIALTIES, linkedInProfile.getSpecialties());
		index = addProfileInfo(index, NUM_POSITIONS, 
					(linkedInProfile.getPositions() != null ? linkedInProfile.getPositions().getTotal().toString() : null));
		index = addProfileInfo(index, NUM_EDUCATIONS, 
					(linkedInProfile.getEducations() != null ? linkedInProfile.getEducations().getTotal().toString() : null));
		index = addProfileInfo(index, PICTURE_URL, linkedInProfile.getPictureUrl());
		index = addProfileInfo(index, NUM_RECS_GIVEN, 
					(linkedInProfile.getRecommendationsGiven() != null ? linkedInProfile.getRecommendationsGiven().getTotal().toString() : null));
		index = addProfileInfo(index, NUM_RECS_RECEIVED, 
					(linkedInProfile.getRecommendationsReceived() != null ? linkedInProfile.getRecommendationsReceived().getTotal().toString() : null));
		index = addProfileInfo(index, TOTAL_MEM_GROUPS, 
					(linkedInProfile.getMemberGroups() != null ? linkedInProfile.getMemberGroups().getTotal().toString() : null));
		index = addProfileInfo(index, TOTAL_PERSON_ACTIVITIES,
					(linkedInProfile.getPersonActivities() != null ? linkedInProfile.getPersonActivities().getCount().toString() : null));
				
		
		return profileInfo;
	}
	
	private int addProfileInfo(int index, String attribute, String value){
		
		if( value != null ){
			profileInfo[index][ATTRIBUTE_INDEX] = attribute;
			profileInfo[index][VALUE_INDEX] = value;
			index++;
		}		
		return index;
	}
	
	private int determineLinkedInValidRows() {
		// TODO Auto-generated method stub
		int nTotalRows = 0;
		
		if(linkedInProfile.getFirstName() != null)
			nTotalRows ++;
		
		if(linkedInProfile.getLastName() != null)
			nTotalRows++;
		
		if(linkedInProfile.getHeadline() != null)
			nTotalRows++;
		
		if(linkedInProfile.getLocation() != null)
			if( linkedInProfile.getLocation().getCountry() != null)
				nTotalRows++;
		
		if(linkedInProfile.getIndustry() != null)
			nTotalRows++;
		
		if( linkedInProfile.getCurrentStatus() != null)
			nTotalRows++;
		
		if( linkedInProfile.getDistance() != null)
			nTotalRows++;

		if( linkedInProfile.getCurrentStatusTimestamp() != null)
			nTotalRows++;
		
		if( linkedInProfile.getNumRecommenders() != null)
			nTotalRows++;
		
		if( linkedInProfile.getSummary() != null)
			nTotalRows++;
		
		if( linkedInProfile.getInterests() != null)
			nTotalRows++;
		
		if( linkedInProfile.getAssociations() != null)
			nTotalRows++;

		if(linkedInProfile.getHonors() != null)
			nTotalRows++;
		
		if(linkedInProfile.getSpecialties() != null)
			nTotalRows++;
		
		if(linkedInProfile.getPositions() != null)
			nTotalRows++;
		
		if( linkedInProfile.getEducations() != null)
			nTotalRows++;
		
		if(linkedInProfile.getPictureUrl() != null)
			nTotalRows++;
		
		if( linkedInProfile.getRecommendationsGiven() != null)
			nTotalRows++;
		
		if(linkedInProfile.getRecommendationsReceived() != null)
			nTotalRows++;
		
		if(linkedInProfile.getMemberGroups() != null)
			nTotalRows++;
		
		if(linkedInProfile.getPersonActivities() != null)
			nTotalRows++;
		
		return nTotalRows;
	}
	private String[][] populateFacebookProfile() throws JSONException, FacebookError {
		// TODO Auto-generated method stub
		
		JSONObject facebookInfo = Util.parseJson(facebookProfile);

		JSONArray attributes = facebookInfo.names();
		
		profileInfo = new String[attributes.length()+1][2];
		
		profileInfo[0][ATTRIBUTE_INDEX] = PROFILE_SOURCE;
		profileInfo[0][VALUE_INDEX] = getProfileSource();
		
		for( int i = 0; i < attributes.length(); i++){
			String attributeName = attributes.getString(i);			
			profileInfo[i+1][ATTRIBUTE_INDEX] = attributeName.replace(UNDERSCORE, SPACE).toUpperCase();
			profileInfo[i+1][VALUE_INDEX] = facebookInfo.getString(attributeName);
		}
		
		return profileInfo;
	}
}
