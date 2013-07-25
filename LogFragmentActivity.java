package com.logisome.metasome;

import android.support.v4.app.Fragment;

public class LogFragmentActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new metabolicFragment();
	}

}
