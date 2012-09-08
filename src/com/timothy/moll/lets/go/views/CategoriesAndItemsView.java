package com.timothy.moll.lets.go.views;

import com.timothy.moll.lets.go.data.CategoriesAndItems;
import com.timothy.moll.lets.go.data.Category;
import com.timothy.moll.lets.go.data.DBHelper;
import android.content.Context;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;


public class CategoriesAndItemsView extends ScrollView {

	public static final int id = 101;
	
	public CategoriesAndItemsView(Context context) {
		super(context);
		this.setId(CategoriesAndItemsView.id);
		setupScreen();
	}
	
	private void setupScreen() {
    	TableLayout mainLayout = new TableLayout(getContext());
    	mainLayout.setLayoutParams(new LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    	DBHelper db = new DBHelper(getContext());
    	CategoriesAndItems CandA = db.getCategoriesAndItems();
    	
    	for (Category category : CandA.getCategories()) {
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
    	
    	this.addView(mainLayout);
	}

	public void update() {
		this.removeAllViews();
		setupScreen();
	}
	
}
