package com.timothy.moll.lets.go.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ListData {

	private String id;
	private String name;
	private List<Category> categories;
	private List<Item> allItems;
	
	public ListData(String id, String name, List<Category> categories) {
		this.id = id;
		this.name = name;
		this.categories = categories;
		allItems = new ArrayList<Item>();
		// Categories could be null when this List is only a stub
		// to give a list of list names (view of all lists)
		if (categories != null) {
			for (Category category : categories) {
				allItems.addAll(category.getItems());
			}
		}
	}
	
	public List<Category> getCategories() {
		return categories;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public List<Item> getAllItems() {
		return allItems;
	}
	
	public List<String> getAllItemIDs() {
		List<String> IDs = new Vector<String>();
		for (Item item : allItems) {
			IDs.add(item.getId());
		}
		return IDs;
	}
	
	public List<Item> getChangedItems() {
		List<Item> changedItems = new ArrayList<Item>();
		for (Item item : this.allItems) {
			if (item.isChanged()) {
				changedItems.add(item);
			}
		}
		return changedItems;
	}
}
