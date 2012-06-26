package com.dmtprogramming.pathfindercombat;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class PFCharacterDataSource {
	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;
	private String[] allColumns = { DatabaseHelper._c_characters_id,
			DatabaseHelper._c_characters_name };
	
	public PFCharacterDataSource(Context context) {
		dbHelper = new DatabaseHelper(context);
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}
	
	public PFCharacter findCharacter(int id) {
		Cursor cursor = database.query(DatabaseHelper._t_characters, allColumns, DatabaseHelper._c_characters_id + " = " + id, null, null, null, null);
		cursor.moveToFirst();
		if (cursor.getCount() == 1) {
			return cursorToPFCharacter(cursor);
		}
		return new PFCharacter();
	}
	
	private PFCharacter cursorToPFCharacter(Cursor cursor) {
		PFCharacter cha = new PFCharacter();
		
		cha.setId(cursor.getLong(0));
		cha.setName(cursor.getString(1));
		
		return cha;
	}
}
