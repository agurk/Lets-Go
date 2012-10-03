package com.timothy.moll.lets.go.views;

import com.timothy.moll.lets.go.data.Category;

import android.content.ClipData.Item;
import android.content.Context;
import android.widget.ScrollView;
import android.widget.TableLayout;

public class RunListView extends ScrollView {

	private TableLayout mainLayout;
	
	public RunListView(Context context) {
		super(context);
		this.mainLayout = new TableLayout(context);
		this.addView(mainLayout);
		mainLayout.setLayoutParams(new LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
	}

	public void addCategory(Category category) {
		
	}
	
	public void toggleItemVisibility(Item item) {
		
	}
	
}
