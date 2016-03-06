package com.example.senthilkumar.assignment1;


import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {
    View view;
    private static boolean STATUS = true;
    private TextView messageText;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button resetButton;
    TableLayout tableLayout;
    private String[][] positions = new String[3][3];

    public Fragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_2, container, false);
        tableLayout = (TableLayout) view.findViewById(R.id.tableLayout1);
        messageText = (TextView) view.findViewById(R.id.moveTextView);

        button1 = (Button) view.findViewById(R.id.button1);
        button2 = (Button) view.findViewById(R.id.button2);
        button3 = (Button) view.findViewById(R.id.button3);
        button4 = (Button) view.findViewById(R.id.button4);
        button5 = (Button) view.findViewById(R.id.button5);
        button6 = (Button) view.findViewById(R.id.button6);
        button7 = (Button) view.findViewById(R.id.button7);
        button8 = (Button) view.findViewById(R.id.button8);
        button9 = (Button) view.findViewById(R.id.button9);

        button1.setOnClickListener(clickListener);
        button2.setOnClickListener(clickListener);
        button3.setOnClickListener(clickListener);
        button4.setOnClickListener(clickListener);
        button5.setOnClickListener(clickListener);
        button6.setOnClickListener(clickListener);
        button7.setOnClickListener(clickListener);
        button8.setOnClickListener(clickListener);
        button9.setOnClickListener(clickListener);

        resetButton = (Button) view.findViewById(R.id.resetButton);
        resetButton.setOnClickListener(resetClickListener);
        return view;
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String text;
            text = STATUS ? "X" : "O";
            Button selectedButton = (Button) v;
            selectedButton.setText(text);
            selectedButton.setTextSize(50);
            selectedButton.setEnabled(false);
            STATUS = !STATUS;
            CreatePositions(view);
            ValidatePositions(view);
        }
    };

    private View.OnClickListener resetClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            STATUS = true;
            messageText.setText("");
            for(String[] temp : positions)
                Arrays.fill(temp, "");
            for(int i = 0; i <tableLayout.getChildCount(); i++) {
                TableRow tableRow = (TableRow) tableLayout.getChildAt(i);
                for(int j = 0; j < tableRow.getChildCount(); j++) {
                    Button button = (Button) tableRow.getChildAt(j);
                    button.setText("");
                    button.setEnabled(true);
                    button.setBackgroundResource(android.R.drawable.btn_default);
                }
            }
        }
    };

    public void CreatePositions(View v) {

        TableLayout tableLayout = (TableLayout) v.findViewById(R.id.tableLayout1);
        for(int i = 0; i <tableLayout.getChildCount(); i++) {
            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);
            for(int j = 0; j < tableRow.getChildCount(); j++) {
                Button button = (Button) tableRow.getChildAt(j);
                if (!button.isEnabled())
                    positions[i][j] = button.getText().toString();
            }
        }
    }

    public void ValidatePositions(View v) {
        String count = "";
        for(int i = 0; i < positions.length; i++) {
            for(int j = 0; j < positions.length; j++) {
                if(positions[i][j] != null)
                    count = count + positions[i][j];
            }
            switch(count) {
                case "XXX":
                    ShowWinner(v, "X", "Row", i);
                    break;
                case "OOO":
                    ShowWinner(v, "O", "Row", i);
                    break;
            }
            count = "";
        }

        count = "";
        for(int i = 0; i < positions.length; i++) {
            for(int j = 0; j < positions.length; j++) {
                if(positions[j][i] != null)
                    count = count + positions[j][i];
            }
            switch(count) {
                case "XXX":
                    ShowWinner(v, "X", "Column", i);
                    break;
                case "OOO":
                    ShowWinner(v, "O", "Column", i);
                    break;
            }
            count = "";
        }

        count = "";
        for(int i = 0, j = 0; i < positions.length && j < positions.length; i++, j++) {
            if(positions[i][j] != null)
                count = count + positions[i][j];
        }
        switch(count) {
            case "XXX":
                ShowWinner(v, "X", "D1", 1);
                break;
            case "OOO":
                ShowWinner(v, "O", "D1", 1);
                break;
            default:
                count = "";
        }

        count = "";
        for(int i = 0, j = 2; i < positions.length && j < positions.length; i++, j--) {
            if (positions[i][j] != null)
                count = count + positions[i][j];
        }
        switch(count) {
            case "XXX":
                ShowWinner(v, "X", "D2", 1);
                break;
            case "OOO":
                ShowWinner(v, "O", "D2", 1);
                break;
            default:
                count = "";
        }
    }

    public void ShowWinner(View v, String player, String align, int num) {
        TableLayout tableLayout = (TableLayout) v.findViewById(R.id.tableLayout1);
        if("Row".equals(align)) {
            TableRow tableRow = (TableRow) tableLayout.getChildAt(num);
            messageText.setText(getString(R.string.winner) + player);
            for (int i = 0; i < tableRow.getChildCount(); i++) {
                tableRow.getChildAt(i).setBackgroundColor(Color.YELLOW);
            }
        }
        else if("Column".equals(align)){
            messageText.setText(getString(R.string.winner) + player);

            for(int i = 0; i < tableLayout.getChildCount(); i++) {
                TableRow tableRow = (TableRow) tableLayout.getChildAt(i);
                tableRow.getChildAt(num).setBackgroundColor(Color.YELLOW);
            }
        }
        else if("D1".equals(align)) {
            messageText.setText(getString(R.string.winner) + player);
            for(int i = 0; i < tableLayout.getChildCount(); i++) {
                TableRow tableRow = (TableRow) tableLayout.getChildAt(i);
                tableRow.getChildAt(i).setBackgroundColor(Color.YELLOW);
            }
        }
        else if("D2".equals(align)) {
            messageText.setText(getString(R.string.winner) + player);

            for(int i = 0; i < tableLayout.getChildCount(); i++) {
                TableRow tableRow = (TableRow) tableLayout.getChildAt(i);
                tableRow.getChildAt(Math.abs(2-i)).setBackgroundColor(Color.YELLOW);
            }
        }

        DisableAllButtons(v);
    }

    public void DisableAllButtons(View v) {
        TableLayout tableLayout = (TableLayout) v.findViewById(R.id.tableLayout1);
        for(int i = 0; i <tableLayout.getChildCount(); i++) {
            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);
            for(int j = 0; j < tableRow.getChildCount(); j++) {
                Button button = (Button) tableRow.getChildAt(j);
                button.setEnabled(false);
            }
        }
    }

    @Override
    public void onDestroy() {
        STATUS = true;
        super.onDestroy();
    }
}
