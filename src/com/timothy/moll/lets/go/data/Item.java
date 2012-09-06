package com.timothy.moll.lets.go.data;

public class Item {

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

	private String id;
	private String name;
	private boolean checked;
	
	public Item(String id, String name, boolean checked) {
		this.id = id;
		this.name = name;
		this.checked = checked;
	}

	public boolean matchesId(String id) {
		if (id.equals(this.id)) {
			return true;
		}
		return false;
	}
	
}
