package com.timothy.moll.lets.go;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.timothy.moll.lets.go.data.CategoriesAndItems;
import com.timothy.moll.lets.go.data.Category;
import com.timothy.moll.lets.go.data.Item;

public class ManageItem extends Activity {
	
	private Item item;
	
	private TextView typeName;
	
	private RadioGroup categoryGroup;
	
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.manage_categories_items);
	     ActionBar actionBar = getActionBar();
	     actionBar.setDisplayHomeAsUpEnabled(true);
	     actionBar.show();
	     
	     typeName = (TextView) findViewById(R.id.category_item_type);
	     typeName.setText("Item Name");

	     String id = getIntent().getExtras().getString("ID");
	     if (id == null) {
	    	 this.item = new Item(null, null, false, null);
	     } else {
	    	 CategoriesAndItems cAndI = new CategoriesAndItems(this);
	    	 this.item = cAndI.getItemById(id);
	    	 EditText te = (EditText) findViewById(R.id.category_item_name);
	    	 te.setText(item.getName());
	     }
	     
	     showCategories();
	 }
	 
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
		 getMenuInflater().inflate(R.menu.manage_categories_items, menu);
		 return true;
	    }
	 
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item){
		 switch(item.getItemId()) {
		 case R.id.manage_categories_items_save:
			 save();
		 case R.id.manage_categories_items_cancel:
		 case android.R.id.home:
			 finish();
			 break;
        }
		 return true;
	 }

	 private void showCategories() {
		 LinearLayout layout = (LinearLayout) findViewById(R.id.manage_categories_items_layout);
		  this.categoryGroup = new RadioGroup(this);
		 layout.addView(this.categoryGroup);
		 
		 CategoriesAndItems CandA = new CategoriesAndItems(this);
		 for(Category category : CandA.getCategories()) {
			 RadioButton rb = new RadioButton(this);
			 rb.setText(category.getName());
			 rb.setId(Integer.parseInt(category.getId()));
			 this.categoryGroup.addView(rb);
			 if(category.getId().equals(this.item.getCategoryId())) {
				 this.categoryGroup.check(rb.getId());
			 }
		 }
	 }
	 
	private void save() {
		// TODO: Add data checking
		CategoriesAndItems CandA = new CategoriesAndItems(this);
		EditText te = (EditText) findViewById(R.id.category_item_name);
		int selectedCategory = this.categoryGroup.getCheckedRadioButtonId();
		// -1 means no child is selected
		if (selectedCategory > -1) {
			RadioButton rb = (RadioButton) findViewById(selectedCategory);
//			this.item.setCategoryId(rb.getText().toString());
			this.item.setCategoryId(Integer.toString(rb.getId()));
			this.item.setName(te.getText().toString());
			Log.w("updating item", item.getName());
			CandA.updateItem(this.item);
		}
    }
	
}