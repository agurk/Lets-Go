package com.timothy.moll.lets.go;

import java.util.ArrayList;
import java.util.List;

import com.timothy.moll.lets.go.R;
import com.timothy.moll.lets.go.ManageCategoriesItems.TYPE;
import com.timothy.moll.lets.go.data.CategoriesAndItems;
import com.timothy.moll.lets.go.data.Category;
import com.timothy.moll.lets.go.data.DBHelper;
import com.timothy.moll.lets.go.data.Item;
import com.timothy.moll.lets.go.data.ListData;
import com.timothy.moll.lets.go.data.Lists;
import com.timothy.moll.lets.go.views.SelectableCategoryItemList;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ManageList extends Activity {
	
	private ListData list;
	
	private SelectableCategoryItemList itemList;
	
	private TextView itemName;
	
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.manage_categories_items);
	     ActionBar actionBar = getActionBar();
	     actionBar.setDisplayHomeAsUpEnabled(true);
	     actionBar.show();
	     String id = getIntent().getExtras().getString("ID");
	     if (id == null) {
	    	 list = null;
	     } else {
	    	 Lists lists = new Lists(this);
	    	 list = lists.getList(id);
	     }
	     createListContent();
	     
	     itemName = (TextView) findViewById(R.id.category_item_type);
	     itemName.setText("List Name");
	 }
	 
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
		 getMenuInflater().inflate(R.menu.manage_list, menu);
		 return true;
	    }
	 
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item){
		 switch(item.getItemId()) {
		 case R.id.manage_list_save:
			 save();
		 case R.id.manage_list_cancel:
		 case android.R.id.home:
			 finish();
			 break;
       }
		 return true;
	 }
	 
	 private void createListContent() {
		 List<String> selectedItems = null;
		 if (this.list != null) {
			 EditText te = (EditText) findViewById(R.id.category_item_name);
			 te.setText(list.getName());
			 selectedItems = new ArrayList<String>();
			 for (Item item : list.getAllItems()) {
				 selectedItems.add(item.getId());
			 }
		 }
		this.itemList = new SelectableCategoryItemList(this);
        DBHelper db = new DBHelper(this);
     	CategoriesAndItems CandI = db.getCategoriesAndItems();
     	for (Category category : CandI.getCategories()) {
     		if (selectedItems != null) {
     			for (Item item : category.getItems()) {
     				if (!selectedItems.contains(item.getId())) {
     					item.setChecked(false);
     				}
     			}
     		}
    		this.itemList.addCategory(category);
    	}
     	LinearLayout ll = (LinearLayout) findViewById(R.id.manage_categories_items_layout);
     	ll.addView(this.itemList);
	 }
	 
	 private void save() {
		 Lists lists = new Lists(this);
		 EditText te = (EditText) findViewById(R.id.category_item_name);
		 lists.updateList(list, te.getText().toString(), this.itemList.getSelectedItems());
	 }

}
