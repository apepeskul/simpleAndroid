package com.apepeskul.CriminalIntent.fragment;



import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import com.apepeskul.CriminalIntent.R;
import com.apepeskul.CriminalIntent.model.Crime;
import com.apepeskul.CriminalIntent.model.CrimeLab;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CrimeFragment extends android.support.v4.app.Fragment {

    public static final String EXTRA_CRIME_ID = "CRIME_ID";
    private static final String DIALOG_DATE = "date";
    public static final int REQUEST_DATE = 0;


    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mCheckBox;

    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CRIME_ID, crimeId);
        CrimeFragment crimeFragment = new CrimeFragment();
        crimeFragment.setArguments(args);
        return crimeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeId = (UUID)getArguments().getSerializable(EXTRA_CRIME_ID);

        mCrime = CrimeLab.getInstance(getActivity()).getCrime(crimeId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);
        mDateButton = (Button) v.findViewById(R.id.crime_date);
        final SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMM dd, yyyy");
        mDateButton.setText(dateFormat.format(mCrime.getDate()));
        //mDateButton.setEnabled(false);
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                //CrimeDateDialog datePicker = new CrimeDateDialog(getActivity(), null, 2014,06, 20);
                //datePicker.show(fm, DIALOG_DATE);
                //DatePickerFragment datePicker = new DatePickerFragment();
                DatePickerFragment datePicker = DatePickerFragment.newInstance(mCrime.getDate());
                datePicker.show(fm, DIALOG_DATE);
                //setTargetFragment(datePicker, REQUEST_DATE);

            }
        });

        mCheckBox = (CheckBox) v.findViewById(R.id.checkBox);
        mCheckBox.setChecked(mCrime.isSolved());
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });
        mTitleField = (EditText) v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


}