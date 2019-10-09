package com.example.photogallery;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

public class SearchFragment extends Fragment {

    private EditText fromDate;
    private EditText toDate;
    private Calendar fromCalendar;
    private Calendar toCalendar;
    private DatePickerDialog.OnDateSetListener fromListener;
    private DatePickerDialog.OnDateSetListener toListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        fromDate = (EditText) rootView.findViewById(R.id.search_fromDate);
        toDate   = (EditText) rootView.findViewById(R.id.search_toDate);

        return rootView;

    }


    public void cancel(final View v) {
        getActivity().getFragmentManager().popBackStack();
    }

    public void search2(final View v) {
//        Intent i = new Intent();
//        i.putExtra("STARTDATE", fromDate.getText().toString());
//        i.putExtra("ENDDATE", toDate.getText().toString());
//        getActivity().setResult(Activity.RESULT_OK, i);
//        getActivity().getFragmentManager().popBackStack();

        //below should be in MainActivity
        String fromDateText = fromDate.getText().toString();
        String toDateText = fromDate.getText().toString();
        int  fromDateInt = Integer.parseInt(fromDateText);
        int  toDateInt = Integer.parseInt(toDateText);

        PhotosFragment f = new PhotosFragment();
        Bundle args = new Bundle();
        args.putInt("startIndex", fromDateInt);
        args.putInt("endIndex", toDateInt);
        f.setArguments(args);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                f).commit();
    }
}