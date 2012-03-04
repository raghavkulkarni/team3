package com.team3.allindiabankdir.database;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.team3.allindiabankdir.utils.AppConstants;

public class AllIndiaBanksDB extends SQLiteOpenHelper implements AppConstants {
	// log tag name
	private static final String TAG = "AllIndiaBanksDB";
	// The Android's default system path of your application database.
	private static final String DB_PATH = "/data/data/com.team3.allindiabankdir/databases/";
	// Db Name
	private static final String DATABASE_NAME = "all_india_bank_info.db";
	// Db Version
	private static final int DATABASE_VERSION = 1;

	//private AllIndiaBanksDB dbInstance;
	private SQLiteDatabase db;
	private Context appContext;
	
	public AllIndiaBanksDB(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
		if (INFO) {
			Log.i(TAG, INIT);
		}
		
		/*if (dbInstance == null) {
			dbInstance = new AllIndiaBanksDB(context);
		}*/
		this.appContext = context;
		//connect();
	}
	
	public SQLiteDatabase connect() {
		if (INFO) {
			Log.i(TAG, INIT);
		}
		
		//dbInstance = new AllIndiaBanksDB(context);
		
		SQLiteDatabase openDB = null;
		SQLiteDatabase readDB = null;		
		
		try {
			openDB = SQLiteDatabase.openDatabase(DB_PATH + DATABASE_NAME, null, SQLiteDatabase.OPEN_READONLY);
		
			readDB = this.getReadableDatabase();
		
			copyDataBase();
		} catch (Exception e) {
			if (ERROR) {
				Log.e(TAG, e.getMessage(), e);
			}
		} finally {
			openDB.close();
			readDB.close();
		}
		
		db = open();
		return db;
	}
	
	public SQLiteDatabase open() {
		if (INFO) {
			Log.i(TAG, INIT);
		}
		
		if (! (db.isOpen())) {
			db = this.getWritableDatabase();
		} 
		return db;
	}
	
	@Override
	public synchronized void close() {
		super.close();
		db.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		if (INFO) {
			Log.i(TAG, "onCreate");
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (INFO) {
			Log.i(TAG, "onUpgrade");
		}
	}

	/**
	 * Copies your database from your local assets-folder to the just
	 * created empty database in the system folder, from where it can be
	 * accessed and handled. This is done by transfering bytestream.
	 * @throws Exception 
	 * */
	private void copyDataBase() throws Exception {
		if (INFO) {
			Log.i(TAG, INIT);
		}
		
		// Open your local db as the input stream
		InputStream myInput = null;
		OutputStream myOutput = null;
		
		try {
			myInput = appContext.getAssets().open(DATABASE_NAME);
			// Path to the just created empty db
			String outFileName = DB_PATH + DATABASE_NAME;
			// Open the empty db as the output stream
			myOutput = new FileOutputStream(outFileName);
			// transfer bytes from the inputfile to the outputfile
			byte[] buffer = new byte[1024];
			int length;
			while ((length = myInput.read(buffer)) > 0) {
				myOutput.write(buffer, 0, length);
			}
			myOutput.flush();
		} catch (IOException e) {
			if (ERROR) {
				Log.e(TAG, e.getMessage(), e);
			}
			
			throw e;
		} finally {
			// Close the streams
			try {
				myOutput.close();
				myInput.close();
			} catch (IOException e) {
				if (ERROR) {
					Log.e(TAG, e.getMessage(), e);
				}
				
				throw e;
			}
		}
	}
}
