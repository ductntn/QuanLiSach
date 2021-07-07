package com.example.quanlisach;

import android.app.FragmentManager;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class Datepicker extends DialogFragment {
    private int year, month, day;
    public Datepicker(){
        Calendar c = Calendar.getInstance();
        year=c.get(Calendar.YEAR);
        month=c.get(Calendar.MONTH);
        day=c.get(Calendar.DAY_OF_MONTH);
    }


}
