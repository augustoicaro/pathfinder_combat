package com.dmtprogramming.pathfindercombat;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public abstract class FragmentBase extends Fragment implements AdapterView.OnItemSelectedListener {

	private static final String TAG = "PFCombat:FragmentBase";
	
	protected View _view;
	protected List<CharacterModifier> _mods;
    
    protected abstract void onAfterUpdateCharacter(String field);
    protected abstract void populateStats(String field);
	
    public FragmentBase() {
     	_mods = new ArrayList<CharacterModifier>();
    }
    
    // saves the character after a field update and refreshes everything
	public void updateCharacter(String field) {
		Log.d(TAG, "updateCharacter()");
		getDatasource().updatePFCharacter(getCharacter());
		onAfterUpdateCharacter(field);
	}
	
	public PFCharacter getCharacter() {
		ViewPagerFragmentActivity view = (ViewPagerFragmentActivity) getActivity();
		return view.getCharacter();
	}
	
	public PFCharacterDataSource getDatasource() {
		ViewPagerFragmentActivity view = (ViewPagerFragmentActivity) getActivity();
		return view.getDatasource();
	}
	
    protected void setupTrigger(int id, String field) {
    	EditText e = (EditText) findViewById(id);
    	e.addTextChangedListener(new CustomTextWatcher(e, field, this));
    }
	
	protected void populateField(int id, String ignore, String field, String value) {
		if (!ignore.equals(field)) {
			View v = findViewById(id);
			String type = v.getClass().getName();
			if (type.equals("android.widget.EditText")) {
				EditText e = (EditText) v;
				e.clearFocus();
	    		e.setText(value);
			} else if (type.equals("android.widget.TextView")) {
				TextView e = (TextView) v;
				e.clearFocus();
				e.setText(applyToggles(field, value));
			}
		}
	}
	
	public void populateSpinner(int id, String selected, String[] options) {
		Spinner spinner = (Spinner) _view.findViewById(id);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, options);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
		
		for (int i = 0; i < options.length; i++) {
			String test = options[i];
			if (test.equals(selected)) {
				spinner.setSelection(i);
			}
		}    	
	}
	 
    // apply the stats from all of the toggles to a single field
	protected String applyToggles(String field, String value) {
		Log.d(TAG, "applyToggles(" + field + ", " + value +") - mods = " + _mods.size());
		for (int i = 0; i < _mods.size(); i++) {
			CharacterModifier mod = _mods.get(i);
			value = mod.apply(field, value);
		}
		return value;
	}
	
    protected View findViewById(int id) {
    	return _view.findViewById(id);
    }

	protected int parseIntField(int id) {
    	View v = findViewById(id);
		String type = v.getClass().getName();
		int i = 0;
		if (type.equals("android.widget.EditText")) {
			EditText tv = (EditText) v;
			i = Integer.parseInt(tv.getText().toString());
		} else if (type.equals("android.widget.TextView")) {
			TextView tv = (TextView) v;
			i = Integer.parseInt(tv.getText().toString());
		}
		return i;
    }
    
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
		
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.character_menu, menu);
        return true;
    }
 
    public boolean onOptionsItemSelected(MenuItem item) {
    	if (item.getItemId() == R.id.menuDelete) {
	        showDeleteDialog();
	        return true;	        
        }
        return super.onOptionsItemSelected(item);
    }

	private void showDeleteDialog() {
		new AlertDialog.Builder(getActivity())
		.setIcon(android.R.drawable.ic_dialog_alert)
		.setTitle(R.string.delete)
		.setMessage(R.string.really_delete)
		.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				getDatasource().deletePFCharacter(getCharacter());
				getActivity().finish();
			}
		})
		.setNegativeButton(R.string.no, null)
		.show();		
	}
}
