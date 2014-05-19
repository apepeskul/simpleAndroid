package com.apepeskul.CriminalIntent.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.TimePicker;

import java.util.Date;


public class CrimeTimeDialog extends TimePickerDialog {



    public CrimeTimeDialog(Context context, OnTimeSetListener callBack, int hourOfDay, int minute, boolean is24HourView) {
        super(context, callBack, hourOfDay, minute, is24HourView);

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        super.onClick(dialog, which);

        Log.d("TIME_PICKER", "date picked");
    }
}
