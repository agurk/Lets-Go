package com.timothy.moll.lets.go.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategoriesAndItems {
	
	private DBHelper db;
	
//	private Map<String, String> categories;
	private List<Category> categories;
	private Map<String, String> items;
	private Map<String, String> itemRelationships;
	
	public CategoriesAndItems (List<Category> categories, Map<String, String> items,
								Map<String, String> itemRelationships, DBHelper db) {
		this.categories = categories;
		this.items = items;
		this.itemRelationships = itemRelationships;
		this.db = db;
	}

	@Deprecated
	public List<String> getCategoryNames() {
		List<String> categoryName = new ArrayList<String>();
		for (Category entry : this.categories) {
			categoryName.add(entry.getName());
		}
		return categoryName;
	}
	
	@Deprecated
	public List<String> getItemsForCategory(String id) {
		ArrayList<String> matchingItems = new ArrayList<String>();
		for (Map.Entry<String, String> entry : this.itemRelationships.entrySet()) {
			if(entry.getValue().equals(id)) {
				matchingItems.add(entry.getKey());
//				matchingItems.add(this.items.get(entry.getKey()));
			}
		}
		return matchingItems;
	}
	
	public String getItemText(String id) {
		return this.items.get(id);
	}
	
	@Deprecated
	public Map<String, String> getAllItems() {
		return this.items;
	}

	
	public Map<String, String> getItemRelationships() {
		return this.itemRelationships;
	}
	
	public void updateCategory(String id, String category) {
		if (id == null) {
			db.addCategory(category);
		}
	}
	
	@Deprecated
	public void updateItem(String id, String item, String category) {
		String categoryId = null;
		for (Category category2 : categories) {
			if (category2.getName().equals(category)) {
				categoryId = category2.getId();
			}
		}
		if (id == null) {
			db.addItem(item, categoryId);
		}
	}

	public List<Category> getCategories() {
		return categories;
	}
	
}
