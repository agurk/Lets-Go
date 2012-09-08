package com.timothy.moll.lets.go.views;

import com.timothy.moll.lets.go.data.Lists;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

public class AllListsView extends ScrollView {

	public static final int id = 102;
	
	public AllListsView(Context context) {
		super(context);
		this.setId(AllListsView.id);
		setupScreen();
	}

	private void setupScreen() {
    	TableLayout tableLayout = new TableLayout(getContext());
//    	Lists lists = db.getLists();
    	Lists lists = new Lists(this.getContext());
    	
    	for (String list : lists.getListNames()) {
    		ListTableRow listItem = new ListTableRow(getContext());
    		final String listID = lists.getListId(list);
    		listItem.setName(list);
    		listItem.setClickable(true);
    		listItem.setOnClickListener(new View.OnClickListener(){

    			@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClassName("com.timothy.moll.lets.go", "com.timothy.moll.lets.go.RunList");
					intent.putExtra("ID",listID);
					getContext().startActivity(intent);

			}});
    		
    		tableLayout.addView(listItem);
    		
    		View ruler = new View(this.getContext());
    		ruler.setBackgroundColor(0x00000000);
    		tableLayout.addView(ruler,ViewGroup.LayoutParams.MATCH_PARENT, 5);
    	}
    	
    	if(tableLayout.getChildCount() == 0) {
    		TextView tv = new TextView(getContext(), null, android.R.attr.textAppearanceLarge);
    		tv.setPadding(20, 0, 0, 0);
    		tv.setText("No Lists added yet...");
    		tableLayout.addView(tv);
    	}
    	
    	this.addView(tableLayout);
	}
	
	public void update() {
		this.removeAllViews();
		setupScreen();
	}
}
