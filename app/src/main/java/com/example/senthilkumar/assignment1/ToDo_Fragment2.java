package com.example.senthilkumar.assignment1;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToDo_Fragment2 extends Fragment {
    ListView allItems;
    FragmentCommunicator comm;
    Context context;
    View view;
    ArrayList<String> savedList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    public ToDo_Fragment2() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        comm = (FragmentCommunicator) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.to_do__fragment2, container, false);
        allItems = (ListView) view.findViewById(R.id.listView_items);

        // Get saved list and display it on ListView
        savedList = comm.GetSavedItems();
        if(!savedList.isEmpty()) {
            adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1, savedList);
            allItems.setAdapter(adapter);
        }

        allItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShowMessage(allItems.getItemAtPosition(position).toString());
            }
        });
        return view;
    }

    public void ShowMessage(final String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("ToDo Item").setMessage(message);
        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                savedList = comm.GetSavedItems();
                comm.DeleteItem(message);
                savedList.remove(message);
                adapter.notifyDataSetChanged();
            }
        });

        builder.show();
    }

    public void AddItem(ArrayList<String> itemsList) {
        ListView items = (ListView) getActivity().findViewById(R.id.listView_items);  // getActivity() returning NULL
        ArrayAdapter adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, itemsList);
        items.setAdapter(adapter);
    }
}
