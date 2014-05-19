package com.apepeskul.CriminalIntent.activity;

import android.support.v4.app.Fragment;
import com.apepeskul.CriminalIntent.fragment.CrimeListFragment;

/**
 * Created by alex on 18.05.2014.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
