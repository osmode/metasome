package com.logisome.metasome;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class metabolicFragment extends Fragment {
	
	private Button mVisualizeButton;
	private Button mSaveButton;
	private Button mDateButton;
	
	private logManager mLogManager;
	private Date dateSelected = new Date();

	
	private static final String DIALOG_DATE = "date";
	private static final int REQUEST_DATE = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		mLogManager = logManager.get(getActivity());
		//metabolicFragment.context = getApplicationContext();

	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		//super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.nav_menu, menu);
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.metabolicChoice:
			//SingleFragmentActivity.addFragment(METABOLIC_FRAGMENT_TAG, new metabolicFragment());
			//SingleFragmentActivity.removeFragment(SingleFragmentActivity.METABOLIC_FRAGMENT_TAG);
			return true;
		
		case R.id.moodChoice:
			SingleFragmentActivity.addFragment(SingleFragmentActivity.MIND_FRAGMENT_TAG, new mindFragment());
			
			SingleFragmentActivity.removeFragment(SingleFragmentActivity.METABOLIC_FRAGMENT_TAG);
			SingleFragmentActivity.removeFragment(SingleFragmentActivity.SYMPTOM_FRAGMENT_TAG);
			
			return true;
		
		case R.id.symptomChoice:
			SingleFragmentActivity.addFragment(SingleFragmentActivity.SYMPTOM_FRAGMENT_TAG, new symptomFragment());
			
			SingleFragmentActivity.removeFragment(SingleFragmentActivity.METABOLIC_FRAGMENT_TAG);
			SingleFragmentActivity.removeFragment(SingleFragmentActivity.MIND_FRAGMENT_TAG);
			
			return true;
		
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.body_logger_fragment, parent, false);
		
		mDateButton = (Button)v.findViewById(R.id.dateButton);
		mSaveButton = (Button)v.findViewById(R.id.saveButton);
		
		mDateButton.setText(DateFormat.format("yyyy-MM-dd", dateSelected).toString());
		
		mDateButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				FragmentManager fm = getActivity().getSupportFragmentManager();
				DatePickerFragment dialog = DatePickerFragment.newInstance(dateSelected);
				//Set the dialog fragment's target to metabolicFragment 
				dialog.setTargetFragment(metabolicFragment.this, REQUEST_DATE);
				dialog.show(fm, DIALOG_DATE);
				
			}
		});
		
		mSaveButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Log.d("metasome", "Save function");
				long timestamp = dateSelected.getTime() / 1000L;
				Log.d("metasome", String.valueOf(timestamp));
				
				//get values from EditTexts
				EditText hrEditTextValue = (EditText)getActivity().findViewById(R.id.hrEditText);
				int hrValue = editTextToInteger(hrEditTextValue);
				Log.d("metasome", String.valueOf(hrValue));
				
				EditText systolicEditText = (EditText)getActivity().findViewById(R.id.systolicEditText);
				int systolicValue = editTextToInteger(systolicEditText);
				Log.d("metasome", String.valueOf(systolicValue));
				
				EditText diastolicEditText = (EditText)getActivity().findViewById(R.id.diastolicEditText);
				int diastolicValue = editTextToInteger(diastolicEditText);
				Log.d("metasome", String.valueOf(diastolicValue));
				
				EditText weightEditText = (EditText)getActivity().findViewById(R.id.weightEditText);
				int weightValue = editTextToInteger(weightEditText);
				Log.d("metasome", String.valueOf(weightValue));

				EditText glucoseEditText = (EditText)getActivity().findViewById(R.id.glucoseEditText);
				int glucoseValue = editTextToInteger(glucoseEditText);
				Log.d("metasome", String.valueOf(glucoseValue));

				//when save button is clicked, store data in SQLite database
				//by creating a new bodyPanel object and passing it to mHelper (a logManager object).
				//The actual SQLite queries are defined in logDatabaseHelper and are called in logManager
				
				BodyPanel tempLog = new BodyPanel(timestamp, hrValue, systolicValue,
						diastolicValue, weightValue, glucoseValue);
				//mLogManager.insertLog(tempLog);
				
			}
		});
		
		return v;
	}
	
	public static int editTextToInteger(EditText input) {
		try {
			Log.d("metasome",input.getText().toString());
			return Integer.parseInt(input.getText().toString());
		} catch (Exception e) {
			Log.d("metasome", "Unable to typecast: " + e);
			return 0;
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != Activity.RESULT_OK)
			return;
		if (requestCode == REQUEST_DATE) {
			Date date = (Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
			dateSelected = date;
			mDateButton.setText(DateFormat.format("yyyy-MM-dd", dateSelected).toString());

		}
	}
	
}
