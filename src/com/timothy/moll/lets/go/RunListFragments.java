package com.timothy.moll.lets.go;

import com.timothy.moll.lets.go.data.Category;
import com.timothy.moll.lets.go.data.Item;
import com.timothy.moll.lets.go.data.ListData;
import com.timothy.moll.lets.go.data.Lists;
import com.timothy.moll.lets.go.views.ListItemsDisplay;
import com.timothy.moll.lets.go.views.SelectableCategoryItemList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class RunListFragments {

	private RunListFragment packed;
	private RunListFragment unpacked;
	
	private Context context;
	
	private ListData list;
	
	private RunListFragments selfRef;
	
	public RunListFragments(String listId, Context context) {
		this.context = context;
		packed = new RunListFragment();
		packed.setPackedView(true);
		
		unpacked = new RunListFragment();
		
		Lists lists = new Lists(context);
		this.list = lists.getList(listId);
		this.selfRef = this;
//		unpacked. createDisplay();
	}

//	 private LinearLayout createDisplay() {
//		 LinearLayout layout = new LinearLayout(context);
//		 SelectableCategoryItemList itemsList = new SelectableCategoryItemList(context);
//		 layout.addView(itemsList);
//		 
//		 for (Category category : list.getCategories()) {
//			 itemsList.addCategory(category);
//		 }
//	 	return layout;
//	 }
	
	public Fragment getPacked() {
		return packed;
	}

	public Fragment getUnpacked() {
		return unpacked;
	}
	
	public void toggleItem(Item item) {
		item.setPacked(!item.isPacked());
		packed.toggleItemVisibility(item);
		unpacked.toggleItemVisibility(item);
	}
	
	public class RunListFragment extends Fragment {

		private LinearLayout mainView;
		private ListItemsDisplay itemsList;
		private boolean packedView;

		public RunListFragment() {
			super();
		}

		public void setPackedView(boolean state) {
			this.packedView = state;
		}
		
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    			Bundle savedInstanceState) {
	    	Log.w("create View","here");
			 mainView = new LinearLayout(context);
			 itemsList = new ListItemsDisplay(context);
			 mainView.addView(itemsList);
			 
			 for (Category category : list.getCategories()) {
				 itemsList.addCategory(category, this.packedView, selfRef);
			 }
//	    	Lists lists = new Lists(getActivity());
//	    	mainView = new AllListsView(getActivity());
//	    	mainView.addLists(lists.getBasicLists());
	    	return mainView;    	
	    }
	    
	    public void toggleItemVisibility(Item item) {
	    	itemsList.toggleItem(item);
	    }
		
	}
	
}
