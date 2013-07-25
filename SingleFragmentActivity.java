package com.logisome.metasome;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.logisome.medisome.R;

public abstract class SingleFragmentActivity extends FragmentActivity {
	

	//Method 'createFragment()' returns a fragment.
	//It is defined in 'LogFragmentActivity'
	protected abstract Fragment createFragment();
	
	private static FragmentManager fm;
	
	//TAGs used to keep track of fragments
	public final static String METABOLIC_FRAGMENT_TAG = "metabolicFragment";
	public final static String BODY_FRAGMENT_TAG = "bodyFragment";
	public final static String MIND_FRAGMENT_TAG = "mindFragment";
	public final static String SYMPTOM_FRAGMENT_TAG = "symptomFragment";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment);
		//Initialize fragment manager onCreate(Bundle)
		fm = getSupportFragmentManager();
		
		Fragment initialFragment = fm.findFragmentByTag(METABOLIC_FRAGMENT_TAG);
		if(initialFragment == null) {
			addFragment(METABOLIC_FRAGMENT_TAG, new metabolicFragment());
		}
		/*
		Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
		
		if(fragment == null) {
			fragment = createFragment();
			fm.beginTransaction()
				.add(R.id.fragmentContainer, fragment, TAG)
				.commit();
		}
		*/
	}
	
	//Method 'removeFragment(String)' detaches fragments without destroying them
	public static boolean removeFragment(String tagName) {
		Fragment foundFragment = fm.findFragmentByTag(tagName);
		if (foundFragment == null)
			return false;
		else {
			fm.beginTransaction().detach(fm.findFragmentByTag(tagName)).commit();
			Log.d("metasome","Detached fragment "+tagName);
			return true;
		}
		
	}
	
	//Method 'addFragment' attempts to re-attach a fragment before creating it anew
	public static void addFragment(String tagName, Fragment newFragment) {
		if (doesExist(tagName)) {
			Fragment existingFragment = fm.findFragmentByTag(tagName);
			fm.beginTransaction().attach(existingFragment).commit();
			Log.d("metasome", "Attached fragment "+tagName);
		}
		else {
			fm.beginTransaction().add(R.id.fragmentContainer, newFragment, tagName).commit();
			Log.d("metasome", "Added new fragment"+tagName);
		}
	}
	
	public static boolean doesExist(String tagName) {
		Fragment foundFragment = fm.findFragmentByTag(tagName);
		if (foundFragment == null)
			return false;
		else
			return true;	
	}
	
}
