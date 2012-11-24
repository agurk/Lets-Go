package com.timothy.moll.lets.go.data;

import android.widget.TextView;

public class Item {

	private String id;
	private String name;
	private boolean packed;
	private String categoryId;
	
	private TextView packedView;
	private TextView unpackedView;
	
	private boolean changed;
	
	public Item(String id, String name, boolean checked, String categoryId) {
		this.id = id;
		this.name = name;
		this.packed = checked;
		this.categoryId = categoryId;
		if (id == null) {
			this.changed = true;
		}
	}

	public boolean matchesId(String id) {
		if (id.equals(this.id)) {
			return true;
		}
		return false;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
		this.changed = true;
	}
	
	public boolean isPacked() {
		return packed;
	}

	public void setPacked(boolean checked) {
		this.packed = checked;
		this.changed = true;
	}

	public String getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
		this.changed = true;
	}

	public TextView getPackedView() {
		return packedView;
	}

	public void setPackedView(TextView packedView) {
		this.packedView = packedView;
		this.changed = true;
	}

	public TextView getUnpackedView() {
		return unpackedView;
	}

	public void setUnpackedView(TextView unpackedView) {
		this.unpackedView = unpackedView;
		this.changed = true;
	}

	public boolean isChanged() {
		return changed;
	}

	public void setSaved() {
		this.changed = false;
	}
	
}
