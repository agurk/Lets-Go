package com.timothy.moll.lets.go;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import com.timothy.moll.lets.go.data.Lists;
import com.timothy.moll.lets.go.views.SelectableCategoryItemList;

public class RunList extends FragmentActivity implements ActionBar.TabListener  {
	
//	private ListData list;
	private String listId;
	
	private SelectableCategoryItemList itemsList;
	
	SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
//        FragmentManager bar = getSupportFragmentManager();
//        FragmentManager bar;// = new FragmentManager();
//        mSectionsPagerAdapter = new SectionsPagerAdapter(bar);

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
        
        listId = getIntent().getExtras().getString("ID");
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
			 intent.putExtra("ID", listId);
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

//	 @Override
//	 public void onResume() {
//		 LinearLayout layout = (LinearLayout) findViewById(R.id.run_list_layout);
//		 if (this.itemsList != null) {
//			 layout.removeAllViews();
//		 }
//		 createDisplay();
//		 super.onResume();
//	 }
//	 
//	 private void createDisplay() {
//		 LinearLayout layout = (LinearLayout) findViewById(R.id.run_list_layout);
//		 this.itemsList = new SelectableCategoryItemList(this);
//		 layout.addView(this.itemsList);
//		 
//		 for (Category category : list.getCategories()) {
//			 this.itemsList.addCategory(category);
//		 }
//		 
//	 }
	 
	 private void delete() {
		 //TODO add confirm on delete!
		 Lists lists = new Lists(this);
		 lists.deleteList(lists.getList(listId));
	 }
	 
	 private void save() {
		 Lists lists = new Lists(this);
		 lists.updateCheckedItems(listId, this.itemsList.getAllItems());
	 }
	 
	 
	    @Override
	    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	    }

	    @Override
	    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	        // When the given tab is selected, switch to the corresponding page in the ViewPager.
	        mViewPager.setCurrentItem(tab.getPosition());
	    }

	    @Override
	    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	    }
	    
	    public class SectionsPagerAdapter extends FragmentPagerAdapter {

	        public SectionsPagerAdapter(FragmentManager fm) {
	            super(fm);
	        }

	        @Override
	        public Fragment getItem(int i) {
	        	RunListFragments rLFs = new RunListFragments(listId, getBaseContext());
	        	switch(i) {
	        		case 0:
	        			return rLFs.getUnpacked();
	        		case 1:
	        			return rLFs.getPacked();
	        	}
	        	return null;
	        }

	        @Override
	        public int getCount() {
	            return 2;
	        }

	        @Override
	        public CharSequence getPageTitle(int position) {
	            switch (position) {
	                case 0: return getString(R.string.run_list_title_section1).toUpperCase();
	                case 1: return getString(R.string.run_list_title_section2).toUpperCase();
	            }
	            return null;
	        }
	    }
	    
}
