package com.splyze;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class UserProfileDataAdapter extends ArrayAdapter<String[]> {

	public UserProfileDataAdapter(Context context, int layout,
			int textViewResourceId, String[][] objects) {
		super(context, layout, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View row = convertView;
		
		if( row == null ){
			row = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.myprofile, parent, false);
		}
		
		String[] item = getItem(position);
		
		TextView attribute = (TextView)row.findViewById(R.id.attribute);
		TextView value = (TextView)row.findViewById(R.id.value);
			
		attribute.setText(item[0]);
		value.setText(item[1]);
		
		return row;
		
	}

}
