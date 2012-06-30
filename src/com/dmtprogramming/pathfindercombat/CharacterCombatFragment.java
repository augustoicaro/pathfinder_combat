package com.dmtprogramming.pathfindercombat;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

public class CharacterCombatFragment extends FragmentBase {

	private static final String TAG = "PFCombat:CharacterCombatFragment";

	private CharacterModifier _smite;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.character_combat, container, false);
        
        setupView();
        populateStats("");

        setupTriggers();
		return _view;
	}
	
    private void setupTriggers() {
    	setupTrigger(R.id.txtDailyTitle, "daily_title");
    	setupTrigger(R.id.txtDailyCurrent, "daily_current");
    	setupTrigger(R.id.txtDailyTotal, "daily_total");
	}

	protected void populateStats(String f) {
    	Log.d(TAG, "populateStats(" + f + ")");
    	
    	// save some typing
    	PFCharacter c = getCharacter();
		
    	populateField(R.id.txtWeaponDice, f, "weapon_damage", c.getWeaponDamage());
    	
    	populateField(R.id.txtAttacks, f, "attacks", c.getAttacks());
    	populateField(R.id.txtStrModPlusAttack, f, "strmod", String.valueOf(c.getStrMod()));
    	populateField(R.id.txtWeaponPlusAttack, f, "weapon_plus", String.valueOf(c.getWeaponPlus()));
    	populateField(R.id.txtWeaponFocusPlusAttack, f, "weapon_focus_atk", String.valueOf(c.getWeaponFocusMod()));
    	populateField(R.id.txtOtherPlusAttack, f, "plus_hit", String.valueOf(0));
    	
    	populateField(R.id.txtStrModPlusDamage, f, "strmod", String.valueOf(c.getStrMod()));
    	populateField(R.id.txtWeaponPlusDamage, f, "weapon_plus", String.valueOf(c.getWeaponPlus()));
    	populateField(R.id.txtOtherPlusDamage, f, "plus_damage", String.valueOf(c.getPowerAttackDamage()));
    	
    	populateField(R.id.txtDailyCurrent, f, "daily_current", String.valueOf(c.getDailyCurrent()));
    	populateField(R.id.txtDailyTotal, f, "daily_total", String.valueOf(c.getDailyTotal()));
    	populateField(R.id.txtDailyTitle, f, "daily_title", c.getDailyTitle());
    	
    	// flurry of blows calcs
    	ToggleButton flurryOfBlows = (ToggleButton) findViewById(R.id.btnFlurryOfBlows);
    	flurryOfBlows.setChecked(getCharacter().getFlurryOfBlows());
    	
    	// unarmed calcs
    	ToggleButton unarmed = (ToggleButton) findViewById(R.id.btnUnarmed);
    	unarmed.setChecked(getCharacter().getUnarmed());
    	
    	// weapon focus calcs
    	ToggleButton weaponFocus = (ToggleButton) findViewById(R.id.btnWeaponFocus);
    	weaponFocus.setChecked(getCharacter().getWeaponFocus());
    	if (weaponFocus.isChecked()) {
    		TextView tv = (TextView) findViewById(R.id.txtWeaponFocusPlusAttack);
    		tv.clearFocus();
    		tv.setText("1");
    	}
    	
    	// size modifier calcs
    	String weapon_damage = getCharacter().getWeaponDamage();
    	int sizeMod = 0;
    	String sizeStr = applyToggles("size", "");
    	if (!sizeStr.equals("")) {
    		sizeMod = Integer.parseInt(sizeStr);
    	}
    	if (sizeMod > 0) {
    		int ind = -1;
    		for (int i = 0; i < PFCharacter.MEDIUM_WEAPON_DAMAGES.length; i++) {
    			if (PFCharacter.MEDIUM_WEAPON_DAMAGES[i].equals(weapon_damage)) {
    				ind = i;
    			}
    		}
    		if (ind != -1) {
    			weapon_damage = PFCharacter.LARGE_WEAPON_DAMAGES[ind];
    		}
    	}
    	
    	// extra attack calc
    	String extraAttack = applyToggles("extra_attack", "false");
    	if (extraAttack.equals("true")) {
    		TextView tv = (TextView) findViewById(R.id.txtAttacks);
    		String attacks = tv.getText().toString();
    		attacks = attacks.concat(" / " + getCharacter().getBAB());
    		tv.clearFocus();
    		tv.setText(attacks);
    	}
    	
    	Spinner spinWeaponPlus = (Spinner) findViewById(R.id.spinWeaponPlus);
    	TextView weaponPlusAttack = (TextView) findViewById(R.id.txtWeaponPlusAttack);
    	TextView weaponPlusDamage = (TextView) findViewById(R.id.txtWeaponPlusDamage);
    	weaponPlusAttack.clearFocus();
    	weaponPlusAttack.setText(spinWeaponPlus.getSelectedItem().toString());
    	weaponPlusDamage.clearFocus();
    	weaponPlusDamage.setText(spinWeaponPlus.getSelectedItem().toString());
    	
    	// plus to hit calcs
    	int plusHit = 0;
    	plusHit += parseIntField(R.id.txtStrModPlusAttack);
    	plusHit += parseIntField(R.id.spinWeaponPlus);
    	plusHit += parseIntField(R.id.txtWeaponFocusPlusAttack);
    	plusHit += parseIntField(R.id.txtOtherPlusAttack);
    	String attacks = (String) ((TextView) findViewById(R.id.txtAttacks)).getText();
    	calculateFinalPlusHit(attacks, plusHit);
    	
    	// plus to damage calcs
    	int plusDamage = 0;
    	plusDamage += parseIntField(R.id.txtStrModPlusDamage);
    	plusDamage += parseIntField(R.id.spinWeaponPlus);
    	plusDamage += parseIntField(R.id.txtOtherPlusDamage);  
    	calculateFinalPlusDamage(weapon_damage, plusDamage);
	}

	private void setupView() {
    	String[] weaponPlus = { "1", "2", "3", "4", "5", "6", "7", "8" };
    	populateSpinner(R.id.spinDamage, getCharacter().getWeaponDamage(), PFCharacter.MEDIUM_WEAPON_DAMAGES);
    	populateSpinner(R.id.spinWeaponPlus, String.valueOf(getCharacter().getWeaponPlus()), weaponPlus);

    	CharacterModifier powerAttack = new CharacterModifier();
    	powerAttack.hit = -2;
    	powerAttack.damage = 4;
    	
    	_smite = new CharacterModifier();
    	updateSmite();
    	
    	CharacterModifier plus2Str = new CharacterModifier();
    	plus2Str.str = 2;
    	
    	CharacterModifier plus4Str = new CharacterModifier();
    	plus4Str.str = 4;
    	
    	CharacterModifier plus1Hit = new CharacterModifier();
    	plus1Hit.hit = 1;
    	
    	CharacterModifier plus2Hit = new CharacterModifier();
    	plus2Hit.hit = 2;
    	
    	CharacterModifier plus4Hit = new CharacterModifier();
    	plus4Hit.hit = 4;
    	
    	CharacterModifier flank = new CharacterModifier();
    	flank.hit = 2;
    	
    	CharacterModifier plus1Damage = new CharacterModifier();
    	plus1Damage.damage = 1;
    	
    	CharacterModifier plus2Damage = new CharacterModifier();
    	plus2Damage.damage = 2;
    	
    	CharacterModifier fightDefensively = new CharacterModifier();
    	fightDefensively.hit = -4;
    	
    	CharacterModifier plus1d6 = new CharacterModifier();
    	plus1d6.damageDice = "1d6";
    	
    	CharacterModifier plus2d6 = new CharacterModifier();
    	plus2d6.damageDice = "2d6";
    	
    	CharacterModifier crit = new CharacterModifier();
    	
    	CharacterModifier enlargePerson = new CharacterModifier();
    	enlargePerson.size = 1;
    	enlargePerson.str = 2;
    	enlargePerson.hit = -1;
    	
    	CharacterModifier haste = new CharacterModifier();
    	haste.hit = 1;
    	haste.extraAttack = true;
    	
    	addToggle(R.id.btnPowerAttack, powerAttack);
    	addToggle(R.id.btnSmite, _smite);
    	addToggle(R.id.btnPlus2Str, plus2Str);
    	addToggle(R.id.btnPlus4Str, plus4Str);
    	addToggle(R.id.btnPlus1Hit, plus1Hit);
    	addToggle(R.id.btnPlus2Hit, plus2Hit);
    	addToggle(R.id.btnPlus4Hit, plus4Hit);
    	addToggle(R.id.btnFlank, flank);
    	addToggle(R.id.btnPlus1Damage, plus1Damage);
    	addToggle(R.id.btnPlus2Damage, plus2Damage);
    	addToggle(R.id.btnFightDefensively, fightDefensively);
    	addToggle(R.id.btnPlus1d6, plus1d6);
    	addToggle(R.id.btnPlus2d6, plus2d6);
    	addToggle(R.id.btnCritical, crit);
    	addToggle(R.id.btnEnlargePerson, enlargePerson);
    	addToggle(R.id.btnHaste, haste);
    	
    	ToggleButton weaponFocus = (ToggleButton) findViewById(R.id.btnWeaponFocus);
    	weaponFocus.setOnClickListener(new OnClickListener() {
    		public void onClick(View v) {
    			ToggleButton tb = (ToggleButton) v;
    			getCharacter().setWeaponFocus(tb.isChecked());
    			updateCharacter("weapon_focus");
    		}
    	});
    	
    	ToggleButton flurryOfBlows = (ToggleButton) findViewById(R.id.btnFlurryOfBlows);
    	flurryOfBlows.setOnClickListener(new OnClickListener() {
    		public void onClick(View v) {
    			ToggleButton tb = (ToggleButton) v;
    			getCharacter().setFlurryOfBlows(tb.isChecked());
    			updateCharacter("flurry_of_blows");
    		}
    	});
    	
    	ToggleButton unarmed = (ToggleButton) findViewById(R.id.btnUnarmed);
    	unarmed.setOnClickListener(new OnClickListener() {
    		public void onClick(View v) {
    			ToggleButton tb = (ToggleButton) v;
    			getCharacter().setUnarmed(tb.isChecked());
    			updateCharacter("unarmed");
    		}
    	});
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
    	String plusDamageDice = applyToggles("damage_dice", "");
    	if (!plusDamageDice.equals("")) {
    		plusDamageDice = " + " + plusDamageDice;
    	}
    	ToggleButton crit = (ToggleButton) findViewById(R.id.btnCritical);
    	if (crit.isChecked()) {
    		plusDamage *= 2;
    	}
    	tv.clearFocus();
    	tv.setText(weaponDice + " + " + String.valueOf(plusDamage) + plusDamageDice);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, String.format("test"));
    }

	@Override
	protected void onAfterUpdateCharacter(String field) {
		updateSmite();
		populateStats(field);
	}

    // update smite when the character gets updated
    private void updateSmite() {
    	_smite.hit = getCharacter().getChaMod();
    	_smite.damage = getCharacter().getLevel();
    }
    
    private void addToggle(int id, CharacterModifier mod) {
    	ToggleButton t = (ToggleButton) findViewById(id);
    	t.setOnClickListener(new ToggleClickListener(t, mod, this));
    	_mods.add(mod);
    }
    
    @Override
	public void onResume() {
		super.onResume();
	}
}
