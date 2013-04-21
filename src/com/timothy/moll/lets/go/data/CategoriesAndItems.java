package com.timothy.moll.lets.go.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;

public class CategoriesAndItems {
	
	private DBHelper db;
	
	private List<Category> categories;
	private List<Item> items;
	private Map<String, String> itemRelationships;
	
	public CategoriesAndItems(Context context) {
		this.db = new DBHelper(context);		
		
		this.categories = db.getCategories();
		this.items = db.getItems();
		this.itemRelationships = db.getItemRelationships();
	}
	
	@Deprecated
	public List<String> getItemsForCategory(String id) {
		ArrayList<String> matchingItems = new ArrayList<String>();
		for (Map.Entry<String, String> entry : this.itemRelationships.entrySet()) {
			if(entry.getValue().equals(id)) {
				matchingItems.add(entry.getKey());
			}
		}
		return matchingItems;
	}

	
	public Item getItemById(String id) {
		for (Item item : this.items) {
			if (item.getId().equals(id)) {
				return item;
			}
		}
		return null;
	}
	
	public void updateItem(Item item) {
		if (item.getId() == null) {
			db.addItem(item);
		} else {
			db.updateItem(item);
		}
	}
	
	@Deprecated
	public Map<String, String> getItemRelationships() {
		return this.itemRelationships;
	}
	
	public void updateCategory(Category category) {
		if (category.getId() == null) {
			db.addCategory(category);
		} else {
			db.updateCategory(category);
		}
	}

	public List<Category> getCategories() {
		return categories;
	}
	
	public Category getCategoryById(String id) {
		return db.getCategoryById(id);
	}
	
//	public void updatePackedItems(String listId, List<Item> changedItems) {
//		db.updateListItems(listId, changedItems);
//	}
}
