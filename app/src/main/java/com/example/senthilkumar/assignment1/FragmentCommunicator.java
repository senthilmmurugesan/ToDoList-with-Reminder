package com.example.senthilkumar.assignment1;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Senthil Kumar on 2/21/2016.
 */
public interface FragmentCommunicator {
    void AddItem(String data);
    void DeleteItem(String item);
    ArrayList<String> GetSavedItems();
}
