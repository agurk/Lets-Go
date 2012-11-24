package com.timothy.moll.lets.go.views;

import java.util.List;

import android.content.Context;
import android.widget.ScrollView;
import android.widget.TableLayout;
import com.timothy.moll.lets.go.data.Category;
import com.timothy.moll.lets.go.views.style.WidgetFactory;

public class CategoriesAndItemsView extends ScrollView {

	private TableLayout mainLayout;

	public CategoriesAndItemsView(Context context) {
		super(context);
		this.mainLayout = new TableLayout(context);
		this.addView(mainLayout);
	}

	public void addCategories(List<Category> categories) {
		for (Category category : categories) {
			mainLayout.addView(new CategoryListItem(getContext(), category));
		}

    	if(mainLayout.getChildCount() == 0) {
    		mainLayout.addView(WidgetFactory.getEmptyListLabel(getContext(), "No Categories added yet..."));
    	}
	}

	public void update(List<Category> categories) {
		mainLayout.removeAllViews();
		addCategories(categories);
	}	
}