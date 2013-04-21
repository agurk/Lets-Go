package com.timothy.moll.lets.go.views;

import com.timothy.moll.lets.go.data.Item;
import com.timothy.moll.lets.go.views.style.WidgetFactory;

import android.content.Context;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

public class RunListItem extends TableRow {
	
	private Item item;
	
	private TextView itemName;
	
	private RunListCategory packedCategory;
	private RunListCategory unpackedCategory;
	
	private Boolean packed;

	public RunListItem(Context context, Item item, RunListCategory packed, RunListCategory unpacked) {
		super(context);
		this.item = item;
		this.packedCategory = packed;
		this.unpackedCategory = unpacked;
		this.packed = item.isPacked();
		createItem();
	}
	
	private void createItem() {
		this.itemName = WidgetFactory.getRunListItemTextView(getContext(), item.getName());
		this.addView(this.itemName);
		final RunListItem thisRef = this;
		itemName.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				thisRef.togglePacked();
		}});
		if(packed) {
			packedCategory.addItem(this);
		} else {
			unpackedCategory.addItem(this);
		}
	}
	
	public void resetItem() {
		if (packed) {
			packedCategory.removeItem(this);
			unpackedCategory.addItem(this);
			item.setPacked(!packed);
			packed = !packed;
		}
	}
	
	private void togglePacked() {
		if(packed) {
			packedCategory.removeItem(this);
			unpackedCategory.addItem(this);
		} else {
			unpackedCategory.removeItem(this);
			packedCategory.addItem(this);
		}
		packed = !packed;
		item.setPacked(!item.isPacked());
	}
	
	public Item getItem() {
		return this.item;
	}
}