package com.team3.allindiabankdir.database.adapters;

import com.team3.allindiabankdir.database.AllIndiaBanksDB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BanksAdapter implements Adapter {
	private static final String TABLE = "BANK_INFO_TABLE";
	
	public static final String COL_BANK_NAME = "BANK_NAME";
	public static final String COL_STATE = "STATE";
	public static final String COL_DISTRICT = "DISTRICT";
	public static final String COL_BRANCH = "BRANCH";
	public static final String COL_ADDRESS = "ADDRESS";
	public static final String COL_PHONE = "PHONE";
	public static final String COL_IFSC = "IFSC_CODE";
	public static final String COL_MICR = "MICR_CODE";
	
	private SQLiteDatabase sqLiteDatabase;
	
	public BanksAdapter(Context context) {
		AllIndiaBanksDB allIndiaBanksDB = new AllIndiaBanksDB(context);
		this.sqLiteDatabase = allIndiaBanksDB.connect();
	}

	public Cursor fetchAll() {
		throw new UnsupportedOperationException();
	}

	public Cursor fetchContains(String selection) {
		String[] columns = {COL_BANK_NAME,
							COL_STATE,
							COL_DISTRICT,
							COL_BRANCH,
							COL_ADDRESS,
							COL_PHONE,
							COL_IFSC,
							COL_MICR};
		return fetchContains(columns, selection);
	}

	public Cursor fetchContains(String[] columns, String selection) {
		Cursor cursor = sqLiteDatabase.query(TABLE, columns, selection, null, null, null, null);
		return cursor;
	}
}
