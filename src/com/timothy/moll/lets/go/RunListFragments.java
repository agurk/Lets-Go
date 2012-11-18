package com.timothy.moll.lets.go;

import com.timothy.moll.lets.go.data.Category;
import com.timothy.moll.lets.go.data.Item;
import com.timothy.moll.lets.go.data.ListData;
import com.timothy.moll.lets.go.data.Lists;
import com.timothy.moll.lets.go.views.RunListCategory;
import com.timothy.moll.lets.go.views.RunListDisplay;
import com.timothy.moll.lets.go.views.SelectableCategoryItemList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;

public class RunListFragments {

	private RunListFragment packed;
	private RunListFragment unpacked;
	
	private Context context;
	
	private ListData list;
	
	private RunListFragments selfRef;
	
	public RunListFragments(String listId, Context context) {
		this.context = context;
		Lists lists = new Lists(context);
		this.list = lists.getList(listId);
		this.selfRef = this;
		
		packed = new RunListFragment();		
		unpacked = new RunListFragment();
		for (Category category : list.getCategories()) {
			RunListCategory packedCategory = new RunListCategory(context, category, packed);
			RunListCategory unpackedCategory = new RunListCategory(context, category, unpacked);
			for(Item item : category.getItems()) {
				RunListItem rlItem = new RunListItem(context, item, packedCategory, unpackedCategory);
			}
		}
	}
	
	public Fragment getPacked() {
		return packed;
	}

	public Fragment getUnpacked() {
		return unpacked;
	}
	

	
	public class RunListFragment extends Fragment {

		private LinearLayout mainView;
		private TableLayout categoriesAndItems;
		private boolean packedView;

		public RunListFragment() {
			super();
		}

	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    			Bundle savedInstanceState) {
			 mainView = new LinearLayout(context);
			 categoriesAndItems = new TableLayout(context);
			 mainView.addView(categoriesAndItems);
	    	return mainView;    	
	    }
	    
	    public void addCategory(RunListCategory category) {
	    	this.categoriesAndItems.addView(category);
	    }
	    
	    public void removeCategory(RunListCategory category) {
	    	this.categoriesAndItems.removeView(category);
	    }
	}
	
}
