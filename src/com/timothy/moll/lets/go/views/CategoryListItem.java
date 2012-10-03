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

public class CategoryListItem extends TableRow {

	private TextView categoryName;
	private LinearLayout layout;
	private TableLayout items;
		
	public CategoryListItem(Context context) {
		super(context);
		createMainLayout();
	}

	private void createMainLayout() {
		this.layout = new LinearLayout(this.getContext());
		this.layout.setLayoutParams(new LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		this.layout.setOrientation(LinearLayout.VERTICAL);
		this.addView(this.layout);
		this.setPadding(0, 0, 0, 30);
		
		this.categoryName = new TextView(this.getContext(),null, android.R.attr.textAppearanceLarge);
		this.categoryName.setPadding(20, 0, 0, 5);

		View ruler = new View(this.getContext());
		ruler.setBackgroundColor(0xFF0000FF);

		this.items = new TableLayout(this.getContext());
		
		this.layout.addView(this.categoryName);
		this.layout.addView(ruler,ViewGroup.LayoutParams.MATCH_PARENT, 2);
		this.layout.addView(this.items);
	}
	
	public void addCategory(Category category) {
		setCategoryName(category.getName());
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
	
	private void setCategoryName(String category) {
		this.categoryName.setText(category);
	}
	
	private void addItem(Item item) {
		TableRow row = new TableRow(this.getContext());
		this.items.addView(row);
		TextView itemName = new TextView(this.getContext(), null, android.R.attr.textAppearanceMedium);
		row.addView(itemName);
		itemName.setText(item.getName());
		itemName.setPadding(40, 10, 0, 0);
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
