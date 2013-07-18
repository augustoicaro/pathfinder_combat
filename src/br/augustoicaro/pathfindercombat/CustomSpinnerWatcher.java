package br.augustoicaro.pathfindercombat;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

//text watcher for the various text fields on the page
class CustomSpinnerWatcher implements AdapterView.OnItemSelectedListener {
	private static final String TAG = "PFCombat";
	private Spinner _spinner;
	private String _field;
	private FragmentBase _fragment;
	
	public CustomSpinnerWatcher(Spinner e, String field, FragmentBase a) {
		_spinner = e;
		_field = field;
		_fragment = a;
	}
	
	public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
	 	if (_fragment.getCharacter().setData(_field, _spinner.getSelectedItem().toString())) {
			Log.d(TAG, "CustomTextWatcher: " + _field + " updated successfully, updating the sheet");
	 		_fragment.updateCharacter(_field); 
	    }
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}
