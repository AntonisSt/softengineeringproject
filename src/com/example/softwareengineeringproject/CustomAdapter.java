package com.example.softwareengineeringproject;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<Event>{

	private ArrayList<Event> list;
	private Context context;
	
	public CustomAdapter(Context context, int resource, ArrayList<Event> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		list = new ArrayList<Event>();
		list.addAll(objects);
	}
	
	private class ViewHolder {
		TextView code;
		CheckBox attend;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		
		if(convertView == null) {
			LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(R.layout.listview_layout, null);
			holder = new ViewHolder();
			holder.attend = (CheckBox) convertView.findViewById(R.id.attend);
			holder.code = (TextView) convertView.findViewById(R.id.events_info);
			convertView.setTag(holder);
			
			holder.attend.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					CheckBox cb = (CheckBox)v;
					Event event = (Event) cb.getTag();
					event.setAttend(cb.isChecked());
				}
			});
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Event event = list.get(position);
		holder.code.setText(" (" +  event.getEventName() + ")");
		holder.attend.setChecked(event.getAttend());
		holder.attend.setTag(event);
		return convertView;
	}
}
