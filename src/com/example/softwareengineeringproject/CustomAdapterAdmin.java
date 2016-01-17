package com.example.softwareengineeringproject;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class CustomAdapterAdmin extends ArrayAdapter<AdminItem>{

	private ArrayList<AdminItem> list;
	private Context context;
	
	public CustomAdapterAdmin(Context context, int resource, ArrayList<AdminItem> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		list = new ArrayList<AdminItem>();
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
					AdminItem item = (AdminItem)cb.getTag();
					item.setChecked(cb.isChecked());
				}
			});
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		AdminItem item = list.get(position);
		holder.code.setText(item.getName());
		holder.attend.setChecked(item.getChecked());
		holder.attend.setTag(item);
		
		
		return convertView;
	}

}
