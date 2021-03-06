package com.timothy.moll.lets.go;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.timothy.moll.lets.go.R;
import com.timothy.moll.lets.go.data.CategoriesAndItems;
import com.timothy.moll.lets.go.data.DBHelper;

public class ManageCategoriesItems extends Activity {
	
	public enum TYPE {CATEGORY, ITEM};
	
	private TYPE type;
	private String id;
	
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
	     
	     type = TYPE.valueOf(getIntent().getExtras().getString("TYPE"));
	     id = getIntent().getExtras().getString("ID");
	     if (type.equals(TYPE.ITEM)) {
	    	 showCategories(id);
	    	 typeName.setText("Item Name");
	     } else {
	    	 typeName.setText("Category Name");
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

	 private void showCategories(String selectedCategoryId) {
		 LinearLayout layout = (LinearLayout) findViewById(R.id.manage_categories_items_layout);
		  this.categoryGroup = new RadioGroup(this);
		 layout.addView(this.categoryGroup);
		 
		 
		 DBHelper db = new DBHelper(this);
		 CategoriesAndItems CandA = db.getCategoriesAndItems();
		 for(String category : CandA.getCategoryNames()) {
			 RadioButton rb = new RadioButton(this);
			 rb.setText(category);
			 this.categoryGroup.addView(rb);
			 if(category.equals(selectedCategoryId)) {
				 this.categoryGroup.check(rb.getId());
			 }
		 }
	 }
	 
	 private void save() {
//		 Log.w("item","SAVING...");
		 DBHelper db = new DBHelper(this);
		 CategoriesAndItems CandA = db.getCategoriesAndItems();
		 EditText te = (EditText) findViewById(R.id.category_item_name);
		 if (this.type == TYPE.CATEGORY) {
			 CandA.updateCategory(id, te.getText().toString());
		 } else {
			 int selectedCategory = this.categoryGroup.getCheckedRadioButtonId();
			 // -1 means no child is selected
			 if (selectedCategory > -1) {
//				 Log.w("ITEM","updating DB..."+selectedCategory);
				 RadioButton rb = (RadioButton) findViewById(selectedCategory);
				 CandA.updateItem(id, te.getText().toString(),
						 rb.getText().toString());
			 }
		 }
	 }
	
}