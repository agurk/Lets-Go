package com.timothy.moll.lets.go.views;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.FrameLayout.LayoutParams;

import com.timothy.moll.lets.go.data.ListData;
import com.timothy.moll.lets.go.data.Lists;

public class AllListsView extends ScrollView {
	
	private TableLayout mainLayout;
	
	public AllListsView(Context context) {
		super(context);
		this.mainLayout = new TableLayout(context);
		mainLayout.setLayoutParams(new LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		this.addView(mainLayout);
	}

	public void addLists(List<ListData> lists) {    	
    	for (ListData list : lists) {
    		ListTableRow listItem = new ListTableRow(getContext());
    		final String listID = list.getId();
    		listItem.setName(list.getName());
    		listItem.setClickable(true);
    		listItem.setOnClickListener(new View.OnClickListener(){

    			@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClassName("com.timothy.moll.lets.go", "com.timothy.moll.lets.go.RunList");
					intent.putExtra("ID",listID);
					getContext().startActivity(intent);

			}});
    		
    		mainLayout.addView(listItem);
    		
    		View ruler = new View(this.getContext());
    		ruler.setBackgroundColor(0x00000000);
    		mainLayout.addView(ruler,ViewGroup.LayoutParams.MATCH_PARENT, 5);
    	}
    	
    	if(mainLayout.getChildCount() == 0) {
    		TextView tv = new TextView(getContext(), null, android.R.attr.textAppearanceLarge);
    		tv.setPadding(20, 0, 0, 0);
    		tv.setText("No Lists added yet...");
    		mainLayout.addView(tv);
    	}
   	}
	
	public void update(List<ListData> lists) {
		mainLayout.removeAllViews();
		addLists(lists);
	}
}
