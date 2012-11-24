package com.timothy.moll.lets.go.views;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.timothy.moll.lets.go.RunListFragments.RunListFragment;
import com.timothy.moll.lets.go.data.Category;
import com.timothy.moll.lets.go.views.style.WidgetFactory;

public class RunListCategory extends TableRow {

	private LinearLayout categoryLayout;
		
	private RunListFragment display;
	
	private int itemNumber;
	private boolean displayed;

	public RunListCategory(Context context, Category category, RunListFragment display) {
		super(context);
		this.display = display;
		this.categoryLayout = WidgetFactory.getRunListCategoryLayout(context);
		this.addView(categoryLayout);
		this.categoryLayout.addView(WidgetFactory.getRunListCategoryTextView(getContext(), category.getName()));
		this.categoryLayout.addView(WidgetFactory.getRuler(getContext()));
	}

	public void addItem(RunListItem newItem) {
		this.itemNumber++;
		this.categoryLayout.addView(newItem);
		setIfDisplayed();
	}
	
	public void removeItem(RunListItem oldItem) {
		// TODO: this could fail if a non-valid item is passed to it
		// i.e. it decrements the item number counter, without removing
		// an item
		this.itemNumber--;
		this.categoryLayout.removeView(oldItem);
		setIfDisplayed();
	}
	
	private void setIfDisplayed () {
		if (this.itemNumber>0 && !displayed) {
			display.addCategory(this);
			displayed = true;
		} else if (this.itemNumber<1 && displayed) {
			display.removeCategory(this);
			displayed = false;
		}	
	}
}