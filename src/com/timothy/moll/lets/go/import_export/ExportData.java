package com.timothy.moll.lets.go.import_export;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.widget.Toast;

public class ExportData {
	
	private final String FILE_NAME = "data.xml";
	private final Context context;
	
	public ExportData(Context context) {
		this.context = context;
	}
	
 	public void export() {
 		try {
 			FileOutputStream outputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
 			outputStream.write("Test".getBytes());
 			outputStream.close();

 		} catch (IOException e) {
 			e.printStackTrace();
 		}
 	}
 	
 	public void load() {
 		try {
 			FileInputStream inputStream = context.openFileInput(FILE_NAME);
 			InputStreamReader ir = new InputStreamReader(inputStream);
 			BufferedReader br = new BufferedReader(ir);
 			
 			String line = br.readLine();
 			while (line != null) {
 	 			line = br.readLine();
 	 			Toast toast = Toast.makeText(context, line, Toast.LENGTH_LONG);
 	 			toast.show();
 			}

 			inputStream.close();

 		} catch (IOException e) {
 			e.printStackTrace();
 		}
 	}

}
