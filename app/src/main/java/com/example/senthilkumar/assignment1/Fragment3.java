package com.example.senthilkumar.assignment1;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends Fragment  {
    Spinner dateSpinner, timeSpinner;
    EditText editText_Name, editText_PhNum;
    AutoCompleteTextView todoList;
    Button makeButton;
    View view;
    FragmentCommunicator fragmentCommunicator;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentCommunicator = (FragmentCommunicator) context;
    }

    public Fragment3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_3, container, false);

        dateSpinner = (Spinner) view.findViewById(R.id.spinner_date);
        dateSpinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    showDatePickerDialog(v);
                }
                return true;
            }
        });

        timeSpinner = (Spinner) view.findViewById(R.id.spinner_time);
        timeSpinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    showTimePickerDialog(v);
                }
                return true;
            }
        });

        editText_Name = (EditText) view.findViewById(R.id.editText_Name);
        editText_PhNum = (EditText) view.findViewById(R.id.editText_PhNum);
        todoList = (AutoCompleteTextView) view.findViewById(R.id.autoCompleteTextView);

        ArrayList<String> todoItems = fragmentCommunicator.GetSavedItems();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, todoItems);
        todoList.setThreshold(1);
        todoList.setAdapter(adapter);

        makeButton = (Button) view.findViewById(R.id.makeButton);
        makeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText_Name.getText().toString();
                String item = todoList.getText().toString();
                String date = dateSpinner.getSelectedItem().toString();
                String time = timeSpinner.getSelectedItem().toString();
                if(!"".equals(name.trim()) && date.contains("/") && time.contains(":") && !"".equals(item.trim()))
                    AskConfirmation(name, item, date, time);
                else
                    Toast.makeText(getActivity(), "Enter all mandatory fields!", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    private void AskConfirmation(String name, String todo, String date, String time) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Set Reminder");
        builder.setItems(new String[]{"Name: " + name, todo, "Date : " + date, "Time : " + time}, null);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Done!!!", Toast.LENGTH_LONG).show();
                editText_Name.setText("");
                editText_PhNum.setText("");
                todoList.setText("");
                makeButton.requestFocus();
            }
        });

        builder.show();
    }

    private void showTimePickerDialog(View v) {
        DialogFragment timeFragment = new TimePickerFragment();
        timeFragment.show(getActivity().getFragmentManager(), "Time");
    }

    private void showDatePickerDialog(View v) {
        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getActivity().getFragmentManager(), "Date");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
