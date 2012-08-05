package com.dmtprogramming.pathfindercombat.database;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dmtprogramming.pathfindercombat.R;
import com.dmtprogramming.pathfindercombat.models.*;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

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
	public static final String _c_characters_unarmed = "unarmed";
	public static final String _c_characters_flurry_of_blows = "flurry_of_blows";
	public static final String _c_characters_daily_total = "daily_total";
	public static final String _c_characters_daily_current = "daily_current";
	public static final String _c_characters_daily_title = "daily_title";
	public static final String _c_characters_weapon_id = "weapon_id";

	public static final String _t_conditions = "conditions";
	public static final String _c_conditions_id = "_id";
	public static final String _c_conditions_character_id = "character_id";
	public static final String _c_conditions_name = "name";
	public static final String _c_conditions_duration = "duration";
	
	public static final String _t_weapons = "weapons";
	public static final String _c_weapons_id = "_id";
	public static final String _c_weapons_character_id = "character_id";
	public static final String _c_weapons_name = "name";
	public static final String _c_weapons_damage_dice = "damage_dice";
	public static final String _c_weapons_hit = "hit";
	public static final String _c_weapons_damage = "damage";
	public static final String _c_weapons_critical_multiplier = "critical_multiplier";
	public static final String _c_weapons_range = "range";
	public static final String _c_weapons_additional_damage_dice = "additional_damage_dice";

	private Dao<PFCharacter, Integer> characterDao;
	private Dao<Condition, Integer> conditionDao;
	private Dao<Weapon, Integer> weaponDao;
	
	public DatabaseHelper(Context context) {
		super(context, _dbName, null, DBVERSION, R.raw.ormlite_config);
	}
	
	private void doUpgrade(SQLiteDatabase db, ConnectionSource source, int oldVersion) {
		try {
			if (oldVersion == 1) {	
					addCharacterClass(db, source);
			} else if (oldVersion == 2) {
				addSizeAndWeaponColumns(db, source);
			} else if (oldVersion == 3) {
				addFlurryOfBlowsColumn(db, source);
			} else if (oldVersion == 4) {
				addDailyColumns(db, source);
			} else if (oldVersion == 5) {
				addCriticalMultiplerColumn(db, source);
			} else if (oldVersion == 6) {
				addConditionsTable(db, source);
			} else if (oldVersion == 7) {
				addWeaponsTable(db, source);
			} else {
				Log.v(TAG, String.format("unknown upgrade to db from version %d", oldVersion));
			}
		} catch (SQLException e) {
			Log.v(TAG, String.format("error upgrading database", e));
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addWeaponsTable(SQLiteDatabase db, ConnectionSource source) throws SQLException {
		try {
			TableUtils.createTable(connectionSource, Weapon.class);
		} catch (SQLException e) {
			Log.v(TAG, "Unable to create databases", e);
		}
		Dao<PFCharacter, Integer> dao = getCharacterDao();
		dao.executeRaw("ALTER TABLE characters ADD COLUMN weapon_id INTEGER");
		dao.executeRaw("ALTER TABLE characters DROP COLUMN weapon_damage");
		dao.executeRaw("ALTER TABLE characters DROP COLUMN weapon_plus");
		dao.executeRaw("ALTER TABLE characters DROP COLUMN critical_multiplier");
	}

	private void addCharacterClass(SQLiteDatabase db, ConnectionSource source) throws SQLException {
		Dao<PFCharacter, Integer> dao = getCharacterDao();
		dao.executeRaw("ALTER TABLE characters ADD COLUMN character_class TEXT");
	}
	
	private void addSizeAndWeaponColumns(SQLiteDatabase db, ConnectionSource source) throws SQLException {
		Dao<PFCharacter, Integer> dao = getCharacterDao();
		dao.executeRaw("ALTER TABLE characters ADD COLUMN size TEXT");
		dao.executeRaw("ALTER TABLE characters ADD COLUMN weapon_damage TEXT");
		dao.executeRaw("ALTER TABLE characters ADD COLUMN weapon_plus INTEGER");
		dao.executeRaw("ALTER TABLE characters ADD COLUMN unarmed BOOLEAN");
	}
	
	private void addFlurryOfBlowsColumn(SQLiteDatabase db, ConnectionSource source) throws SQLException {
		Dao<PFCharacter, Integer> dao = getCharacterDao();
		dao.executeRaw("ALTER TABLE characters ADD COLUMN flurry_of_blows BOOLEAN");
	}
	
	private void addDailyColumns(SQLiteDatabase db, ConnectionSource source) throws SQLException {
		Dao<PFCharacter, Integer> dao = getCharacterDao();
		dao.executeRaw("ALTER TABLE characters ADD COLUMN daily_total INTEGER");
		dao.executeRaw("ALTER TABLE characters ADD COLUMN daily_current INTEGER");
		dao.executeRaw("ALTER TABLE characters ADD COLUMN daily_title TEXT");		
	}

	private void addCriticalMultiplerColumn(SQLiteDatabase db, ConnectionSource source) throws SQLException {
		Dao<PFCharacter, Integer> dao = getCharacterDao();
		dao.executeRaw("ALTER TABLE characters ADD COLUMN critical_multiplier INTEGER");
	}
	

	private void addConditionsTable(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, Condition.class);
		} catch (SQLException e) {
			Log.v(TAG, "Unable to create databases", e);
		}
	}

	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {
		try {
			TableUtils.createTable(connectionSource, PFCharacter.class);
			TableUtils.createTable(connectionSource, Condition.class);
			TableUtils.createTable(connectionSource, Weapon.class);
		} catch (SQLException e) {
			Log.v(TAG, "Unable to create databases", e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource source, int oldVersion, int newVersion) {
		Log.v(TAG, String.format("onUpgrade(%d, %d)", oldVersion, newVersion));
		while (oldVersion < newVersion) {
			Log.v(TAG, String.format("upgrading database from version %d to %d", oldVersion, oldVersion + 1));
			doUpgrade(db, source, oldVersion);
			oldVersion++;
		}
	}
	
	public Dao<PFCharacter, Integer> getCharacterDao() throws SQLException {
		if (characterDao == null) {
			characterDao = getDao(PFCharacter.class);
		}
		return characterDao;
	}

	public Dao<Condition, Integer> getConditionDao() throws SQLException {
		if (conditionDao == null) {
			conditionDao = getDao(Condition.class);
		}
		return conditionDao;
	}

	public Dao<Weapon, Integer> getWeaponDao() throws SQLException {
		if (weaponDao == null) {
			weaponDao = getDao(Weapon.class);
		}
		return weaponDao;
	}
	
}
