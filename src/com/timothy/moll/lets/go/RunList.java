package com.timothy.moll.lets.go;

import com.timothy.moll.lets.go.data.Category;
import com.timothy.moll.lets.go.data.ListData;
import com.timothy.moll.lets.go.data.Lists;
import com.timothy.moll.lets.go.views.SelectableCategoryItemList;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class RunList extends Activity {
	
	private ListData list;
	
	private SelectableCategoryItemList itemsList;
	
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.run_list);
	     ActionBar actionBar = getActionBar();
	     actionBar.setDisplayHomeAsUpEnabled(true);
	     actionBar.show();
	     Lists lists = new Lists(this);
	     list = lists.getList(getIntent().getExtras().getString("ID"));
//	     createDisplay();
	     setTitle(list.getName());
	 }
	 
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
		 getMenuInflater().inflate(R.menu.run_list, menu);
		 return true;
	    }
	 
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item){
		 switch(item.getItemId()) {
		 case R.id.run_list_edit:
			 save();
			 Intent intent = new Intent();
			 intent.setClassName("com.timothy.moll.lets.go", "com.timothy.moll.lets.go.ManageList");
			 intent.putExtra("ID", list.getId());
			 startActivity(intent);
			 break;
		 case R.id.run_list_delete:
			 delete();
			 finish();
			 break;
		 case android.R.id.home:
			 save();
			 finish();
			 break;
		 case R.id.run_list_save:
			 save();
			 break;
      }
		 return true;
	 }

	 @Override
	 public void onResume() {
		 LinearLayout layout = (LinearLayout) findViewById(R.id.run_list_layout);
		 if (this.itemsList != null) {
			 layout.removeAllViews();
		 }
		 createDisplay();
		 super.onResume();
	 }
	 
	 private void createDisplay() {
		 LinearLayout layout = (LinearLayout) findViewById(R.id.run_list_layout);
		 this.itemsList = new SelectableCategoryItemList(this);
		 layout.addView(this.itemsList);
		 
		 for (Category category : list.getCategories()) {
			 this.itemsList.addCategory(category);
		 }
		 
	 }
	 
	 private void delete() {
		 //TODO add confirm on delete!
		 Lists lists = new Lists(this);
		 lists.deleteList(this.list);
	 }
	 
	 private void save() {
		 Lists lists = new Lists(this);
		 lists.updateCheckedItems(list.getId(), this.itemsList.getAllItems());
	 }
}
