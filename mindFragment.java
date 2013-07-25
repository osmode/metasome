package com.logisome.metasome;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.logisome.medisome.R;

public class mindFragment extends Fragment {
	
	private Button mVisualizeButton;
	private Button mSaveButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		
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
		
		return v;
	}
	
}
