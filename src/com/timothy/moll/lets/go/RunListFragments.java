package com.timothy.moll.lets.go;

import java.util.ArrayList;
import java.util.List;

import com.timothy.moll.lets.go.data.Category;
import com.timothy.moll.lets.go.data.Item;
import com.timothy.moll.lets.go.data.ListData;
import com.timothy.moll.lets.go.views.RunListCategory;
import com.timothy.moll.lets.go.views.RunListItem;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;

public class RunListFragments {

	private RunListFragment packed;
	private RunListFragment unpacked;
	
	private Context context;
	
	private ListData list;
	
	private List<RunListItem> runListItems;
	
	
	public RunListFragments(ListData list, Context context) {
		this.context = context;
		this.list = list;
		packed = new RunListFragment();
		unpacked = new RunListFragment();
		
		runListItems = new ArrayList<RunListItem>();
		for (Category category : list.getCategories()) {
			RunListCategory packedCategory = new RunListCategory(context, category, packed);
			RunListCategory unpackedCategory = new RunListCategory(context, category, unpacked);
			for(Item item : category.getItems()) {
				RunListItem rlItem = new RunListItem(context, item, packedCategory, unpackedCategory);
				this.runListItems.add(rlItem);
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
		private ScrollView scrollView;
		private LinearLayout mainView;
		private TableLayout categoriesAndItems;

	    public RunListFragment() {
			super();
			scrollView = new ScrollView(context);
	    	mainView = new LinearLayout(context);
	    	scrollView.addView(mainView);
			categoriesAndItems = new TableLayout(context);
			mainView.addView(categoriesAndItems);
		}

		@Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    			Bundle savedInstanceState) {
	    	return scrollView;
	    }
	    
	    public void addCategory(RunListCategory category) {
	    	if (this.categoriesAndItems == null) {
	    		
	    	} else {
	    		this.categoriesAndItems.addView(category);	
	    	}
	    }
	    
	    public void removeCategory(RunListCategory category) {
	    	this.categoriesAndItems.removeView(category);
	    }
	}
	
}
