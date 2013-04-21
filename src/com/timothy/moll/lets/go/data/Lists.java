package com.timothy.moll.lets.go.data;

import java.util.List;

import android.content.Context;


public class Lists {

	private DBHelper db;
		
	private List<ListData> lists;

	
	public Lists(Context context) {
		DBHelper db = new DBHelper(context);
		this.db = db;
		this.lists = db.getLists();
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
			db.addItemsToList(listId, items);
		} else {
			listId = list.getId();
			db.updateListName(listId, listName);
			List<String> removedItems = list.getAllItemIDs();
			
			for (String item : removedItems ) {
				if (items.contains(item)) {
					removedItems.remove(item);
					items.remove(item);
				}
			}
			
			db.addItemsToList(listId, items);
			db.removeItemsFromList(listId, removedItems);
			
		}
	}
	
	
	
	public List<Item> getItemsForCategoryid(String listId, String categoryId) {
		return db.getItemsForCategory(listId, categoryId);
	}
	
	public void deleteList(ListData listIn) {
		db.deleteList(listIn.getId());
	}
	
	public void updatePackedItems(ListData listIn) {
		db.updatePackedItems(listIn.getId(), listIn.getChangedItems());
	}
	
	public void resetList(ListData listIn) {
		db.resetList(listIn.getId());
	}
	
	public void saveItemPackedState(Item item, Boolean state, String listId) {
		db.saveItemPackedState(item.getId(), state, listId);
	}
	
	
}
