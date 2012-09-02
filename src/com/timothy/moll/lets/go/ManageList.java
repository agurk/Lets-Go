package com.timothy.moll.lets.go;

import com.timothy.moll.lets.go.ManageCategoriesItems.TYPE;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow.LayoutParams;

public class ManageList extends Activity {
	
	private String id;
	
	private SelectableCategoryItemList itemList;
	
	
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.manage_categories_items);
	     ActionBar actionBar = getActionBar();
	     actionBar.setDisplayHomeAsUpEnabled(true);
	     actionBar.show();
	     id = getIntent().getExtras().getString("ID");
	     createListContent();
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
		 case R.id.manage_categories_items_cancel:
		 case android.R.id.home:
			 finish();
			 break;
       }
		 return true;
	 }
	 
	 private void createListContent() {
		this.itemList = new SelectableCategoryItemList(this);
        DBHelper db = new DBHelper(this);
     	CategoriesAndItems CandI = db.getCategoriesAndItems();
     	for (String category : CandI.getCategories()) {
    		this.itemList.addCategory(category, CandI.getItemsForCategory(category), CandI.getAllItems());
    	}
     	LinearLayout ll = (LinearLayout) findViewById(R.id.manage_categories_items_layout);
     	ll.addView(this.itemList);
	 }
	 
	 private void save() {
		 DBHelper db = new DBHelper(this);
		 CategoriesAndItems CandI = db.getCategoriesAndItems();
		 EditText te = (EditText) findViewById(R.id.category_item_name);
		 CandI.updateList(id, te.getText().toString(), this.itemList.getSelectedItems());
	 }

}
