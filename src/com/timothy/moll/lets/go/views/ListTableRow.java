package com.timothy.moll.lets.go.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TableRow;
import android.widget.TextView;

public class ListTableRow extends TableRow {

	private TextView listName;
	
	public ListTableRow(Context context) {
		super(context);
		createLayout();
		this.setBackgroundColor(0x10030303);
	}
	
	public ListTableRow(Context context, AttributeSet attrs) {
		super(context, attrs);
		createLayout();
	}

	private void createLayout() {
		this.listName = new TextView(getContext(), null, android.R.attr.textAppearanceLarge);
		this.listName.setPadding(20, 0, 0, 15);
		this.addView(this.listName);
	}
	
	public void setName(String name) {
		this.listName.setText(name);
	}
	
}
