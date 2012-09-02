package com.timothy.moll.lets.go;

import java.util.List;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

public class MainScreen extends FragmentActivity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
     * sections. We use a {@link android.support.v4.app.FragmentPagerAdapter} derivative, which will
     * keep every loaded fragment in memory. If this becomes too memory intensive, it may be best
     * to switch to a {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        // Create the adapter that will return a fragment for each of the three primary sections
        // of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding tab.
        // We can also use ActionBar.Tab#select() to do this if we have a reference to the
        // Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by the adapter.
            // Also specify this Activity object, which implements the TabListener interface, as the
            // listener for when this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }

//    @Override
//    public void onResume() {  // After a pause OR at startup
//        super.onResume();
//        this.notifyDataSetChanged();
//	}

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	switch(item.getItemId()) {
    		case R.id.add_category:
    			loadManageCategoriesItem(ManageCategoriesItems.TYPE.CATEGORY, null);
                break;
            case R.id.add_item:
                loadManageCategoriesItem(ManageCategoriesItems.TYPE.ITEM, null);
            	break;
            case R.id.add_new_list:
            	loadManageList(null);
            	break;
            case R.id.menu_settings:
                //TODO
                break;
    	}
    	return true;
    }

    private void loadManageList(String id) {
    	Intent intent = new Intent();
    	intent.setClassName("com.timothy.moll.lets.go", "com.timothy.moll.lets.go.ManageList");
        intent.putExtra("ID", id);
        startActivity(intent);
    }
    
//  null String for id to indicate we want a new one
    private void loadManageCategoriesItem(ManageCategoriesItems.TYPE type, String id) {
    	Intent intent = new Intent();
    	intent.setClassName("com.timothy.moll.lets.go", "com.timothy.moll.lets.go.ManageCategoriesItems");
        intent.putExtra("TYPE", type.toString());
        intent.putExtra("ID", id);
        startActivity(intent);
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

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
     * sections of the app.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = new DummySectionFragment();
            Bundle args = new Bundle();
            args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, i + 1);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: return getString(R.string.title_section1).toUpperCase();
                case 1: return getString(R.string.title_section2).toUpperCase();
            }
            return null;
        }
    }

    /**
     * A dummy fragment representing a section of the app, but that simply displays dummy text.
     */
    public static class DummySectionFragment extends Fragment {
        public DummySectionFragment() {
        }

        public static final String ARG_SECTION_NUMBER = "section_number";
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
        			Bundle savedInstanceState) {
        	View mainLayout = null;
        	Bundle args = getArguments();
        	switch(args.getInt(ARG_SECTION_NUMBER)) {
        		case 1:
        			mainLayout = setupMainScreen();
        			break;
        		case 2:
        			mainLayout = setupCIScreen();
        			break;
        	}
        	return mainLayout;
        }
        
        private View setupMainScreen() {
        	TableLayout mainLayout = new TableLayout(getActivity());
        	DBHelper db = new DBHelper(getActivity());
        	CategoriesAndItems CandA = db.getCategoriesAndItems();
        	
        	for (String list : CandA.getLists()) {
        		ListTableRow listItem = new ListTableRow(getActivity());
        		listItem.setName(list);
        		mainLayout.addView(listItem);
        	}
        	
        	if(mainLayout.getChildCount() == 0) {
        		TextView tv = new TextView(getActivity(), null, android.R.attr.textAppearanceLarge);
        		tv.setPadding(20, 0, 0, 0);
        		tv.setText("No Lists added yet...");
        		mainLayout.addView(tv);
        	}
        	
        	return mainLayout;
        }

        private View setupCIScreen() {
        	TableLayout mainLayout = new TableLayout(getActivity());
        	mainLayout.setLayoutParams(new LayoutParams( LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        	DBHelper db = new DBHelper(getActivity());
        	CategoriesAndItems CandA = db.getCategoriesAndItems();
        	
        	for (String category : CandA.getCategories()) {
        		CategoryListItem one = new CategoryListItem(getActivity());
        		one.SetCategory(category);
//        		one.addItem(Integer.toString(CandA.getItemsForCategory(category).size()));
        		for (String item : CandA.getItemsForCategory(category)) {
        			one.addItem(CandA.getItemText(item));
        		}
        		mainLayout.addView(one);
        	}
        	
        	if(mainLayout.getChildCount() == 0) {
        		TextView tv = new TextView(getActivity(), null, android.R.attr.textAppearanceLarge);
        		tv.setPadding(20, 0, 0, 0);
        		tv.setText("No Categories added yet...");
        		mainLayout.addView(tv);
        	}
        	
        	return mainLayout;
        }
        
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                Bundle savedInstanceState) {
//            TextView textView = new Textfffthis.layout.addView(tv, ViewGroup.LayoutParams.FILL_PARENT, 50);View(getActivity());
//            textView.setGravity(Gravity.CENTER);
//            Bundle args = getArguments();
//            textView.setText(Integer.toString(args.getInt(ARG_SECTION_NUMBER)));
//            return textView;
//        }
    }
}
