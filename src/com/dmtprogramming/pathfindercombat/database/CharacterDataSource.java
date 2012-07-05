package com.dmtprogramming.pathfindercombat.database;

import java.util.ArrayList;
import java.util.List;

import com.dmtprogramming.pathfindercombat.database.DatabaseHelper;
import com.dmtprogramming.pathfindercombat.PFCombatApplication;
import com.dmtprogramming.pathfindercombat.models.PFCharacter;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.dmtprogramming.pathfindercombat.*;

public class CharacterDataSource {
	private static final String TAG = "PFCombat:PFCharacterDataSource";
	
	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;
	private PFCombatApplication _app;
	
	private String[] allColumns = { DatabaseHelper._c_characters_id,
			DatabaseHelper._c_characters_name,
			DatabaseHelper._c_characters_character_class,
			DatabaseHelper._c_characters_player,
			DatabaseHelper._c_characters_level,
			DatabaseHelper._c_characters_monk_level,
			DatabaseHelper._c_characters_strength,
			DatabaseHelper._c_characters_dexterity,
			DatabaseHelper._c_characters_constitution,
			DatabaseHelper._c_characters_intelligence,
			DatabaseHelper._c_characters_wisdom,
			DatabaseHelper._c_characters_charisma,
			DatabaseHelper._c_characters_weapon_focus,
			DatabaseHelper._c_characters_power_attack,
			DatabaseHelper._c_characters_weapon_finesse,
			DatabaseHelper._c_characters_size,
			DatabaseHelper._c_characters_weapon_damage,
			DatabaseHelper._c_characters_weapon_plus,
			DatabaseHelper._c_characters_unarmed,
			DatabaseHelper._c_characters_flurry_of_blows,
			DatabaseHelper._c_characters_daily_total,
			DatabaseHelper._c_characters_daily_current,
			DatabaseHelper._c_characters_daily_title,
			DatabaseHelper._c_characters_critical_multiplier
			};
	
	public CharacterDataSource(PFCombatApplication app) {
		dbHelper = new DatabaseHelper(app);
		_app = app;
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}
	
	public PFCharacter findCharacter(long _id) {
		Cursor cursor = database.query(DatabaseHelper._t_characters, allColumns, DatabaseHelper._c_characters_id + " = " + _id, null, null, null, null);
		cursor.moveToFirst();
		if (cursor.getCount() == 1) {
			Log.d(TAG, "PFCharacter found with id = " + _id);
			return cursorToPFCharacter(cursor);
		}
		Log.d(TAG, "PFCharacter not found with id = " + _id);
		return new PFCharacter();
	}
	
	public PFCharacter createPFCharacter(String name) {
		ContentValues values = new ContentValues();
		
		values.put(DatabaseHelper._c_characters_character_class, "Paladin");
		values.put(DatabaseHelper._c_characters_player, "Player");
		values.put(DatabaseHelper._c_characters_name, name);
		values.put(DatabaseHelper._c_characters_level, 1);
		values.put(DatabaseHelper._c_characters_monk_level, 0);
		values.put(DatabaseHelper._c_characters_strength, 10);
		values.put(DatabaseHelper._c_characters_dexterity, 10);
		values.put(DatabaseHelper._c_characters_constitution, 10);
		values.put(DatabaseHelper._c_characters_intelligence, 10);
		values.put(DatabaseHelper._c_characters_wisdom, 10);
		values.put(DatabaseHelper._c_characters_charisma, 10);
		values.put(DatabaseHelper._c_characters_weapon_finesse, false);
		values.put(DatabaseHelper._c_characters_power_attack, false);
		values.put(DatabaseHelper._c_characters_weapon_focus, false);
		values.put(DatabaseHelper._c_characters_size, "Medium");
		values.put(DatabaseHelper._c_characters_weapon_damage, "1d6");
		values.put(DatabaseHelper._c_characters_weapon_plus, 0);
		values.put(DatabaseHelper._c_characters_unarmed, false);
		values.put(DatabaseHelper._c_characters_flurry_of_blows, false);
		values.put(DatabaseHelper._c_characters_daily_total, 0);
		values.put(DatabaseHelper._c_characters_daily_current, 0);
		values.put(DatabaseHelper._c_characters_daily_title, "Daily Power");
		values.put(DatabaseHelper._c_characters_critical_multiplier, "x2");
		
		long insertId = database.insert(DatabaseHelper._t_characters, null, values);
		Cursor cursor = database.query(DatabaseHelper._t_characters, allColumns, DatabaseHelper._c_characters_id + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		PFCharacter cha = cursorToPFCharacter(cursor);
		cursor.close();
		Log.d(TAG, "PFCharacter created with id = " + insertId + " and name = " + name);
		return cha;
	}
	
	public void updatePFCharacter(PFCharacter cha) {
		ContentValues values = new ContentValues();
		
		values.put(DatabaseHelper._c_characters_character_class, cha.getCharacterClass());
		values.put(DatabaseHelper._c_characters_player, cha.getPlayer());
		values.put(DatabaseHelper._c_characters_name, cha.getName());
		values.put(DatabaseHelper._c_characters_level, cha.getLevel());
		values.put(DatabaseHelper._c_characters_monk_level, cha.getMonkLevel());
		values.put(DatabaseHelper._c_characters_strength, cha.getStr());
		values.put(DatabaseHelper._c_characters_dexterity, cha.getDex());
		values.put(DatabaseHelper._c_characters_constitution, cha.getCon());
		values.put(DatabaseHelper._c_characters_intelligence, cha.getInt());
		values.put(DatabaseHelper._c_characters_wisdom, cha.getWis());
		values.put(DatabaseHelper._c_characters_charisma, cha.getCha());
		values.put(DatabaseHelper._c_characters_weapon_finesse, false);
		values.put(DatabaseHelper._c_characters_power_attack, cha.getPowerAttack());
		values.put(DatabaseHelper._c_characters_weapon_focus, cha.getWeaponFocus());
		values.put(DatabaseHelper._c_characters_size, cha.getSize());
		values.put(DatabaseHelper._c_characters_weapon_damage, cha.getWeaponDamage());
		values.put(DatabaseHelper._c_characters_weapon_plus, cha.getWeaponPlus());
		values.put(DatabaseHelper._c_characters_unarmed, cha.getUnarmed());
		values.put(DatabaseHelper._c_characters_flurry_of_blows, cha.getFlurryOfBlows());
		values.put(DatabaseHelper._c_characters_daily_total, cha.getDailyTotal());
		values.put(DatabaseHelper._c_characters_daily_current, cha.getDailyCurrent());
		values.put(DatabaseHelper._c_characters_daily_title, cha.getDailyTitle());
		values.put(DatabaseHelper._c_characters_critical_multiplier, cha.getCriticalMultiplier());
		
		int num = database.update(DatabaseHelper._t_characters, values, DatabaseHelper._c_characters_id + " = " + cha.getId(), null);
		
		Log.d(TAG, "updated PFCharacter id = " + cha.getId() + " rows affected = " + num);
	}
	
	public void deletePFCharacter(PFCharacter cha) {
		long id = cha.getId();
		Log.d(TAG, "PFCharacter deleted with id = " + id);
		database.delete(DatabaseHelper._t_characters, DatabaseHelper._c_characters_id + " = " + id, null);
	}
	
	public void reloadPFCharacter(PFCharacter cha) {
		Cursor cursor = database.query(DatabaseHelper._t_characters, allColumns, DatabaseHelper._c_characters_id + " = " + cha.getId(), null, null, null, null);
		cursor.moveToFirst();
		if (cursor.getCount() == 1) {
			Log.d(TAG, "PFCharacter found with id = " + cha.getId());
			cha = loadCursorToPFCharacter(cursor, cha);
		} else {
			Log.d(TAG, "PFCharacter not found with id = " + cha.getId());
		}
	}
	
	private PFCharacter loadCursorToPFCharacter(Cursor cursor, PFCharacter cha) {
		
		cha.setId(cursor.getLong(0));
		cha.setName(cursor.getString(1));
		cha.setCharacterClass(cursor.getString(2));
		cha.setPlayer(cursor.getString(3));
		cha.setLevel(cursor.getInt(4));
		cha.setMonkLevel(cursor.getInt(5));
		cha.setStr(cursor.getInt(6));
		cha.setDex(cursor.getInt(7));
		cha.setCon(cursor.getInt(8));
		cha.setInt(cursor.getInt(9));
		cha.setWis(cursor.getInt(10));
		cha.setCha(cursor.getInt(11));
		cha.setWeaponFocus(cursor.getInt(12) > 0);
		cha.setPowerAttack(cursor.getInt(13) > 0);
		//cha.setWeaponFinesse(cursor.getInt(14) > 0);
		cha.setSize(cursor.getString(15));
		cha.setWeaponDamage(cursor.getString(16));
		cha.setWeaponPlus(cursor.getInt(17));
		cha.setUnarmed(cursor.getInt(18) > 0);
		cha.setFlurryOfBlows(cursor.getInt(19) > 0);
		cha.setDailyTotal(cursor.getInt(20));
		cha.setDailyCurrent(cursor.getInt(21));
		cha.setDailyTitle(cursor.getString(22));
		cha.setCriticalMultiplier(cursor.getString(23));
		
		cha.setConditions(_app.getConditionDataSource().getConditionsForCharacter(cha.getId()));
		
		return cha;
	}
	
	private PFCharacter cursorToPFCharacter(Cursor cursor) {
		PFCharacter cha = new PFCharacter();
		return loadCursorToPFCharacter(cursor, cha);
	}

	public List<PFCharacter> getAllPFCharacters() {
		List<PFCharacter> characters = new ArrayList<PFCharacter>();
		
		Cursor cursor = database.query(DatabaseHelper._t_characters, allColumns, null, null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			PFCharacter cha = cursorToPFCharacter(cursor);
			characters.add(cha);
			cursor.moveToNext();
		}
		
		cursor.close();
		return characters;
	}
}
