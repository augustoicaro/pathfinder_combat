package com.dmtprogramming.pathfindercombat;

import java.util.ArrayList;
import java.util.List;

import com.dmtprogramming.pathfindercombat.models.Condition;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class ConditionsFragment extends FragmentBase {
	
	private static final String TAG = "PFCombat:ConditionsFragment";
	private ConditionArrayAdapter _listAdapter;
	private List<Condition> conditions;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.conditions_fragment, container, false);
		
		setupIntentFilter();
		
        setupView();
        populateStats("");
        
        hideConditionMenu();
		return _view;
	}
	
	private void hideConditionMenu() {
		ListView lv = (ListView) findViewById(R.id.listConditions);
		ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) lv.getLayoutParams();
		params.setMargins(0, 0, 0, 0);
		
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.layoutConditionsMenu);
		layout.setVisibility(View.GONE);
	}
	
	private void showConditionMenu() {
		ListView lv = (ListView) findViewById(R.id.listConditions);
		ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) lv.getLayoutParams();
		params.setMargins(0, 0, 0, 50);
		
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.layoutConditionsMenu);
		layout.setVisibility(View.VISIBLE);		
	}
	
	private void setupView() {
		// TODO Auto-generated method stub
		Log.d(TAG, "test");
		populateList();
		
		Button newCondition = (Button) findViewById(R.id.btnNewCondition);
		newCondition.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				PFCombatApplication app = (PFCombatApplication) getActivity().getApplication();
				
				final Dialog dialog = new Dialog(ConditionsFragment.this.getActivity());
				dialog.setContentView(R.layout.new_condition_dialog);
				dialog.setTitle("New Condition");
				dialog.setCancelable(true);
				
				EditText durationText = (EditText) dialog.findViewById(R.id.txtRounds);
				durationText.setText("1");
				
				Spinner spinner = (Spinner) dialog.findViewById(R.id.spinConditions);
				List<String> conditions = app.getSortedConditionNames();
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, conditions);
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinner.setAdapter(adapter); 			
				
				Button button = (Button)dialog.findViewById(R.id.btnAdd);
				button.setOnClickListener(new View.OnClickListener() {	
					@Override
					public void onClick(View v) {
						Spinner spinner = (Spinner) dialog.findViewById(R.id.spinConditions);
						EditText text = (EditText) dialog.findViewById(R.id.txtRounds);
						String name = spinner.getSelectedItem().toString();
						String durationStr = text.getText().toString();
						long duration = 1;
						if (!durationStr.equals("")) {
							duration = Integer.parseInt(durationStr);
						}
						getConditionDataSource().createCondition(getCharacter(), name, duration);
						
						populateList();
						dialog.cancel();
					}
				});
				
				Button button2 = (Button)dialog.findViewById(R.id.btnCancel);
				button2.setOnClickListener(new View.OnClickListener() {	
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialog.cancel();
					}
				});
				
				dialog.getWindow().setLayout(380, 380);
				
				dialog.show();	
			}
		});
		
		Button nextRound = (Button) findViewById(R.id.btnNextRound);
		nextRound.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				for (int i = 0; i < conditions.size(); i++) {
					Condition condition = conditions.get(i);
					condition.setDuration(condition.getDuration() - 1);
					if (condition.getDuration() == 0) {
						getConditionDataSource().deleteCondition(getCharacter(), condition);
					} else {
						getConditionDataSource().updateCondition(getCharacter(), condition);		
					}
				}
				
				populateList();
			}
		});
		
		Button removeConditions = (Button) findViewById(R.id.btnRemoveConditions);
		removeConditions.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				List<Condition> deleters = _listAdapter.checked();
				for (int i = 0; i < deleters.size(); i++) {
					getConditionDataSource().deleteCondition(getCharacter(), deleters.get(i));
				}
				populateList();
				hideConditionMenu();
			}
		});
	}

	@Override
	public void onAfterUpdateCharacter(String field) {
		populateList();
	}

	@Override
	protected void populateStats(String field) {
	}
	
	protected void populateList() {
		if (_listAdapter != null) {
			_listAdapter.notifyDataSetInvalidated();
		}
        ListView lv = (ListView) findViewById(R.id.listConditions);
        
        conditions = getCharacter().getConditions();
        _listAdapter = new ConditionArrayAdapter(getActivity(), conditions, this);
        lv.setAdapter(_listAdapter);
        lv.forceLayout();
        _listAdapter.notifyDataSetChanged();
        
        Log.d(TAG, "populating list, count = " + conditions.size());
        for(int i = 0; i < conditions.size(); i++) {
        	Log.d(TAG, "     id = " + conditions.get(i).getId());        	
        }
	}
	
    @Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}
	
	private static class ConditionViewHolder {
		TextView textView;
		TextView textName;
		CheckBox checkBox;
		Condition condition;
		TextView description;
		RelativeLayout row;
	}
	
	private static class ConditionArrayAdapter extends ArrayAdapter<Condition> {
		private static final String TAG = "PFCombat:ConditionArrayAdapter";
		private LayoutInflater inflater;
		private ConditionsFragment fragment;
		private boolean menuShown;
		private List<ConditionViewHolder> holders;
		private List<Condition> checked;
		
		public ConditionArrayAdapter(Context context, List<Condition> conditionList, ConditionsFragment frag) {
			super(context, R.layout.condition_row, R.id.rowTextView, conditionList);
			holders = new ArrayList<ConditionViewHolder>();
			checked = new ArrayList<Condition>();
			fragment = frag;;
			inflater = LayoutInflater.from(context);
			menuShown = false;
		}
		
		public List<Condition> checked() {
			return this.checked;
		}
		
		// hides or shows the condition menu based on check boxes checked
		public void hideShowConditionMenu(boolean checked, ConditionViewHolder activeHolder) {
			if (checked) {
				this.checked.add(activeHolder.condition);
				Log.d(TAG, "checkbox added, showing the menu");
				if (!menuShown) {
					fragment.showConditionMenu();
				}
			} else {
				this.checked.remove(activeHolder.condition);
				Log.d(TAG, "checkbox removed, test to see if we hide the menu");
				boolean otherChecked = false;
				for (int i = 0; i < holders.size(); i++) {
					ConditionViewHolder holder = holders.get(i);
					if (holder != activeHolder && holder.checkBox.isChecked()) {
						otherChecked = true;
					}
				}
				if (!otherChecked) {
					fragment.hideConditionMenu();
				}
			}
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			Condition condition = (Condition) this.getItem(position);
			
			TextView textView;
			TextView textName;
			CheckBox checkBox;
			final TextView description;
			RelativeLayout row;
			
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.condition_row, null, false);
				
				textView = (TextView) convertView.findViewById(R.id.rowTextView);
				textName = (TextView) convertView.findViewById(R.id.rowName);
				checkBox = (CheckBox) convertView.findViewById(R.id.rowCheckBox);
				description = (TextView) convertView.findViewById(R.id.rowTextDescription);
				row = (RelativeLayout) convertView.findViewById(R.id.layoutConditionRow);
				
				final ConditionViewHolder holder = new ConditionViewHolder();
				holder.textView = textView;
				holder.checkBox = checkBox;
				holder.description = description;
				holder.condition = condition;
				holder.textName = textName;
				holder.row = row;
				
				checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						hideShowConditionMenu(isChecked, holder);
					}
					
				});
				
				row.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (description.getVisibility() == View.GONE) {
							description.setVisibility(View.VISIBLE);
						} else {
							description.setVisibility(View.GONE);
						}
					}
				});
				
				holders.add(holder);
				convertView.setTag(holder);
			} else {
				ConditionViewHolder viewHolder = (ConditionViewHolder) convertView.getTag();
				textView = viewHolder.textView;
				checkBox = viewHolder.checkBox;
				description = viewHolder.description;
				textName = viewHolder.textName;
				row = viewHolder.row;
			}
			
			checkBox.setText("");
			textView.setText("(" + condition.getDuration() + ")");
			PFCombatApplication app = (PFCombatApplication) fragment.getActivity().getApplication();
			description.setText(Html.fromHtml(app.getConditionShortDescription(condition.getName())));
			textName.setText(Html.fromHtml("<u>" + condition.getName() + "</u>"));
			return convertView;
		}
	}
}

