package com.timothy.moll.lets.go;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "lets_go_data";
	private static final int DB_VERSION = 1;
	
	private static final String CATEGORIES_TABLE = "categories";
	private static final String CATEGORY_NAME    = "category";
	private static final String CATEGORY_ID      = "id";
	
	private static final String ITEM_TABLE    = "items";
	private static final String ITEM_NAME     = "item";
	private static final String ITEM_CATEGORY = "category_id";
	private static final String ITEM_ID       = "id";
	
	private static final String LIST_TABLE = "lists";
	private static final String LIST_NAME  = "list";
	private static final String LIST_ID    = "id";
	
	private static final String LIST_ITEMS_TABLE  = "list_items";
	private static final String LIST_ITEMS_NAME   = "list_id";
	private static final String LIST_ITEMS_ITEM   = "item_id";
	private static final String LIST_ITEMS_CHECKED = "checked_state";
	private static final String LIST_ITEMS_ID     = "id";
	
	
	public DBHelper(Context context) {
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
	
	public CategoriesAndItems getCategoriesAndItems() {
		SQLiteDatabase db = this.getWritableDatabase();
		
		
		String selectQuery = "SELECT "+CATEGORY_ID + ", "+CATEGORY_NAME+" FROM " + CATEGORIES_TABLE;       
        Cursor cursor = db.rawQuery(selectQuery, null);
        Map<String, String> categories = new HashMap<String, String>();
		if (cursor.moveToFirst()) {
            do {
            	// Returns Category, ID
            	categories.put(cursor.getString(1), cursor.getString(0));
            } while (cursor.moveToNext());
        }

		selectQuery = "SELECT " + CATEGORY_NAME + ", " + ITEM_TABLE +"." +ITEM_ID
						+ " FROM " + CATEGORIES_TABLE + ", " + ITEM_TABLE 
						+" WHERE " + CATEGORIES_TABLE + "." + CATEGORY_ID 
						+ " = " + ITEM_TABLE + "." + ITEM_CATEGORY;
        cursor = db.rawQuery(selectQuery, null);
        Map<String, String> itemRelationship = new HashMap<String, String>();
		if (cursor.moveToFirst()) {
			do {
				// returning item, category
				itemRelationship.put(cursor.getString(1), cursor.getString(0));
			} while (cursor.moveToNext());
		}
		
		
		selectQuery = "SELECT " + ITEM_ID + ", " + ITEM_NAME
				+ " FROM " + ITEM_TABLE;
		cursor = db.rawQuery(selectQuery, null);
		Map<String, String> items = new HashMap<String, String>();
		if (cursor.moveToFirst()) {
			do {
				// returning id, item
				items.put(cursor.getString(0), cursor.getString(1));
			} while (cursor.moveToNext());
		}
		
		selectQuery = "SELECT " + LIST_NAME
					+ " FROM " + LIST_TABLE;
		cursor = db.rawQuery(selectQuery, null);
        List<String> lists = new ArrayList<String>();
		if (cursor.moveToFirst()) {
			do { lists.add(cursor.getString(0)); } while (cursor.moveToNext());
		}
		
		return  new CategoriesAndItems(categories, items, itemRelationship, lists, this);
	}
	
	public void addCategory(String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CATEGORY_NAME, category);
        db.insert(CATEGORIES_TABLE, null, values);
        db.close();
	}
	
	public void addItem(String item, String categoryId) {
		SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ITEM_NAME, item);
        values.put(ITEM_CATEGORY, categoryId);
        db.insert(ITEM_TABLE, null, values);
        db.close();
	}

	public String addList(String listName, List<String> categories) {
		SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LIST_NAME, listName);
//        values.put(ITEM_CATEGORY, categoryId);
        Long foo = db.insert(LIST_TABLE, null, values);
        db.close();
        return foo.toString();
	}
	
	public void updateListItems(String id, List<String> items) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DELETE FROM " + LIST_ITEMS_TABLE
					+ " WHERE " + LIST_ITEMS_NAME
					+ " = " + id);
		
		for (String item: items) {
			ContentValues values = new ContentValues();
			values.put(LIST_ITEMS_NAME, id);
			values.put(LIST_ITEMS_ITEM, item);
			values.put(LIST_ITEMS_CHECKED, 0);
			db.insert(LIST_ITEMS_TABLE, null, values);
		}
        db.close();
	}
	
}
