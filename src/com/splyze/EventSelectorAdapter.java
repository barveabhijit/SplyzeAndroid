package com.splyze;

import java.util.ArrayList;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class EventSelectorAdapter extends ArrayAdapter<EventBriefInfo> implements SpinnerAdapter {
	
	private Context context;
	
	public EventSelectorAdapter( Context context, ArrayList<EventBriefInfo> eventInfoList )
	{
		super(context, android.R.layout.simple_spinner_item, eventInfoList);		
		this.context = context;
		
		this.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	}
	
	public int getCount() {
		// TODO Auto-generated method stub
		return super.getCount();
	}

	public EventBriefInfo getItem(int position) {
		// TODO Auto-generated method stub
		return super.getItem(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return getItem(position).getEventID();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		if( convertView == null )
			convertView = (TextView) new TextView(context);
		
		((TextView) convertView).setTextColor(Color.BLACK);
		((TextView) convertView).setText(getItem(position).getEventName());
		
		return convertView;
	}

	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return super.getItemViewType(position);
	}

	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return super.getViewTypeCount();
	}

	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return super.hasStableIds();
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (getCount() == 0);
	}

	public void registerDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub
		super.registerDataSetObserver(observer);
	}

	public void unregisterDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub
		unregisterDataSetObserver(observer);
	}

	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if( convertView == null )
			convertView = (TextView) new TextView(context);
		
		((TextView) convertView).setTextColor(Color.BLACK);
		((TextView) convertView).setText(getItem(position).getEventName());
		
		return convertView;
	}
}
