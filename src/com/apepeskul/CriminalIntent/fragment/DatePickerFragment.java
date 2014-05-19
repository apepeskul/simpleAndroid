package com.apepeskul.CriminalIntent.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import com.apepeskul.CriminalIntent.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by admin on 5/19/2014.
 */
public class DatePickerFragment extends DialogFragment {
    public static final String EXTRA_DATE = "com.apepeskul.date";
    private Date mDate;
    public static DatePickerFragment newInstance(Date date){
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATE, date);
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setArguments(args);
        return datePickerFragment;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.date_dialog,null);

        mDate = (Date)getArguments().getSerializable(EXTRA_DATE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day  = calendar.get(Calendar.DAY_OF_MONTH);
        DatePicker datePicker = (DatePicker) v.findViewById(R.id.date_picker_dialog);
        datePicker.init(year,month,day,new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mDate = new GregorianCalendar(year,month,day).getTime();
                getArguments().putSerializable(EXTRA_DATE, mDate);
            }
        });
        return new AlertDialog.Builder(getActivity()).setView(datePicker).setTitle(R.string.crime_date)
                .setPositiveButton(android.R.string.ok, null).create();

    }
}