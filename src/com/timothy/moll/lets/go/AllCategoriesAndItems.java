package com.timothy.moll.lets.go;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.timothy.moll.lets.go.data.CategoriesAndItems;
import com.timothy.moll.lets.go.views.CategoriesAndItemsView;

public class AllCategoriesAndItems extends Fragment {
		
	private static CategoriesAndItemsView mainView;
		
	public AllCategoriesAndItems() {
		super(); 
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    			Bundle savedInstanceState) {
    	CategoriesAndItems cAndI = new CategoriesAndItems(getActivity());
    	mainView = new CategoriesAndItemsView(getActivity());
    	mainView.addCategories(cAndI.getCategories());
       	return mainView;    	
    }
    
    public void updateView(Context context) {
    	Log.w("Refreshing","here");
    		
    		CategoriesAndItems cAndI = new CategoriesAndItems(context);
    		if (mainView != null) {
    			Log.w("Refreshing","there");
    		mainView.update(cAndI.getCategories());
    		}
    }

	
}
