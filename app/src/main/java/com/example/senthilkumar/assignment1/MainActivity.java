package com.example.senthilkumar.assignment1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements FragmentCommunicator {
    TabLayout tabLayout;
    ViewPager viewPager;
    ListView items;
    ArrayList<String> itemsList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.view);
        CustomAdapter cusAdapter = new CustomAdapter(getSupportFragmentManager(), getApplicationContext());
        viewPager.setAdapter(cusAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // To hide the soft keyboard when switch to other tabs.
                if (getCurrentFocus() != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //Restore the saved shared prefs
        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        Set<String> savedItems = settings.getStringSet("Items", null);

        if (savedItems != null) {
            itemsList = new ArrayList<>(savedItems);
        }
    }

    @Override
    public void AddItem(String data) {
        // Why can't I do this part inside ToDoFrag2?
        // where the context returns null !! Why?
        itemsList.add(data);
//        ToDo_Fragment2 frag2 = new ToDo_Fragment2();
//        frag2.AddItem(itemsList);

        items = (ListView) findViewById(R.id.listView_items);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsList);
        items.setAdapter(adapter);
    }

    @Override
    public void DeleteItem(String item) {
        itemsList.remove(item);
        if(adapter != null) //Before entering new items, adapter is null...
            adapter.notifyDataSetChanged();
    }

    @Override
    public ArrayList<String> GetSavedItems() {
        return itemsList;
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Backup the ToDoItems for future use
        SharedPreferences prefs = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        LinkedHashSet<String> savedItems = new LinkedHashSet<>(itemsList);
        editor.putStringSet("Items", savedItems);
        editor.apply();
    }
}