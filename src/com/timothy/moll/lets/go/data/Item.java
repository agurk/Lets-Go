package com.timothy.moll.lets.go.data;

public class Item {

	private String id;
	private String name;
	private boolean packed;
	private String categoryId;
	
	public Item(String id, String name, boolean checked, String categoryId) {
		this.id = id;
		this.name = name;
		this.packed = checked;
		this.categoryId = categoryId;
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
	}
	
	public boolean isPacked() {
		return packed;
	}

	public void setPacked(boolean checked) {
		this.packed = checked;
	}

	public String getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
