package com.timothy.moll.lets.go.views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.timothy.moll.lets.go.RunListFragments.RunListFragment;
import com.timothy.moll.lets.go.data.Category;

public class RunListCategory extends TableRow {

	private LinearLayout categoryLayout;
	
	private Category category;
	
	private TableLayout display;
	
	private int itemNumber;
	private boolean displayed;

	public RunListCategory(Context context, Category category, RunListFragment display) {
		super(context);
		setPadding(0, 0, 0, 30);
		this.category = category;
		this.display = display;
		categoryLayout = new LinearLayout(this.getContext());
		categoryLayout.setOrientation(LinearLayout.VERTICAL);
		this.addView(categoryLayout);
		createCategory();
	}
		
	private void createCategory() {
		TextView categoryName = new TextView(this.getContext(),null, android.R.attr.textAppearanceLarge);
		categoryName.setPadding(20, 0, 0, 5);
		categoryName.setText(category.getName());
		this.categoryLayout.addView(categoryName);
		View ruler = new View(this.getContext());
		ruler.setBackgroundColor(0xFF0000FF);
		this.categoryLayout.addView(ruler,ViewGroup.LayoutParams.MATCH_PARENT, 2);
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
		} else if (this.itemNumber<1 && displayed) {
			display.removeCategory(this);
		}
	}
		
}