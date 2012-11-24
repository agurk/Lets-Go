package com.timothy.moll.lets.go.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils.InsertHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "lets_go_data";
	private static final int DB_VERSION = 1;
	
	private static final String CATEGORIES_TABLE = "categories";
	private static final String CATEGORY_NAME    = "category";
	private static final String CATEGORY_ID      = "cat_id";
	
	private static final String ITEM_TABLE    = "items";
	private static final String ITEM_NAME     = "item";
	private static final String ITEM_CATEGORY = "category_id";
	private static final String ITEM_ID       = "item_id";
	
	private static final String LIST_TABLE = "lists";
	private static final String LIST_NAME  = "list";
	private static final String LIST_ID    = "list_id";
	
	private static final String LIST_ITEMS_TABLE   = "list_items";
	private static final String LIST_ITEMS_NAME    = "list_id_LIN";
	private static final String LIST_ITEMS_ITEM    = "item_id_LIN";
	private static final String LIST_ITEMS_CHECKED = "checked_state";
	private static final String LIST_ITEMS_ID      = "list_item_id";
	
	
	protected DBHelper(Context context) {
		  super(context, DB_NAME, null, DB_VERSION); 
	}
	
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + CATEGORIES_TABLE + " ("
					+ CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ CATEGORY_NAME + " TEXT)" );
		
		db.execSQL("CREATE TABLE " + ITEM_TABLE + " ("
				+ ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ ITEM_NAME + " TEXT, "
				+ ITEM_CATEGORY + " INTEGER)");
		
		db.execSQL("CREATE TABLE " + LIST_TABLE + " ("
				+ LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ LIST_NAME + " TEXT)" );
		
		db.execSQL("CREATE TABLE " + LIST_ITEMS_TABLE + " ("
				+ LIST_ITEMS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ LIST_ITEMS_NAME + " INTEGER, "
				+ LIST_ITEMS_ITEM + " INTEGER, "
				+ LIST_ITEMS_CHECKED + " INTEGER)");
	}
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + CATEGORIES_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + LIST_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + LIST_ITEMS_TABLE);
		
		onCreate(db);
	}
	
	private List<Item> getItemsWithoutCategory() {
		List<Item> items = new ArrayList<Item>();
		
		SQLiteDatabase db = this.getWritableDatabase();
		String selectQuery = "SELECT "+ITEM_ID + ", "+ITEM_NAME
							+ " FROM " + ITEM_TABLE 
							+ " WHERE " + ITEM_CATEGORY + " NOT IN "  
								+ " ( SELECT " + CATEGORY_ID + " FROM "
								+ CATEGORIES_TABLE + " ) ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
        	do {
        		Item item = new Item(cursor.getString(0), cursor.getString(1), Boolean.TRUE, null);
        		items.add(item);
        	} while (cursor.moveToNext());
        }
		
		return items;
	}
	
	private List<Item> getItemsForCategory(String categoryId) {
		List<Item> items = new ArrayList<Item>();
		
		SQLiteDatabase db = this.getWritableDatabase();
		String selectQuery = "SELECT "+ITEM_ID + ", "+ITEM_NAME
							+ " FROM " + ITEM_TABLE
							+ " WHERE " + ITEM_CATEGORY + " = " + categoryId;       
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
        	do {
        		Item item = new Item(cursor.getString(0), cursor.getString(1), Boolean.TRUE, categoryId);
        		items.add(item);
        	} while (cursor.moveToNext());
        }
		
		return items;
	}
	
	protected List<Category> getCategories() {
		SQLiteDatabase db = this.getWritableDatabase();
		
		String selectQuery = "SELECT "+CATEGORY_ID + ", "+CATEGORY_NAME+" FROM " + CATEGORIES_TABLE;       
        Cursor cursor = db.rawQuery(selectQuery, null);
        List<Category> categories = new ArrayList<Category>();
		if (cursor.moveToFirst()) {
            do {
            	Category category = new Category(cursor.getString(0), cursor.getString(1), getItemsForCategory(cursor.getString(0)));
            	categories.add(category);
            } while (cursor.moveToNext());
        }
		
		db.close();
		Category emptyCategory = new Category(null, "Orphan Children", getItemsWithoutCategory());
		categories.add(emptyCategory);
		return categories;
	}
	
	protected Category getCategoryById(String id) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		String selectQuery = "SELECT "+CATEGORY_ID + ", "+CATEGORY_NAME+" FROM " + CATEGORIES_TABLE
							+ " WHERE " + CATEGORY_ID + " = " + id;       
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        Category category = new Category(cursor.getString(0), cursor.getString(1), getItemsForCategory(cursor.getString(0)));

		db.close();
		
		return category;
	}

	protected Map<String, String> getItemRelationships() {
		SQLiteDatabase db = this.getWritableDatabase();
		
		String selectQuery = "SELECT " + CATEGORY_NAME + ", " + ITEM_TABLE +"." +ITEM_ID
						+ " FROM " + CATEGORIES_TABLE + ", " + ITEM_TABLE 
						+" WHERE "  + CATEGORY_ID + " = "  + ITEM_CATEGORY;
        Cursor cursor = db.rawQuery(selectQuery, null);
        Map<String, String> itemRelationship = new HashMap<String, String>();
		if (cursor.moveToFirst()) {
			do {
				// returning item, category
				itemRelationship.put(cursor.getString(1), cursor.getString(0));
			} while (cursor.moveToNext());
		}
		
		db.close();
		return itemRelationship;
	}
	
	protected List<Item> getItems() {
		SQLiteDatabase db = this.getWritableDatabase();
		String selectQuery = "SELECT " + ITEM_ID + ", " + ITEM_NAME + ", " + ITEM_CATEGORY
				+ " FROM " + ITEM_TABLE;
		Cursor cursor = db.rawQuery(selectQuery, null);
		List<Item> items = new ArrayList<Item>();
		if (cursor.moveToFirst()) {
			do {
				Item item = new Item(cursor.getString(0), cursor.getString(1), false, cursor.getString(2));
				items.add(item);
			} while (cursor.moveToNext());
		}
		
		return items;
	}
	
	protected ListData getList(String id) {
		SQLiteDatabase db = this.getWritableDatabase();

		String selectQuery = "SELECT " + LIST_NAME
					+ " FROM " + LIST_TABLE
					+ " WHERE " + LIST_ID + " = " + id;
		Cursor cursor = db.rawQuery(selectQuery, null);
		cursor.moveToFirst();
		String listName = cursor.getString(0);
		
		List<Category> categories = new ArrayList<Category>();
		for (Map.Entry<String, String> entry : getCategoriesForList(id).entrySet()) {
			Category category = new Category(entry.getValue(), entry.getKey(), getItemsForCategory(id, entry.getValue()));
			categories.add(category);
		}
		
		return new ListData(id, listName, categories);
	}
	
	protected List<ListData> getLists() {
		SQLiteDatabase db = this.getWritableDatabase();

		String selectQuery = "SELECT " + LIST_NAME + ", " + LIST_ID
					+ " FROM " + LIST_TABLE;
		Cursor cursor = db.rawQuery(selectQuery, null);
		List<ListData> lists = new ArrayList<ListData>();
		if (cursor.moveToFirst()) {
			do {
				ListData list = new ListData(cursor.getString(1), cursor.getString(0), null);
				lists.add(list);
			} while (cursor.moveToNext());
		}
		return lists;
	}
	
	
	
	protected void addCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CATEGORY_NAME, category.getName());
        db.insert(CATEGORIES_TABLE, null, values);
        db.close();
	}
	
	protected void addItem(Item item) {
		SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ITEM_NAME, item.getName());
        values.put(ITEM_CATEGORY, item.getCategoryId());
        db.insert(ITEM_TABLE, null, values);
        db.close();
        item.setSaved();
	}

	protected String addList(String listName, List<String> categories) {
		SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LIST_NAME, listName);
//        values.put(ITEM_CATEGORY, categoryId);
        Long foo = db.insert(LIST_TABLE, null, values);
        db.close();
        return foo.toString();
	}
	
	@Deprecated
	protected void updateListItems(String id, List<String> items, String UN_USED) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DELETE FROM " + LIST_ITEMS_TABLE
					+ " WHERE " + LIST_ITEMS_NAME
					+ " = " + id);
		
		InsertHelper ih = new InsertHelper(db, LIST_ITEMS_TABLE);
		final int list_items_name    = ih.getColumnIndex(LIST_ITEMS_NAME);
		final int list_items_item    = ih.getColumnIndex(LIST_ITEMS_ITEM);
		final int list_items_checked = ih.getColumnIndex(LIST_ITEMS_CHECKED); 
		
		try {
			for (String item: items) {
				
				ih.prepareForInsert();
				
				ih.bind(list_items_name, id);
				ih.bind(list_items_item, item);
				ih.bind(list_items_checked, Boolean.toString(false));
				
				ih.execute();
			}
		} finally {
			ih.close();
		}
        db.close();
	}

	protected void updateListItems(String id, List<Item> items) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DELETE FROM " + LIST_ITEMS_TABLE
					+ " WHERE " + LIST_ITEMS_NAME
					+ " = " + id);
		
		for (Item item: items) {
			ContentValues values = new ContentValues();
			values.put(LIST_ITEMS_NAME, id);
			values.put(LIST_ITEMS_ITEM, item.getId());
			values.put(LIST_ITEMS_CHECKED, Boolean.toString(item.isPacked()));
			db.insert(LIST_ITEMS_TABLE, null, values);
			item.setSaved();
		}
        db.close();
	}
	
	protected void updatePackedItems(String listId, List<Item> items) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		for (Item item: items) {
			// Cleardown the old values
			db.execSQL("DELETE FROM " + LIST_ITEMS_TABLE
					+ " WHERE " + LIST_ITEMS_NAME
					+ " = " + listId + " AND "
					+ LIST_ITEMS_ITEM + " = " + item.getId());
			// Save the new values
			ContentValues values = new ContentValues();
			values.put(LIST_ITEMS_NAME, listId);
			values.put(LIST_ITEMS_ITEM, item.getId());
			values.put(LIST_ITEMS_CHECKED, Boolean.toString(item.isPacked()));
			db.insert(LIST_ITEMS_TABLE, null, values);
			// Mark item as updated
			item.setSaved();
		}
        db.close();
	}
	
	private Map<String, String> getCategoriesForList(String listId) {
		SQLiteDatabase db = this.getWritableDatabase();

		String selectQuery = "SELECT DISTINCT " + CATEGORY_NAME + ", " + CATEGORY_ID
					+ " FROM " + CATEGORIES_TABLE + ", " + ITEM_TABLE + ", " + LIST_ITEMS_TABLE 
					+ " WHERE " + LIST_ITEMS_NAME + " = " + listId
					+ " AND " + LIST_ITEMS_ITEM + " = " + ITEM_ID
					+ " AND " + ITEM_CATEGORY + " = " + CATEGORY_ID;
		Cursor cursor = db.rawQuery(selectQuery, null);
        Map<String, String> categories = new HashMap<String, String>();
		if (cursor.moveToFirst()) {
			do { categories.put(cursor.getString(0), cursor.getString(1)); } while (cursor.moveToNext());
		}
		return categories;
	}
	
	protected List<Item> getItemsForCategory(String listId, String categoryId) {
		SQLiteDatabase db = this.getWritableDatabase();

		String selectQuery = "SELECT  " + ITEM_NAME + ", " + ITEM_ID + ", " + LIST_ITEMS_CHECKED
					+ " FROM " + CATEGORIES_TABLE + ", " + ITEM_TABLE + ", " + LIST_ITEMS_TABLE 
					+ " WHERE " + LIST_ITEMS_NAME + " = " + listId
					+ " AND " + CATEGORY_ID + " = " + categoryId
					+ " AND " + ITEM_CATEGORY + " = " + CATEGORY_ID
					+ " AND " + LIST_ITEMS_ITEM + " = " + ITEM_ID;
		Cursor cursor = db.rawQuery(selectQuery, null);
		List<Item> items= new ArrayList<Item>();
		if (cursor.moveToFirst()) {
			do {
				Item item = new Item(cursor.getString(1), cursor.getString(0), Boolean.parseBoolean(cursor.getString(2)), categoryId);
				items.add(item);
			} while (cursor.moveToNext());
		}
		return items;
	}
	
	protected void deleteList(String listId) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DELETE FROM " + LIST_ITEMS_TABLE
					+ " WHERE " + LIST_ITEMS_NAME
					+ " = " + listId);
		db.execSQL("DELETE FROM " + LIST_TABLE
				+ " WHERE " + LIST_ID
				+ " = " + listId);
		db.close();
	}

	protected void updateListName(String listId, String listName) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues args = new ContentValues();
		args.put(LIST_NAME, listName);
		db.update(LIST_TABLE, args, LIST_ID + " = " + listId, null);
		db.close();
	}

	protected void updateItem(Item item) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues args = new ContentValues();
		args.put(ITEM_NAME, item.getName());
		args.put(ITEM_CATEGORY, item.getCategoryId());
		db.update(ITEM_TABLE, args, ITEM_ID + " = " + item.getId(), null);
		db.close(); 
		item.setSaved();
	}
	
	// TODO: only updates name. Do I want to update items too?
	protected void updateCategory(Category category) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues args = new ContentValues();
		args.put(CATEGORY_NAME, category.getName());
		db.update(CATEGORIES_TABLE, args, CATEGORY_ID + " = " + category.getId(), null);
		db.close();
	}
}
