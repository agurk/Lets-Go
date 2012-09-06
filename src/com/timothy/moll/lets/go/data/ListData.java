package com.timothy.moll.lets.go.data;

import java.util.ArrayList;
import java.util.List;

public class ListData {

	private String id;
	private String name;
	private List<Category> categories;
	
	public ListData(String id, String name, List<Category> categories) {
		this.id = id;
		this.name = name;
		this.categories = categories;;
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
		List<Item> items = new ArrayList<Item>();
		for (Category category : categories) {
			items.addAll(category.getItems());
		}
		return items;
	}
}
