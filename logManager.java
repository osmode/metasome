package com.logisome.metasome;

import android.content.Context;
import android.content.SharedPreferences;

public class logManager {
	private static final String TAG = "logTag";
	
	private static final String PREFS_FILE = "logs";
	
	private static logManager sLogManager;
	private Context mAppContext;
	private SharedPreferences mPrefs;
	private logDatabaseHelper mHelper;
	
	private logManager(Context appContext) {
		mAppContext = appContext;
		mHelper = new logDatabaseHelper(mAppContext);
		mPrefs = mAppContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
		
	}
	
	public static logManager get(Context c) {
		if (sLogManager == null) {
			//Use application context to avoid leaking activities
			sLogManager = new logManager(c.getApplicationContext());
		}
		return sLogManager;
	}
	
	public void insertLog(BodyPanel bodyPanel) {
		mHelper.insertBodyFragment(bodyPanel);
	}
	
	public void insertLog(MindPanel mindPanel) {
		mHelper.insertMindFragment(mindPanel);
	}
	
	public void insertLog(SymptomPanel symptomPanel) {
		mHelper.insertSymptomFragment(symptomPanel);
	}
	
	
}
