package com.timothy.moll.lets.go.views;

import com.timothy.moll.lets.go.data.Item;

import android.content.Context;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

public class RunListItem extends TableRow {
	
	private Item item;
	
	private TextView itemName;
	
	private RunListCategory packedCategory;
	private RunListCategory unpackedCategory;
	
	Boolean packed;

	public RunListItem(Context context, Item item, RunListCategory packed, RunListCategory unpacked) {
		super(context);
		this.item = item;
		this.packedCategory = packed;
		this.unpackedCategory = unpacked;
		this.packed = item.isPacked();
		createItem();
	}
	
	private void createItem() {
		this.itemName = new TextView(this.getContext());
		this.addView(this.itemName);
		this.itemName.setText(item.getName());
		final RunListItem thisRef = this;
		itemName.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				thisRef.togglePacked();
		}});
		if(packed) {
			packedCategory.addView(this);
		} else {
			unpackedCategory.addView(this);
		}
	}
	
	private void togglePacked() {
		if(packed) {
			packedCategory.removeView(this);
			unpackedCategory.addView(this);
		} else {
			unpackedCategory.removeView(this);
			packedCategory.addView(this);
		}
		packed = !packed;
		item.setPacked(!packed);
	}
}