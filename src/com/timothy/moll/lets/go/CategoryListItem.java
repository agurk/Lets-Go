package com.timothy.moll.lets.go;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class CategoryListItem extends TableRow {

	private TextView category;
	private LinearLayout layout;
	private TableLayout items;
	
	public CategoryListItem(Context context) {
		super(context);
		createMainLayout();
	}

	private void createMainLayout() {
		this.layout = new LinearLayout(this.getContext());
		this.layout.setLayoutParams(new LayoutParams( LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		this.layout.setOrientation(LinearLayout.VERTICAL);
		this.addView(this.layout);
		this.setPadding(0, 0, 0, 30);
		
		this.category = new TextView(this.getContext(),null, android.R.attr.textAppearanceLarge);
		this.category.setPadding(20, 0, 0, 5);

		View ruler = new View(this.getContext());
		ruler.setBackgroundColor(0xFF00FF00);

		this.items = new TableLayout(this.getContext());
		
		this.layout.addView(this.category);
		this.layout.addView(ruler,ViewGroup.LayoutParams.FILL_PARENT, 2);
		this.layout.addView(this.items);
	}
	
	
	public void SetCategory(String category) {
		this.category.setText(category);
	}
	
	public void addItem(String item) {
		TableRow row = new TableRow(this.getContext());
		this.items.addView(row);
		TextView itemName = new TextView(this.getContext(), null, android.R.attr.textAppearanceMedium);
		row.addView(itemName);
		itemName.setText(item);
		itemName.setPadding(40, 10, 0, 0);
		
	}
	
}
