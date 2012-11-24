package com.timothy.moll.lets.go.views;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.timothy.moll.lets.go.data.Category;
import com.timothy.moll.lets.go.data.Item;
import com.timothy.moll.lets.go.views.style.WidgetFactory;

public class CategoryListItem extends TableRow {

	private LinearLayout layout;
	private TextView categoryName;
	
	private TableLayout items;

	public CategoryListItem(Context context, Category category) {
		super(context);	
		this.layout = WidgetFactory.getCategoryListItemLayout(context);
		this.addView(this.layout);
		this.categoryName = WidgetFactory.getRunListCategoryTextView(getContext(), category.getName());
		this.layout.addView(this.categoryName);
		this.layout.addView(WidgetFactory.getRuler(getContext()));
		this.items = new TableLayout(getContext());
		this.layout.addView(this.items);
		for (Item item : category.getItems()) {
			addItem(item);
		}
		final String categoryId = category.getId();
		// Check for null, as this will be the collect all category for
		// orphan items
		if (categoryId != null) {
			categoryName.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClassName("com.timothy.moll.lets.go", "com.timothy.moll.lets.go.ManageCategory");
					intent.putExtra("ID",categoryId);
					getContext().startActivity(intent);					
				}
			});
		}
	}
	
	private void addItem(Item item) {
		TableRow row = new TableRow(this.getContext());
		this.items.addView(row);
		TextView itemName = WidgetFactory.getRunListItemTextView(getContext(), item.getName()); 
		row.addView(itemName);
		final String itemId = item.getId();
		itemName.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClassName("com.timothy.moll.lets.go", "com.timothy.moll.lets.go.ManageItem");
				intent.putExtra("ID", itemId);
				getContext().startActivity(intent);	
			}		
		});
	}
}