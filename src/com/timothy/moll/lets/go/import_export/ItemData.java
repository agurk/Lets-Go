package com.timothy.moll.lets.go.import_export;

public class ItemData {
	
	private final String Category;
	private final String Item;
	
	public ItemData(final String category, final String item) {
		this.Category = category;
		this.Item = item;
	}

	public String getCategory() {
		return Category;
	}

	public String getItem() {
		return Item;
	}

}
