package com.dmtprogramming.pathfindercombat.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final int DBVERSION = 7;
	
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
	public static final String _c_characters_size = "size";
	public static final String _c_characters_weapon_damage = "weapon_damage";
	public static final String _c_characters_weapon_plus = "weapon_plus";
	public static final String _c_characters_unarmed = "unarmed";
	public static final String _c_characters_flurry_of_blows = "flurry_of_blows";
	public static final String _c_characters_daily_total = "daily_total";
	public static final String _c_characters_daily_current = "daily_current";
	public static final String _c_characters_daily_title = "daily_title";
	public static final String _c_characters_critical_multiplier = "critical_multiplier";
	
	public static final String _t_conditions = "conditions";
	public static final String _c_conditions_id = "_id";
	public static final String _c_conditions_character_id = "character_id";
	public static final String _c_conditions_name = "name";
	public static final String _c_conditions_duration = "duration";
	
	public DatabaseHelper(Context context) {
		super(context, _dbName, null, DBVERSION);
		Log.v(TAG, String.format("%s created", TAG));
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.v(TAG, "onCreate()");
		String create_character_table = String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s BOOLEAN, %s BOOLEAN, %s BOOLEAN, %s TEXT, %s TEXT, %s INTEGER, %s BOOLEAN, %s BOOLEAN, %s INTEGER, %s INTEGER, %s TEXT, %s INTEGER)",
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
				_c_characters_weapon_finesse,
				_c_characters_size,
				_c_characters_weapon_damage,
				_c_characters_weapon_plus,
				_c_characters_unarmed,
				_c_characters_flurry_of_blows,
				_c_characters_daily_total,
				_c_characters_daily_current,
				_c_characters_daily_title,
				_c_characters_critical_multiplier
		);
		Log.v(TAG, String.format("SQL: %s", create_character_table));
		db.execSQL(create_character_table);
		
		String create_condition_table = String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY, %s INTEGER, %s TEXT, %s INTEGER)",
				_t_conditions,
				_c_conditions_id,
				_c_conditions_character_id,
				_c_conditions_name,
				_c_conditions_duration
		);
		Log.v(TAG, String.format("SQL: %s", create_condition_table));
		db.execSQL(create_condition_table);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.v(TAG, String.format("onUpgrade(%d, %d)", oldVersion, newVersion));
		while (oldVersion < newVersion) {
			Log.v(TAG, String.format("upgrading database from version %d to %d", oldVersion, oldVersion + 1));
			doUpgrade(db, oldVersion);
			oldVersion++;
		}
	}
	
	private void doUpgrade(SQLiteDatabase db, int oldVersion) {
		//String upgrade_sql;
		if (oldVersion == 1) {
			addCharacterClass(db);
		} else if (oldVersion == 2) {
			addSizeAndWeaponColumns(db);
		} else if (oldVersion == 3) {
			addFlurryOfBlowsColumn(db);
		} else if (oldVersion == 4) {
			addDailyColumns(db);
		} else if (oldVersion == 5) {
			addCriticalMultiplerColumn(db);
		} else if (oldVersion == 6) {
			addConditionsTable(db);
		} else {
			Log.v(TAG, String.format("unknown upgrade to db from version %d", oldVersion));
		}
	}

	private void addCharacterClass(SQLiteDatabase db) {
		String upgrade_sql;
		upgrade_sql = String.format("ALTER TABLE %s ADD COLUMN %s TEXT", _t_characters, _c_characters_character_class);
		db.execSQL(upgrade_sql);
	}
	
	private void addSizeAndWeaponColumns(SQLiteDatabase db) {
		String upgrade_sql;
		upgrade_sql = String.format("ALTER TABLE %s ADD COLUMN %s TEXT", _t_characters, _c_characters_size);
		db.execSQL(upgrade_sql);
		upgrade_sql = String.format("ALTER TABLE %s ADD COLUMN %s TEXT", _t_characters, _c_characters_weapon_damage);
		db.execSQL(upgrade_sql);
		upgrade_sql = String.format("ALTER TABLE %s ADD COLUMN %s INTEGER", _t_characters, _c_characters_weapon_plus);
		db.execSQL(upgrade_sql);
		upgrade_sql = String.format("ALTER TABLE %s ADD COLUMN %s BOOLEAN", _t_characters, _c_characters_unarmed);
		db.execSQL(upgrade_sql);
	}
	
	private void addFlurryOfBlowsColumn(SQLiteDatabase db) {
		String upgrade_sql;
		upgrade_sql = String.format("ALTER TABLE %s ADD COLUMN %s BOOLEAN", _t_characters, _c_characters_flurry_of_blows);
		db.execSQL(upgrade_sql);
	}
	
	private void addDailyColumns(SQLiteDatabase db) {
		String upgrade_sql;
		upgrade_sql = String.format("ALTER TABLE %s ADD COLUMN %s INTEGER", _t_characters, _c_characters_daily_total);
		db.execSQL(upgrade_sql);
		upgrade_sql = String.format("ALTER TABLE %s ADD COLUMN %s INTEGER", _t_characters, _c_characters_daily_current);
		db.execSQL(upgrade_sql);
		upgrade_sql = String.format("ALTER TABLE %s ADD COLUMN %s TEXT", _t_characters, _c_characters_daily_title);
		db.execSQL(upgrade_sql);		
	}

	private void addCriticalMultiplerColumn(SQLiteDatabase db) {
		String upgrade_sql;
		upgrade_sql = String.format("ALTER TABLE %s ADD COLUMN %s INTEGER", _t_characters, _c_characters_critical_multiplier);
		db.execSQL(upgrade_sql);
	}
	

	private void addConditionsTable(SQLiteDatabase db) {
		String create_condition_table = String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY, %s INTEGER, %s TEXT, %s INTEGER)",
				_t_conditions,
				_c_conditions_id,
				_c_conditions_character_id,
				_c_conditions_name,
				_c_conditions_duration
		);
		Log.v(TAG, String.format("SQL: %s", create_condition_table));
		db.execSQL(create_condition_table);
	}
}
