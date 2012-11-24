package com.timothy.moll.lets.go.views.style;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WidgetFactory {
	
	public static float HEADING_TEXT_ELEMENT_SIZE = 25;
	public static float BODY_TEXT_ELEMENT_SIZE = 20;

	public static int TEXT_COLOUR = 0xFF000000;
	
	public static TextView getListTableTextView(Context context) {
		TextView foo = new TextView(context);
		foo.setPadding(20, 0, 0, 20);
		foo.setBackgroundColor(0x10030303);
		foo.setTextSize(HEADING_TEXT_ELEMENT_SIZE);
		foo.setWidth(context.getResources().getDisplayMetrics().widthPixels);
		return foo;
	}
	
	public static TextView getRunListCategoryTextView(Context context, String categoryName) {
		TextView foo = new TextView(context);
		foo.setTextSize(HEADING_TEXT_ELEMENT_SIZE);
		foo.setTextColor(TEXT_COLOUR);
		foo.setPadding(20, 0, 0, 5);
		foo.setText(categoryName);
		return foo;
	}

	public static TextView getRunListItemTextView(Context context, String itemName) {
		TextView foo = new TextView(context);
		foo.setTextSize(BODY_TEXT_ELEMENT_SIZE);
		foo.setText(itemName);
		foo.setTextColor(TEXT_COLOUR);
		foo.setPadding(35, 0, 0, 0);
		return foo;
	}

	public static View getRuler(Context context) {
		View ruler = new View(context);
		ruler.setBackgroundColor(0xFF0000FF);
		ruler.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2));
		return ruler;
	}
	
	public static LinearLayout getRunListCategoryLayout(Context context) {
		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setPadding(0, 0, 0, 30);
		return layout;
	}

	public static LinearLayout getCategoryListItemLayout(Context context) {
		LinearLayout layout = new LinearLayout(context);
//		layout.setLayoutParams(new LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setPadding(0, 0, 0, 30);
		return layout;
	}
	
	public static TextView getEmptyListLabel(Context context, String text) {
		TextView foo = new TextView(context);
		foo.setPadding(20, 0, 0, 0);
		foo.setText(text);
		return foo;
	}
}