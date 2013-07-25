package com.logisome.metasome;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class logDatabaseHelper extends SQLiteOpenHelper {
	
	private static final String DB_NAME = "healthLog";
	private static final int VERSION = 1;
	
	private static final String TABLE_BODY = "body";
	private static final String TABLE_MIND = "mind";
	private static final String TABLE_SYMPTOMS = "symptoms";
	
	private static final String COLUMN_TIMESTAMP = "timestamp";
	private final static String COLUMN_HR = "hr";
	private final static String COLUMN_SYSTOLIC = "systolic";
	private final static String COLUMN_DIASTOLIC = "diastolic";
	private final static String COLUMN_WEIGHT = "weight";
	private final static String COLUMN_GLUCOSE = "glucose";
	
	private static final String COLUMN_SLEEP = "sleep";
	private static final String COLUMN_MOOD = "mood";
	private static final String COLUMN_ANXIETY = "anxiety";
	private static final String COLUMN_ENERGY = "energy";
	private static final String COLUMN_CONCENTRATION = "concentration";
	private static final String COLUMN_APPETITE = "appetite";
	private static final String COLUMN_LIBIDO = "libido";
	
	private static final String COLUMN_SOB = "sob";
	private static final String COLUMN_EDEMA = "edema";
	private static final String COLUMN_COUGH = "cough";
	private static final String COLUMN_SPEED = "speed";
	private static final String COLUMN_PAIN = "pain";
	private static final String COLUMN_INHALER = "inhaler";
	private static final String COLUMN_FATIGUE = "fatigue";
	
	public logDatabaseHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		//Create three new tables
		String create_query1 = "create table "+TABLE_BODY+" (_id integer primary key autoincrement, timestamp long, "
				+"hr int, systolic int, diastolic int, weight int, glucose int)";
		
		String create_query2 = "create table "+TABLE_MIND+" (_id integer primary key autoincrement, timestamp integer, "
			+"sleep int, mood int, anxiety int, energy int, concentration int, appetite int, libido int)";
		
		String create_query3 = "create table "+TABLE_SYMPTOMS+" (_id integer primary key autoincrement, timestamp integer, "
				+"sob int, edema int, cough int, speed int, pain int, short inhaler, fatigue int)";
			
		db.execSQL(create_query1);
		db.execSQL(create_query2);
		db.execSQL(create_query3);
		
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//Implement schema change and data massage here when upgrading
	}
	
	public long insertBodyFragment(BodyPanel bodyPanel) {
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_TIMESTAMP, bodyPanel.getTimestamp());
		cv.put(COLUMN_HR, bodyPanel.getHr());
		cv.put(COLUMN_SYSTOLIC,	bodyPanel.getSystolic());
		cv.put(COLUMN_DIASTOLIC, bodyPanel.getDiastolic());
		cv.put(COLUMN_WEIGHT, bodyPanel.getWeight());
		cv.put(COLUMN_GLUCOSE, bodyPanel.getGlucose());
		
		return getWritableDatabase().insert(TABLE_BODY, null, cv);
	}
	
	public long insertMindFragment(MindPanel mindPanel) {
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_TIMESTAMP, mindPanel.getTimestamp());
		cv.put(COLUMN_SLEEP, mindPanel.getSleep());
		cv.put(COLUMN_MOOD,	mindPanel.getMood());
		cv.put(COLUMN_ANXIETY, mindPanel.getAnxiety());
		cv.put(COLUMN_ENERGY, mindPanel.getEnergy());
		cv.put(COLUMN_CONCENTRATION, mindPanel.getConcentration());
		cv.put(COLUMN_APPETITE, mindPanel.getAppetite());
		cv.put(COLUMN_LIBIDO, mindPanel.getLibido());
		
		return getWritableDatabase().insert(TABLE_MIND, null, cv);
	}
	
	public long insertSymptomFragment(SymptomPanel symptomPanel) {
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_TIMESTAMP, symptomPanel.getTimestamp());
		cv.put(COLUMN_SOB, symptomPanel.getSob());
		cv.put(COLUMN_EDEMA,	symptomPanel.getEdema());
		cv.put(COLUMN_COUGH, symptomPanel.getCough());
		cv.put(COLUMN_SPEED, symptomPanel.getSpeed());
		cv.put(COLUMN_PAIN, symptomPanel.getPain());
		cv.put(COLUMN_FATIGUE, symptomPanel.getFatigue());
		cv.put(COLUMN_INHALER, symptomPanel.getInhaler());
		
		return getWritableDatabase().insert(TABLE_BODY, null, cv);
	}
}

