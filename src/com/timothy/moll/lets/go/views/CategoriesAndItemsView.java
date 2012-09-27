package com.timothy.moll.lets.go.views;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.timothy.moll.lets.go.data.Category;


public class CategoriesAndItemsView extends ScrollView {
	
	private TableLayout mainLayout;
	
	public CategoriesAndItemsView(Context context) {
		super(context);
		this.mainLayout = new TableLayout(context);
		this.addView(mainLayout);
		mainLayout.setLayoutParams(new LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
	}
	
	public void addCategories(List<Category> categories) {
		for (Category category : categories) {
			CategoryListItem one = new CategoryListItem(getContext());
			one.addCategory(category);
			mainLayout.addView(one);
		}
		
    	if(mainLayout.getChildCount() == 0) {
    		TextView tv = new TextView(getContext(), null, android.R.attr.textAppearanceLarge);
    		tv.setPadding(20, 0, 0, 0);
    		tv.setText("No Categories added yet...");
    		mainLayout.addView(tv);
    	}
	}
	

	public void update(List<Category> categories) {
		mainLayout.removeAllViews();
		addCategories(categories);
	}
	
}
