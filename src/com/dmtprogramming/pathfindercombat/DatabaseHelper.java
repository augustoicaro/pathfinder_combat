package com.dmtprogramming.pathfindercombat;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final int DBVERSION = 2;
	
	private static final String TAG = "PFCombat:DatabaseHelper";
	
	private static final String _dbName = "PFCombat";

	public static final String _c_created_at = "created_at";
	public static final String _c_updated_at = "updated_at";
	
	public static final String _t_characters = "characters";
	public static final String _c_characters_id = "_id";
	public static final String _c_characters_name = "name";
	public static final String _c_characters_character_class = "character_class";
	public static final String _c_characters_player = "player";
	public static final String _c_characters_level = "level";
	public static final String _c_characters_monk_level = "monk_level";
	public static final String _c_characters_strength = "str";
	public static final String _c_characters_dexterity = "dex";
	public static final String _c_characters_constitution = "con";
	public static final String _c_characters_intelligence = "intel";
	public static final String _c_characters_wisdom = "wis";
	public static final String _c_characters_charisma = "cha";
	public static final String _c_characters_weapon_focus = "weapon_focus";
	public static final String _c_characters_power_attack = "power_attack";
	public static final String _c_characters_weapon_finesse = "weapon_finesse";
	
	public DatabaseHelper(Context context) {
		super(context, _dbName, null, DBVERSION);
		Log.v(TAG, String.format("%s created", TAG));
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.v(TAG, "onCreate()");
		String create_character_table = String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s BOOLEAN, %s BOOLEAN, %s BOOLEAN)",
				_t_characters,
				_c_characters_id,
				_c_characters_name,
				_c_characters_player,
				_c_characters_character_class,
				_c_characters_level,
				_c_characters_monk_level,
				_c_characters_strength,
				_c_characters_dexterity,
				_c_characters_constitution,
				_c_characters_intelligence,
				_c_characters_wisdom,
				_c_characters_charisma,
				_c_characters_weapon_focus,
				_c_characters_power_attack,
				_c_characters_weapon_finesse
		);
		Log.v(TAG, String.format("SQL: %s", create_character_table));
		db.execSQL(create_character_table);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.v(TAG, "onUpgrade()");
		String drop_characters_table = String.format("DROP TABLE IF EXISTS %s", _t_characters);
		Log.v(TAG, String.format("SQL: %s", drop_characters_table));
		db.execSQL(drop_characters_table);
		
		onCreate(db);
	}

}
