package com.timothy.moll.lets.go.data;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;


public class Lists {

	private DBHelper db;
	
	private Context context;
	
	private List<ListData> lists;

	
	public Lists(Context context) {
		this.context = context;
		DBHelper db = new DBHelper(context);
		this.db = db;
		this.lists = db.getLists();
	}
	
	@Deprecated
	public List<String> getListNames() {
		List<String> lists = new ArrayList<String>();
		for(ListData list : this.lists) {
			lists.add(list.getName());
		}
		return lists;
	}
	
	public List<ListData> getBasicLists() {
		return this.lists;
	}
	
	public ListData getList(String id) {
		return db.getList(id);
	}
	
	public String getListId(String listName) {
		String listId = null;
		for (ListData list: this.lists) {
			if (list.getName().equals(listName)) {
				listId = list.getId();
			}
		}
		return listId;
	}
	
	public void updateList(ListData list, String listName, List<String> items) {
		String listId;
		if (list == null) {
			listId = db.addList(listName, items);
		} else {
			listId = list.getId();
			db.updateListName(listId, listName);
		}
		db.updateListItems(listId, items, null);
	}
	
	public void updateCheckedItems(String listId, List<Item> items) {
		db.updateListItems(listId, items);
	}

	public List<Item> getItemsForCategoryid(String listId, String categoryId) {
		return db.getItemsForCategory(listId, categoryId);
	}
	
	public void deleteList(ListData listIn) {
		db.deleteList(listIn.getId());
	}
	
}
