package com.timothy.moll.lets.go.views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.timothy.moll.lets.go.R;
import com.timothy.moll.lets.go.data.Category;
import com.timothy.moll.lets.go.data.Item;

public class CategoryListItem extends TableRow {

	private TextView categoryName;
	private LinearLayout layout;
	private TableLayout items;
	
	private Category category;
	
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
		
		this.categoryName = new TextView(this.getContext(),null, android.R.attr.textAppearanceLarge);
		this.categoryName.setPadding(20, 0, 0, 5);

		View ruler = new View(this.getContext());
		ruler.setBackgroundColor(0xFF0000FF);

		this.items = new TableLayout(this.getContext());
		
		this.layout.addView(this.categoryName);
		this.layout.addView(ruler,ViewGroup.LayoutParams.FILL_PARENT, 2);
		this.layout.addView(this.items);
	}
	
	public void addCategory(Category category) {
		this.category = category;
		setCategoryName(category.getName());
		for (Item item : category.getItems()) {
			addItem(item.getName());
		}
	}
	
	private void setCategoryName(String category) {
		this.categoryName.setText(category);
	}
	
	private void addItem(String item) {
		TableRow row = new TableRow(this.getContext());
		this.items.addView(row);
		TextView itemName = new TextView(this.getContext(), null, android.R.attr.textAppearanceMedium);
		row.addView(itemName);
		itemName.setText(item);
		itemName.setPadding(40, 10, 0, 0);
		
	}
	
}
