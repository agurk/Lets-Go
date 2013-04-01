package com.timothy.moll.lets.go.views;

import com.timothy.moll.lets.go.views.style.WidgetFactory;

import android.content.Context;
import android.widget.TableRow;
import android.widget.TextView;

public class ListTableRow extends TableRow {

	private TextView listName;
	
	public ListTableRow(Context context) {
		super(context);
		this.listName = WidgetFactory.getListTableTextView(getContext());
		this.addView(this.listName);	
	}
	
	public void setName(String name) {
		this.listName.setText(name);
	}
}
