package com.apepeskul.CriminalIntent.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import com.apepeskul.CriminalIntent.R;
import com.apepeskul.CriminalIntent.activity.CrimePagerActivity;
import com.apepeskul.CriminalIntent.model.Crime;
import com.apepeskul.CriminalIntent.model.CrimeLab;

import java.text.SimpleDateFormat;
import java.util.List;

//import com.apepeskul.CriminalIntent.activity.CrimeActivity;

/**
 * Created by alex on 18.05.2014.
 */
public class CrimeListFragment extends android.support.v4.app.ListFragment {
    private static final String TAG = "CrimeListFragment";
    private List<Crime> mCrimes;

  /*  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.crime_list, container, false);
        return v;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crimes_title);
        mCrimes = CrimeLab.getInstance(getActivity()).getCrimes();
        CrimeAdapter adapter = new CrimeAdapter(mCrimes);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Crime c = ((CrimeAdapter) getListAdapter()).getItem(position);
        Intent intent = new Intent(getActivity(), CrimePagerActivity.class);
        intent.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getId());
        startActivity(intent);


    }

    private class CrimeAdapter extends ArrayAdapter<Crime> {
        private CrimeAdapter(List<Crime> crimes) {
            super(getActivity(), 0, crimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime, null);
            }

            Crime c = getItem(position);
            TextView titleTextView = (TextView) convertView.findViewById(R.id.crime_list_title);
            titleTextView.setText(c.getTitle());
            TextView dateTextView = (TextView) convertView.findViewById(R.id.crime_list_date);
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMM dd, yyyy");
            dateTextView.setText(dateFormat.format(c.getDate()));
            CheckBox solvedCheckBox = (CheckBox) convertView.findViewById(R.id.crime_solved_checkbox);
            solvedCheckBox.setChecked(c.isSolved());
            return convertView;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((CrimeAdapter) getListAdapter()).notifyDataSetChanged();
    }
}
