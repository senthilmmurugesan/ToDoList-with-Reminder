package com.example.senthilkumar.assignment1;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class ToDo_Fragment1 extends Fragment {

    EditText item;
    FragmentCommunicator comm;
    public ToDo_Fragment1() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        comm = (FragmentCommunicator) context;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.to_do__fragment1, container, false);
        item = (EditText) view.findViewById(R.id.editText_item);

        item.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>0 && s.subSequence(s.length()-1, s.length()).toString().equals("\n")) {
                    String newItem = item.getText().toString();
                    newItem = newItem.substring(0, newItem.length() - 1);
                    comm.AddItem(newItem);
                    item.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }


}
