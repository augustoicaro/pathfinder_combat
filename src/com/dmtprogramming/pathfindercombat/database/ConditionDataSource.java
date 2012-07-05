package com.dmtprogramming.pathfindercombat.database;

import java.util.ArrayList;
import java.util.List;

import com.dmtprogramming.pathfindercombat.database.DatabaseHelper;
import com.dmtprogramming.pathfindercombat.Log;
import com.dmtprogramming.pathfindercombat.PFCombatApplication;
import com.dmtprogramming.pathfindercombat.models.Condition;
import com.dmtprogramming.pathfindercombat.models.PFCharacter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ConditionDataSource {
	private static final String TAG = "PFCombat:ConditionDataSource";
	
	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;
	private Context context;
	
	private String[] allColumns = { DatabaseHelper._c_conditions_id,
			DatabaseHelper._c_conditions_character_id,
			DatabaseHelper._c_conditions_name,
			DatabaseHelper._c_conditions_duration
			};
	
	public ConditionDataSource(Context context) {
		this.context = context;
		dbHelper = new DatabaseHelper(context);
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}
	
	public Condition findCondition(long _id) {
		Cursor cursor = database.query(DatabaseHelper._t_conditions, allColumns, DatabaseHelper._c_conditions_id + " = " + _id, null, null, null, null);
		cursor.moveToFirst();
		if (cursor.getCount() == 1) {
			Log.d(TAG, "Condition found with id = " + _id);
			return cursorToCondition(cursor);
		}
		Log.d(TAG, "Condition not found with id = " + _id);
		return new Condition();
	}
	
	public Condition createCondition(PFCharacter character, String name, long duration) {
		ContentValues values = new ContentValues();
		
		values.put(DatabaseHelper._c_conditions_character_id, character.getId());
		values.put(DatabaseHelper._c_conditions_name, name);
		values.put(DatabaseHelper._c_conditions_duration, duration);
		
		long insertId = database.insert(DatabaseHelper._t_conditions, null, values);
		Cursor cursor = database.query(DatabaseHelper._t_conditions, allColumns, DatabaseHelper._c_conditions_id + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		Condition condition = cursorToCondition(cursor);
		cursor.close();
		Log.d(TAG, "Condition created with id = " + insertId + " and name = " + name);
		
		updateCharacter(character);
		
		return condition;
	}
	
	public void updateCondition(PFCharacter character, Condition condition) {
		ContentValues values = new ContentValues();
		
		values.put(DatabaseHelper._c_conditions_character_id, condition.getCharacterId());
		values.put(DatabaseHelper._c_conditions_name, condition.getName());
		values.put(DatabaseHelper._c_conditions_duration, condition.getDuration());
		
		int num = database.update(DatabaseHelper._t_conditions, values, DatabaseHelper._c_conditions_id + " = " + condition.getId(), null);
		
		Log.d(TAG, "updated Condition id = " + condition.getId() + " rows affected = " + num);
		
		updateCharacter(character);
	}
	
	public void deleteCondition(PFCharacter character, Condition condition) {
		long id = condition.getId();
		Log.d(TAG, "Condition deleted with id = " + id);
		database.delete(DatabaseHelper._t_conditions, DatabaseHelper._c_conditions_id + " = " + id, null);
		
		updateCharacter(character);
	}
	
	public void updateCharacter(PFCharacter character) {
		PFCombatApplication app = (PFCombatApplication) context.getApplicationContext();
		app.getCharacterDataSource().reloadPFCharacter(character);		
	}
	
	private Condition cursorToCondition(Cursor cursor) {
		Condition condition = new Condition();
		
		condition.setId(cursor.getLong(0));
		condition.setCharacterId(cursor.getLong(1));
		condition.setName(cursor.getString(2));
		condition.setDuration(cursor.getLong(3));
		
		return condition;
	}

	public List<Condition> getConditionsForCharacter(long character_id) {
		List<Condition> conditions = new ArrayList<Condition>();
		
		Cursor cursor = database.query(DatabaseHelper._t_conditions, allColumns, DatabaseHelper._c_conditions_character_id + " = " + character_id, null, null, null, DatabaseHelper._c_conditions_duration);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Condition condition = cursorToCondition(cursor);
			conditions.add(condition);
			cursor.moveToNext();
		}
		
		cursor.close();
		return conditions;
	}
}
