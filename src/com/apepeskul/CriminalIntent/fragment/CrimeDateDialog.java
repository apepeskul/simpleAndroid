package com.apepeskul.CriminalIntent.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import java.util.Date;


public class CrimeDateDialog extends DatePickerDialog {
    public CrimeDateDialog(Context context, OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth) {
        super(context, callBack, year, monthOfYear, dayOfMonth);

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        super.onClick(dialog, which);
        Date date = new Date();
        //date.setTime();

        Log.d("DATE_PICKER", "date picked");
    }
}
