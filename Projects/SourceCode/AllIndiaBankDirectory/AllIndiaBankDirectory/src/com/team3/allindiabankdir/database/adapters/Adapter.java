package com.team3.allindiabankdir.database.adapters;

import android.database.Cursor;

public interface Adapter {
	public Cursor fetchAll();
	public Cursor fetchContains(String[] columns, String field);
}
