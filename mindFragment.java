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

public class mindFragment extends Fragment {
	
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
			
			SingleFragmentActivity.removeFragment(SingleFragmentActivity.MIND_FRAGMENT_TAG);
			SingleFragmentActivity.removeFragment(SingleFragmentActivity.SYMPTOM_FRAGMENT_TAG);
			
			return true;
		
		case R.id.moodChoice:

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
		View v = inflater.inflate(R.layout.mind_logger_fragment, parent, false);
		
		mDateButton = (Button)v.findViewById(R.id.dateButton);
		mDateButton.setText(DateFormat.format("yyyy-MM-dd", dateSelected).toString());
		mSaveButton = (Button)v.findViewById(R.id.saveButton);
		
		mDateButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				DatePickerFragment dialog = DatePickerFragment.newInstance(dateSelected);
				
				//Set the dialog's target to metabolicFragment
				dialog.setTargetFragment(mindFragment.this, REQUEST_DATE);
				dialog.show(fm, DIALOG_DATE);
			}
			
		});
		
		
		mSaveButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Log.d("metasome", "Save function");
				long timestamp = dateSelected.getTime() / 1000L;
				Log.d("metasome", String.valueOf(timestamp));
				
				int sleepValue;
				
				EditText sleepEditText = (EditText)getActivity().findViewById(R.id.sleepEditText);
				try {
					sleepValue = Integer.parseInt(sleepEditText.getText().toString());
				} catch (Exception e) {
					Log.d("metasome", "Unable to typecast: "+e);
					sleepValue = 0;
				}
				Log.d("metasome", String.valueOf(sleepValue));
				
				SeekBar moodSeekBar = (SeekBar)getActivity().findViewById(R.id.moodBar);
				int moodValue = moodSeekBar.getProgress();
				Log.d("metasome", String.valueOf(moodValue));
				
				SeekBar anxietySeekBar = (SeekBar)getActivity().findViewById(R.id.anxietyBar);
				int anxietyValue = anxietySeekBar.getProgress();
				Log.d("metasome", String.valueOf(anxietyValue));

				SeekBar energySeekBar = (SeekBar)getActivity().findViewById(R.id.energyBar);
				int energyValue = energySeekBar.getProgress();
				Log.d("metasome", String.valueOf(energyValue));

				SeekBar concentrationSeekBar = (SeekBar)getActivity().findViewById(R.id.concentrationBar);
				int concentrationValue = concentrationSeekBar.getProgress();
				Log.d("metasome", String.valueOf(concentrationValue));
				
				SeekBar appetiteSeekBar = (SeekBar)getActivity().findViewById(R.id.appetiteBar);
				int appetiteValue = appetiteSeekBar.getProgress();
				Log.d("metasome", String.valueOf(appetiteValue));
				
				SeekBar libidoSeekBar = (SeekBar)getActivity().findViewById(R.id.libidoBar);
				int libidoValue = libidoSeekBar.getProgress();
				Log.d("metasome", String.valueOf(libidoValue));
				
				//when save button is clicked, store data in SQLite database
				//by creating a new mindPanel object and passing it to mHelper (a logManager object).
				//The actual SQLite queries are defined in logDatabaseHelper and are called in logManager
				
				MindPanel tempLog = new MindPanel(timestamp, sleepValue, moodValue,
						anxietyValue, energyValue, concentrationValue, appetiteValue, libidoValue);
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
	

