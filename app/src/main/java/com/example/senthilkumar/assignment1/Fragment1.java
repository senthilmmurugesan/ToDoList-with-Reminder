package com.example.senthilkumar.assignment1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {
    View view;

    public Fragment1() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            view = inflater.inflate(R.layout.fragment_1, container, false);
        } catch (InflateException e) {
            // Why do I get Inflate Exception?
            //Toast.makeText(getActivity(), " Frag1 Null", Toast.LENGTH_SHORT).show();
            return view;
        }
        return view;
    }

}
