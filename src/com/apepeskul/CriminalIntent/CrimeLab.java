package com.apepeskul.CriminalIntent;

import android.content.Context;

import java.util.LinkedList;
import java.util.UUID;

/**
 * Created by alex on 18.05.2014.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private Context mAppContext;
    private LinkedList<Crime> mCrimes;

    public LinkedList<Crime> getCrimes() {
        return mCrimes;

    }

    public Crime getCrime(UUID id) {
        for (Crime crime : mCrimes) {
            if (crime.getId().equals(id))
                return crime;
        }
        return null;
    }

    private CrimeLab(Context appContext) {
        mAppContext = appContext;
        mCrimes = new LinkedList<Crime>();
        for (int i = 0; i < 100; i++) {
            Crime c = new Crime();
            c.setTitle("Crime #" + i);
            c.setSolved(i % 2 == 0); // Для каждого второго объекта
            mCrimes.add(c);
        }
    }

    public static CrimeLab getInstance(Context c) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(c.getApplicationContext());
        }
        return sCrimeLab;
    }
}
