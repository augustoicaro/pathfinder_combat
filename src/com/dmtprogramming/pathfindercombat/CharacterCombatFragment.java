package com.dmtprogramming.pathfindercombat;

import java.util.Iterator;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.dmtprogramming.pathfindercombat.database.DatabaseHelper;
import com.dmtprogramming.pathfindercombat.models.*;
import com.dmtprogramming.pathfindercombat.modifier.ModifierBase;
import com.dmtprogramming.pathfindercombat.modifier.ModifierBase.ModifierField;
import com.dmtprogramming.pathfindercombat.WeaponActivity;

public class CharacterCombatFragment extends FragmentBase {

	private static final String TAG = "PFCombat:CharacterCombatFragment";
	private String currentRange;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.character_combat_fragment, container, false);
		
		currentRange = "";
		
		setupIntentFilter();
		
        setupView();
        populateStats("");

        setupTriggers();
        
        setupButtons();
        
		return _view;
	}
	
    private void setupTriggers() {
    	setupEditTextTrigger(R.id.txtDailyTitle, DatabaseHelper._c_characters_daily_title);
    	setupEditTextTrigger(R.id.txtDailyCurrent, DatabaseHelper._c_characters_daily_current);
    	setupEditTextTrigger(R.id.txtDailyTotal, DatabaseHelper._c_characters_daily_total);
	}
    
    private void setupButtons() {
    	Button newWeapon = (Button) findViewById(R.id.btnWeapons);
    	newWeapon.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), WeaponActivity.class);;

				myIntent.putExtra("CHARACTER_ID", getCharacter().getId());
				startActivityForResult(myIntent, 2);	            
			}
		});
    }

	protected void populateStats(String f) {
    	Log.d(TAG, "populateStats(" + f + ")");
    	
    	// save some typing
    	PFCharacter c = getCharacter();
    	Weapon w = c.getWeapon();
		
    	if (!w.rangeString().equals(currentRange)) {
    		currentRange = w.rangeString();
    		updateToggleTable();
    	}
    	
    	populateField(R.id.txtWeaponDice, f, ModifierField._damage_dice, w.getDamageDice());
    	
    	populateField(R.id.txtAttacks, f, ModifierField._none, c.getAttacks());
    	
    	if (w.rangeString().equals("ranged")) {
    		TextView tv = (TextView) findViewById(R.id.txtPlusHitLabel);
    		tv.setText("Dex");
    		populateField(R.id.txtStatModPlusAttack, f, ModifierField._dex_mod, String.valueOf(c.getDexMod()));
        	populateField(R.id.txtStrModPlusDamage, f, ModifierField._str_mod, String.valueOf(0));    		
    	} else {
    		TextView tv = (TextView) findViewById(R.id.txtPlusHitLabel);
    		tv.setText("Str");
    		populateField(R.id.txtStatModPlusAttack, f, ModifierField._str_mod, String.valueOf(c.getStrMod()));
        	populateField(R.id.txtStrModPlusDamage, f, ModifierField._str_mod, String.valueOf(c.getStrMod()));
    	}
    	populateField(R.id.txtWeaponPlusAttack, f, ModifierField._none, String.valueOf(w.getHit()));
    	populateField(R.id.txtWeaponFocusPlusAttack, f, ModifierField._none, String.valueOf(c.getWeaponFocusMod()));
    	populateField(R.id.txtOtherPlusAttack, f, ModifierField._hit, String.valueOf(0));
    	

    	populateField(R.id.txtWeaponPlusDamage, f, ModifierField._none, String.valueOf(w.getDamage()));
    	populateField(R.id.txtOtherPlusDamage, f, ModifierField._damage, String.valueOf(c.getPowerAttackDamage()));
    	
    	populateField(R.id.txtDailyCurrent, f, ModifierField._none, String.valueOf(c.getDailyCurrent()));
    	populateField(R.id.txtDailyTotal, f, ModifierField._none, String.valueOf(c.getDailyTotal()));
    	populateField(R.id.txtDailyTitle, f, ModifierField._none, c.getDailyTitle());
    	
    	// size modifier calcs
    	String weaponDice = w.getDamageDice();
    	int sizeMod = 0;
    	String sizeStr = applyToggles(ModifierField._size, "");
    	if (!sizeStr.equals("")) {
    		sizeMod = Integer.parseInt(sizeStr);
    	}
    	if (sizeMod > 0) {
    		weaponDice = c.getEnlargedWeaponDamage();
    	} else if (sizeMod < 0) {
    		weaponDice = c.getReducedWeaponDamage();
    	}
    	TextView weaponDiceText = (TextView) findViewById(R.id.txtWeaponDice);
    	weaponDiceText.setText(weaponDice);

    	// attacks
    	TextView tvAttacks = (TextView) findViewById(R.id.txtAttacks);
		String attacks_str = applyToggles(ModifierField._attacks, c.getAttacks());
    	String extraAttack = applyToggles(ModifierField._extra_attack, "0");
    	if (!extraAttack.equals("0")) {
    		attacks_str = attacks_str.concat(" / " + getCharacter().getBAB());
    	}
		tvAttacks.clearFocus();
		tvAttacks.setText(attacks_str);
    	
		// weapon plus
		TextView weaponFullName = (TextView) findViewById(R.id.txtWeaponFullName);
		Weapon weapon = c.getWeapon();
		if (weapon != null) {
			weaponFullName.setText(weapon.toString());
		}
    	
    	// plus to hit
    	int plusHit = 0;
    	plusHit += parseIntField(R.id.txtStatModPlusAttack);
    	plusHit += w.getHit();
    	plusHit += parseIntField(R.id.txtWeaponFocusPlusAttack);
    	plusHit += parseIntField(R.id.txtOtherPlusAttack);
    	String attacks = (String) ((TextView) findViewById(R.id.txtAttacks)).getText();
    	calculateFinalPlusHit(attacks, plusHit);
    	
    	// plus to damage
    	int plusDamage = 0;
    	plusDamage += parseIntField(R.id.txtStrModPlusDamage);
    	plusDamage += w.getDamage();
    	plusDamage += parseIntField(R.id.txtOtherPlusDamage);  
    	calculateFinalPlusDamage(weaponDice, plusDamage);
	}

	private void setupView() {
    	updateToggleTable();
	}
	
	private void updateToggleTable() {
    	TableLayout table = (TableLayout) findViewById(R.id.combat_toggles);
    	table.removeAllViews();
    	for (int i = 0; i < _mods.size(); i++) {
    		ModifierBase mod = _mods.get(i);
    		mod.setEnabled(false);
    	}
    	_mods.clear();
    	TableRow row = new TableRow(getActivity());
    	int position = 0;
    	
    	PFCombatApplication app = (PFCombatApplication)getActivity().getApplication();
    	List<ModifierBase> toggles = app.getToggles();
    	Iterator<ModifierBase> iter = toggles.iterator();
    	while (iter.hasNext()) {
    		ModifierBase mod = iter.next();

    		Log.d(TAG, "character weapon range = " + getCharacter().getWeapon().rangeString());
    		
    		if (mod.appliesToCharacterClass(getCharacter().getCharacterClass()) &&
    				mod.appliesToRange(getCharacter().getWeapon().rangeString())) {
	    		
	    		ToggleButton button = new ToggleButton(getActivity());
	    		button.setTextOn(mod.name());
	    		button.setTextOff(mod.name());
	    		button.setText(mod.name());
	    		row.addView(button);
	    		
	        	button.setOnClickListener(new ToggleClickListener(button, mod, this));
	        	_mods.add(mod);
	    		
	    		position += 1;
	    		if (position == 4) {
	    			table.addView(row);
	    			row = new TableRow(getActivity());
	    			position = 0;
	    		}
	    		Log.d(TAG, "added modifier toggle name = " + mod.name());
    		}
    	}
    	if (position != 4) {
    		table.addView(row);
    	}
	}
	
    private void calculateFinalPlusHit(String attacks_str, int plusHit) {
    	TextView tv = (TextView) findViewById(R.id.txtPlusAttack);
    	String finalPlusAttack = "";
    	String[] attacks = attacks_str.split("/");
    	for (int i = 0; i < attacks.length; i++) {
    		int hit = Integer.parseInt(attacks[i].trim());
    		if (!finalPlusAttack.equals("")) {
    			finalPlusAttack += " / ";
    		}
    		finalPlusAttack = finalPlusAttack + String.valueOf(hit + plusHit);
    	}
    	tv.clearFocus();
    	tv.setText(finalPlusAttack);
    }
    
    private void calculateFinalPlusDamage(String weaponDice, int plusDamage) {
    	TextView tv = (TextView) findViewById(R.id.txtPlusDamage);
    	String plusDamageDice = applyToggles(ModifierField._damage_dice, "");
    	if (!plusDamageDice.equals("")) {
    		plusDamageDice = " + " + plusDamageDice;
    	}
    	Weapon weapon = getCharacter().getWeapon();
    	if (!weapon.getAdditionalDamageDice().equals("")) {
    		plusDamageDice += " + " + weapon.getAdditionalDamageDice();
    	}
    	weaponDice = applyToggles(ModifierField._critical_damage_dice, weaponDice);
    	String plusDamageStr = applyToggles(ModifierField._critical_damage, String.valueOf(plusDamage));
    	tv.clearFocus();
    	tv.setText(weaponDice + " + " + plusDamageStr + plusDamageDice);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
  
	@Override
	public void onAfterUpdateCharacter(String field) {
		if (field.equals(DatabaseHelper._c_characters_character_class)) {
			updateToggleTable();
		}
		populateStats(field);
	}
}
