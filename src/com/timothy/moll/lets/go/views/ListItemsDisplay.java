package com.timothy.moll.lets.go.views;

import java.util.ArrayList;
import java.util.List;

import com.timothy.moll.lets.go.data.Item;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TextView;

public class ListItemsDisplay extends TableLayout {

	private List<String> itemCheckBoxes;
	
	public ListItemsDisplay(Context context) {
		super(context);
	}

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