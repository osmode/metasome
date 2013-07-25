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
import android.widget.SeekBar;

public class symptomFragment extends Fragment {
	
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
			SingleFragmentActivity.addFragment(SingleFragmentActivity.METABOLIC_FRAGMENT_TAG, new metabolicFragment());
			
			SingleFragmentActivity.removeFragment(SingleFragmentActivity.SYMPTOM_FRAGMENT_TAG);
			SingleFragmentActivity.removeFragment(SingleFragmentActivity.MIND_FRAGMENT_TAG);
			
			return true;
			
		case R.id.moodChoice:
			SingleFragmentActivity.addFragment(SingleFragmentActivity.MIND_FRAGMENT_TAG, new mindFragment());
			
			SingleFragmentActivity.removeFragment(SingleFragmentActivity.METABOLIC_FRAGMENT_TAG);
			SingleFragmentActivity.removeFragment(SingleFragmentActivity.SYMPTOM_FRAGMENT_TAG);
			
			return true;
		
		case R.id.symptomChoice:
			
			return true;
		
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.symptom_logger_fragment, parent, false);

		mDateButton = (Button)v.findViewById(R.id.dateButton);
		mSaveButton = (Button)v.findViewById(R.id.saveButton);
		mDateButton.setText(DateFormat.format("yyyy-MM-dd", dateSelected).toString());
		
		mDateButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				DatePickerFragment dialog = DatePickerFragment.newInstance(dateSelected);
				
				//Set the dialog's target to metabolicFragment
				dialog.setTargetFragment(symptomFragment.this, REQUEST_DATE);
				dialog.show(fm, DIALOG_DATE);
			}
			
		});		
		
		mSaveButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Log.d("metasome", "Save function");
				long timestamp = dateSelected.getTime() / 1000L;
				Log.d("metasome", String.valueOf(timestamp));
				
				SeekBar sobSeekBar = (SeekBar)getActivity().findViewById(R.id.sobBar);
				int sobValue = sobSeekBar.getProgress();
				Log.d("metasome", String.valueOf(sobValue));
				
				SeekBar edemaSeekBar = (SeekBar)getActivity().findViewById(R.id.edemaBar);
				int edemaValue = edemaSeekBar.getProgress();
				Log.d("metasome", String.valueOf(edemaValue));

				SeekBar coughSeekBar = (SeekBar)getActivity().findViewById(R.id.coughBar);
				int coughValue = coughSeekBar.getProgress();
				Log.d("metasome", String.valueOf(coughValue));

				EditText speedEditText = (EditText)getActivity().findViewById(R.id.speedEditText);
				int speedValue = metabolicFragment.editTextToInteger(speedEditText);
					
				SeekBar painSeekBar = (SeekBar)getActivity().findViewById(R.id.painBar);
				int painValue = painSeekBar.getProgress();
				Log.d("metasome", String.valueOf(painValue));
				
				EditText inhalerEditText = (EditText)getActivity().findViewById(R.id.inhalerEditText);
				int inhalerValue = metabolicFragment.editTextToInteger(inhalerEditText);
				
				SeekBar fatigueSeekBar = (SeekBar)getActivity().findViewById(R.id.fatigueBar);
				int fatigueValue = fatigueSeekBar.getProgress();
				Log.d("metasome", String.valueOf(fatigueValue));
				
				//when save button is clicked, store data in SQLite database
				//by creating a new symptomPanel object and passing it to mHelper (a logManager object).
				//The actual SQLite queries are defined in logDatabaseHelper and are called in logManager
				
				SymptomPanel tempLog = new SymptomPanel(timestamp, sobValue, edemaValue,
						coughValue, speedValue, painValue, fatigueValue, inhalerValue);
				mLogManager.insertLog(tempLog);
				 
			}
		});
				
		
		return v;
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
