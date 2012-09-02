package com.timothy.moll.lets.go;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class SelectableCategoryItemList extends TableLayout{

	private List<String> itemCheckBoxes;
	
	public SelectableCategoryItemList(Context context) {
		super(context);
		this.setLayoutParams(new LayoutParams( LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		itemCheckBoxes = new ArrayList<String>();
		// TODO Auto-generated constructor stub
	}

	public void addCategory(String category, List<String> itemIds, Map<String, String> allItems) {
		Log.w("SelCatItList","Adding: " + category);
		TableRow tr = new TableRow(this.getContext());
		tr.setPadding(0, 0, 0, 30);
		
		LinearLayout layout = new LinearLayout(this.getContext());
		layout.setOrientation(LinearLayout.VERTICAL);
		
		TextView categoryName = new TextView(this.getContext(),null, android.R.attr.textAppearanceLarge);
		categoryName.setPadding(20, 0, 0, 5);
		categoryName.setText(category);

		View ruler = new View(this.getContext());
		ruler.setBackgroundColor(0xFF00FF00);
		
		layout.addView(categoryName);
		layout.addView(ruler,ViewGroup.LayoutParams.FILL_PARENT, 2);
		layout.addView(createItems(itemIds, allItems));
		tr.addView(layout);
		this.addView(tr);
	}
	
	private View createItems(List<String> itemIds, Map<String, String> allItems) {
		TableLayout itemLayout = new TableLayout(this.getContext());
		for (String item : itemIds) {
			TableRow row = new TableRow(this.getContext());
			itemLayout.addView(row);
			CheckBox itemName = new CheckBox(this.getContext());
			row.addView(itemName);
			itemName.setText(allItems.get(item));
			itemName.setId(Integer.parseInt(item));
			this.itemCheckBoxes.add(item);
			itemName.setPadding(40, 10, 0, 0);
		}
		return itemLayout;
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
}
