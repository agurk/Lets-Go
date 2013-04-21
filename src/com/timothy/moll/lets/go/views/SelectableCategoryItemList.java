package com.timothy.moll.lets.go.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.timothy.moll.lets.go.data.Category;
import com.timothy.moll.lets.go.data.Item;

public class SelectableCategoryItemList extends TableLayout {

	private List<String> itemCheckBoxes;

	public SelectableCategoryItemList(Context context) {
		super(context);
		this.setLayoutParams(new LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		itemCheckBoxes = new ArrayList<String>();
	}


	public void addCategory(Category category) {
		CategoryItemLayout cil = new CategoryItemLayout(getContext());
		cil.addCategory(category);
		this.itemCheckBoxes.addAll(cil.getCheckBoxIds());
		this.addView(cil);
	}


	// Returns id's of items that are selected
	public List<String> getSelectedItems() {
		List<String> selectedItems = new Vector<String>();
		for (String entry: this.itemCheckBoxes) {
			CheckBox cb = (CheckBox) findViewById(Integer.parseInt(entry));
			if (cb != null && cb.isChecked()) {
				selectedItems.add(entry);
			}
		}
		return selectedItems;
	}
	
	public List<Item> getAllItems() {
		List<Item> items = new ArrayList<Item>();
		for (String id: this.itemCheckBoxes) {
			CheckBox cb = (CheckBox) findViewById(Integer.parseInt(id));
			// TODO set name, category
			Item item = new Item(id, null, cb.isChecked(),null);
			items.add(item);
		}
		return items;
	}
}

class CategoryItemLayout extends TableRow {

	private boolean allChecked;

	private List<CheckBox> checkBoxes;
	private List<Item> items;

	public CategoryItemLayout(Context context) {
		super(context);
		setPadding(0, 0, 0, 30);
		checkBoxes = new ArrayList<CheckBox>();
		allChecked = true;
	}

	public void addCategory(Category category) {
		LinearLayout layout = new LinearLayout(this.getContext());
		layout.setOrientation(LinearLayout.VERTICAL);
		
		TextView categoryName = new TextView(this.getContext(),null, android.R.attr.textAppearanceLarge);
		categoryName.setPadding(20, 0, 0, 5);
		categoryName.setText(category.getName());

		final  CategoryItemLayout foo = this;
		categoryName.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				foo.changeAllCheckBoxes();
			}});
		
		View ruler = new View(this.getContext());
		ruler.setBackgroundColor(0xFF0000FF);
		
		layout.addView(categoryName);
		layout.addView(ruler,ViewGroup.LayoutParams.MATCH_PARENT, 2);
		layout.addView(createItems(category.getItems()));
		this.items = category.getItems();
		this.addView(layout);
	}
	
	private View createItems(List<Item> items) {
		TableLayout itemLayout = new TableLayout(this.getContext());
		for (Item item : items) {
			TableRow row = new TableRow(this.getContext());
			itemLayout.addView(row);
			CheckBox itemName = new CheckBox(this.getContext());
			row.addView(itemName);
			this.checkBoxes.add(itemName);
			itemName.setText(item.getName());
			itemName.setId(Integer.parseInt(item.getId()));
			itemName.setChecked(item.isPacked());
//			this.itemCheckBoxes.add(item.getId());
//			itemName.setPadding(40, 10, 0, 0);
		}
		return itemLayout;
	}

	public List<String> getCheckBoxIds() {
		List<String> checkboxIds = new ArrayList<String>();
		for (Item item: this.items) {
			checkboxIds.add(item.getId());
		}
		return checkboxIds;
	}
	
	private void changeAllCheckBoxes() {
		for (CheckBox cb : this.checkBoxes) {
			if (this.allChecked) {
				cb.setChecked(false);
			} else {
				cb.setChecked(true);
			}
		}
		this.allChecked = (!this.allChecked);
	}
	
}
