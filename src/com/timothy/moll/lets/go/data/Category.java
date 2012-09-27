package com.timothy.moll.lets.go.data;

import java.util.List;

public class Category {

	private String name;
	private String id;
	private List<Item> items;
	
	public Category(String id, String name, List<Item> items) {
		this.id = id;
		this.name = name;
		this.items = items;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setName(String name) {
		this.name = name;
	}
}
