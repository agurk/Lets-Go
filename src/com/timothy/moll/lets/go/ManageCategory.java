package com.timothy.moll.lets.go;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.timothy.moll.lets.go.data.CategoriesAndItems;
import com.timothy.moll.lets.go.data.Category;

public class ManageCategory extends Activity {
		
	private Category category;
	
	private TextView typeName;
	
	
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.manage_categories_items);
	     ActionBar actionBar = getActionBar();
	     actionBar.setDisplayHomeAsUpEnabled(true);
	     actionBar.show();
	     
	     typeName = (TextView) findViewById(R.id.category_item_type);
	     
	     typeName.setText("Category Name");
	     
	     String id = getIntent().getExtras().getString("ID");
	     if (id == null) {
	    	 this.category = new Category(null, null, null);
	     } else {
	    	 CategoriesAndItems cAndI = new CategoriesAndItems(this);
	    	 this.category = cAndI.getCategoryById(id);
	    	 EditText te = (EditText) findViewById(R.id.category_item_name);
	    	 te.setText(category.getName());
	     }
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
	 
	 private void save() {
		 // TODO: Add data checking
		 CategoriesAndItems CandA = new CategoriesAndItems(this);
		 EditText te = (EditText) findViewById(R.id.category_item_name);
		 this.category.setName(te.getText().toString());
		 CandA.updateCategory(this.category);
	 }
	
}