package com.timothy.moll.lets.go;

import com.timothy.moll.lets.go.data.Lists;
import com.timothy.moll.lets.go.views.AllListsView;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AllListItems extends Fragment {
	
	private AllListsView mainView;

	public AllListItems() {
		super();
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    			Bundle savedInstanceState) {
    	Lists lists = new Lists(getActivity());
    	mainView = new AllListsView(getActivity());
    	mainView.addLists(lists.getBasicLists());
    	return mainView;    	
    }
	
	public void updateView(Context context) {
			Lists lists = new Lists(context);
			if (mainView != null) {
				mainView.update(lists.getBasicLists());
			}
	}
	
}