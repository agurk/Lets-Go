package com.timothy.moll.lets.go.data;

public class Item {

	private String id;
	private String name;
	private boolean checked;
	private String categoryId;
	
	public Item(String id, String name, boolean checked, String categoryId) {
		this.id = id;
		this.name = name;
		this.checked = checked;
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
	
	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
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
