package com.apepeskul.CriminalIntent;

import android.support.v4.app.Fragment;

/**
 * Created by alex on 18.05.2014.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
