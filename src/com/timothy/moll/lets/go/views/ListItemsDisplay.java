package com.timothy.moll.lets.go.views;

import java.util.ArrayList;
import java.util.List;

import com.timothy.moll.lets.go.RunListFragments;
import com.timothy.moll.lets.go.data.Category;
import com.timothy.moll.lets.go.data.Item;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ListItemsDisplay extends TableLayout {

	private List<String> itemCheckBoxes;
	
	public ListItemsDisplay(Context context) {
		super(context);
	}

//	public void addCategory(Category category, boolean packed, RunListFragments rlf) {
//		CategoryItemLayoutFoo cil = new CategoryItemLayoutFoo(getContext());
//		cil.addCategory(category, packed, rlf);
////		this.itemCheckBoxes.addAll(cil.getCheckBoxIds());
//		this.addView(cil);
//	}

	// Returns id's of items that are selected
	public List<String> getSelectedItems() {
		ArrayList<String> selectedItems = new ArrayList<String>();
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
	
	public void toggleItem(Item item) {
		TextView tv = (TextView) findViewById(Integer.parseInt(item.getId()));
		if (tv.getVisibility() == VISIBLE) {
			tv.setVisibility(INVISIBLE);
		} else {
			tv.setVisibility(VISIBLE);
		}
	}
}

class CategoryItemLayoutFoo extends TableRow {

	private boolean allChecked;

	private List<TextView> checkBoxes;
	private List<Item> items;

	public CategoryItemLayoutFoo(Context context) {
		super(context);
		setPadding(0, 0, 0, 30);
		checkBoxes = new ArrayList<TextView>();
		allChecked = true;
	}
//
//	public void addCategory(Category category, boolean packed, RunListFragments rlf) {
//		LinearLayout layout = new LinearLayout(this.getContext());
//		layout.setOrientation(LinearLayout.VERTICAL);
//		
//		TextView categoryName = new TextView(this.getContext(),null, android.R.attr.textAppearanceLarge);
//		categoryName.setPadding(20, 0, 0, 5);
//		categoryName.setText(category.getName());
////
////		final  CategoryItemLayoutFoo foo = this;
////		categoryName.setOnClickListener(new OnClickListener(){
////
////			@Override
////			public void onClick(View v) {
////				foo.changeAllCheckBoxes();
////			}});
//		
//		View ruler = new View(this.getContext());
//		ruler.setBackgroundColor(0xFF0000FF);
//		
//		layout.addView(categoryName);
//		layout.addView(ruler,ViewGroup.LayoutParams.MATCH_PARENT, 2);
//		layout.addView(createItems(category.getItems(), packed, rlf));
//		this.items = category.getItems();
//		this.addView(layout);
//	}
////	
//	private View createItems(List<Item> items, boolean packed, final RunListFragments rlf) {
//		TableLayout itemLayout = new TableLayout(this.getContext());
//		for (Item item : items) {
//			TableRow row = new TableRow(this.getContext());
//			itemLayout.addView(row);
//			TextView itemName = new TextView(this.getContext());
//			row.addView(itemName);
//			this.checkBoxes.add(itemName);
//			itemName.setText(item.getName());
//			itemName.setId(Integer.parseInt(item.getId()));
//			if ( (packed && item.isPacked()) || (!packed && !item.isPacked()) ) {
//				itemName.setVisibility(VISIBLE);
//			} else {
//				itemName.setVisibility(INVISIBLE);
//			}
//			
//			final Item itemRef = item;
//			itemName.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					rlf.toggleItem(itemRef);
//				}
//				
//			});
////			itemName.setChecked(item.isPacked());
//		}
//		return itemLayout;
//	}

	public List<String> getCheckBoxIds() {
		List<String> checkboxIds = new ArrayList<String>();
		for (Item item: this.items) {
			checkboxIds.add(item.getId());
		}
		return checkboxIds;
	}
	
	
//	private void changeAllCheckBoxes() {
//		for (CheckBox cb : this.checkBoxes) {
//			if (this.allChecked) {
//				cb.setChecked(false);
//			} else {
//				cb.setChecked(true);
//			}
//		}
//		this.allChecked = (!this.allChecked);
//	}
	
}
