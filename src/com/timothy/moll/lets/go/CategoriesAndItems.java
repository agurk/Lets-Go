package com.timothy.moll.lets.go;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;

public class CategoriesAndItems {
	
	private DBHelper db;
	
	private Map<String, String> categories;
	private Map<String, String> items;
	private Map<String, String> itemRelationships;
	private List<String> lists;
	
	public CategoriesAndItems (Map<String, String> categories, Map<String, String> items,
								Map<String, String> itemRelationships, List<String> lists, DBHelper db) {
		this.categories = categories;
		this.items = items;
		this.itemRelationships = itemRelationships;
		this.lists = lists;
		this.db = db;
	}

	public List<String> getCategories() {
		List<String> categoryName = new ArrayList<String>();
		for (Map.Entry<String, String> entry : this.categories.entrySet()) {
			categoryName.add(entry.getKey());
		}
		return categoryName;
	}
	
	public Map<String, String> getCategoriesWithID() {
		return this.categories;
	}
	
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
	
	public Map<String, String> getAllItems() {
		return this.items;
	}
//	
//	public Map<String, String> getItemsForCategoryWithID(String id) {
//		Map<String, String> items = new HashMap<String, String>();
//		for (Map.Entry<String, String> entry : this.items.entrySet()) {
//			if(entry.getValue().equals(id)) {
//				items.put(entry.getKey(), entry.getValue());
//			}
//		}
//		return items;
//	}
	
	public Map<String, String> getItemRelationships() {
		return this.itemRelationships;
	}
	
	public void updateCategory(String id, String category) {
		if (id == null) {
			db.addCategory(category);
		}
	}
	
	public void updateItem(String id, String item, String category) {
		String categoryId = this.categories.get(category);
		Log.w("CatID", categoryId);
		if (id == null) {
			db.addItem(item, categoryId);
		}
	}
	
	public List<String> getLists() {
		return this.lists;
	}
	
	public void updateList(String listId, String listName, List<String> items) {
		if (listId == null) {
			listId = db.addList(listName, items);
		} else {
			// TODO update list details
		}
		db.updateListItems(listId, items);
	}
	
}
