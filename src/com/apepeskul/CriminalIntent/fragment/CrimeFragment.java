package com.apepeskul.CriminalIntent.fragment;


import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.apepeskul.CriminalIntent.R;
import com.apepeskul.CriminalIntent.model.Crime;
import com.apepeskul.CriminalIntent.model.CrimeLab;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

public class CrimeFragment extends android.support.v4.app.Fragment {

    public static final String EXTRA_CRIME_ID = "CRIME_ID";
    private static final String DIALOG_DATE = "date";
    public static final int REQUEST_DATE = 0;


    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton, mTimeButton;
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
        UUID crimeId = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);

        mCrime = CrimeLab.getInstance(getActivity()).getCrime(crimeId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);
        mDateButton = (Button) v.findViewById(R.id.crime_date);
        mTimeButton = (Button)v.findViewById(R.id.crime_time);
        updateDate();
        //mDateButton.setEnabled(false);

        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                //CrimeDateDialog datePicker = new CrimeDateDialog(getActivity(), null, 2014,06, 20);
                //datePicker.show(fm, DIALOG_DATE);
                //DatePickerFragment datePicker = new DatePickerFragment();
                DatePickerFragment datePicker = DatePickerFragment.newInstance(mCrime.getDate());
                datePicker.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                datePicker.show(fm, DIALOG_DATE);

            }
        });


        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrimeTimeDialog timeDialog = new CrimeTimeDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfHour) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(mCrime.getDate());
                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH);
                        int day = calendar.get(Calendar.DAY_OF_MONTH);
                        int hour = hourOfDay;
                        int minute = minuteOfHour;
                        mCrime.setDate(new GregorianCalendar(year, month, day, hour, minute).getTime());
                        updateDate();
                    }
                },mCrime.getDate().getHours(),mCrime.getDate().getMinutes(),true);
                timeDialog.show();
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
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(date);
            updateDate();
        }
    }

    private void updateDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMM dd, yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH : mm");
        mDateButton.setText(dateFormat.format(mCrime.getDate()));
        mTimeButton.setText(timeFormat.format(mCrime.getDate()));
    }


}