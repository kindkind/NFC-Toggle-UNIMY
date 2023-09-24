package com.gmail.creativescientist.nfctoggleunimy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class pageTimer extends AppCompatActivity {

    private Spinner hourSpinner, minSpinner, secSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_timer);

        hourSpinner = findViewById(R.id.hourSpinner2);
        minSpinner = findViewById(R.id.minSpinner2);
        secSpinner = findViewById(R.id.secSpinner2);

        List<String> hourNumber = new ArrayList<>();
        hourNumber.add(0, "Hour"); hourNumber.add("0"); hourNumber.add("1"); hourNumber.add("2"); hourNumber.add("3");
        hourNumber.add("4"); hourNumber.add("5"); hourNumber.add("6"); hourNumber.add("7"); hourNumber.add("8"); hourNumber.add("9");
        hourNumber.add("10"); hourNumber.add("11"); hourNumber.add("12"); hourNumber.add("13"); hourNumber.add("14"); hourNumber.add("15");
        hourNumber.add("16"); hourNumber.add("17"); hourNumber.add("18"); hourNumber.add("19"); hourNumber.add("20"); hourNumber.add("21");
        hourNumber.add("22"); hourNumber.add("23"); hourNumber.add("24");

        List<String> minNumber = new ArrayList<>();
        minNumber.add(0, "Minutes"); minNumber.add("0"); minNumber.add("1"); minNumber.add("2"); minNumber.add("3"); minNumber.add("4");
        minNumber.add("5"); minNumber.add("6"); minNumber.add("7"); minNumber.add("8"); minNumber.add("9"); minNumber.add("10"); minNumber.add("11");
        minNumber.add("12"); minNumber.add("13"); minNumber.add("14"); minNumber.add("15"); minNumber.add("16"); minNumber.add("17"); minNumber.add("18");
        minNumber.add("19"); minNumber.add("20"); minNumber.add("21"); minNumber.add("22"); minNumber.add("23"); minNumber.add("24"); minNumber.add("25");
        minNumber.add("26"); minNumber.add("27"); minNumber.add("28"); minNumber.add("29"); minNumber.add("30"); minNumber.add("31"); minNumber.add("32");
        minNumber.add("33"); minNumber.add("34"); minNumber.add("35"); minNumber.add("36"); minNumber.add("37"); minNumber.add("38"); minNumber.add("39");
        minNumber.add("40"); minNumber.add("41"); minNumber.add("42"); minNumber.add("43"); minNumber.add("44"); minNumber.add("45"); minNumber.add("46");
        minNumber.add("47"); minNumber.add("48"); minNumber.add("49"); minNumber.add("50"); minNumber.add("51"); minNumber.add("52"); minNumber.add("53");
        minNumber.add("54"); minNumber.add("55"); minNumber.add("56"); minNumber.add("57"); minNumber.add("58"); minNumber.add("59"); minNumber.add("60");

        List<String> secNumber = new ArrayList<>();
        secNumber.add(0, "Seconds"); secNumber.add("0"); secNumber.add("1"); secNumber.add("2"); secNumber.add("3"); secNumber.add("4"); secNumber.add("5");
        secNumber.add("6"); secNumber.add("7"); secNumber.add("8"); secNumber.add("9"); secNumber.add("10"); secNumber.add("11"); secNumber.add("12");
        secNumber.add("13"); secNumber.add("14"); secNumber.add("15"); secNumber.add("16"); secNumber.add("17"); secNumber.add("18"); secNumber.add("19");
        secNumber.add("20"); secNumber.add("21"); secNumber.add("22"); secNumber.add("23"); secNumber.add("24"); secNumber.add("25"); secNumber.add("26");
        secNumber.add("27"); secNumber.add("28"); secNumber.add("29"); secNumber.add("30"); secNumber.add("31"); secNumber.add("32"); secNumber.add("33");
        secNumber.add("34"); secNumber.add("35"); secNumber.add("36"); secNumber.add("37"); secNumber.add("38"); secNumber.add("39"); secNumber.add("40");
        secNumber.add("41"); secNumber.add("42"); secNumber.add("43"); secNumber.add("44"); secNumber.add("45"); secNumber.add("46"); secNumber.add("47");
        secNumber.add("48"); secNumber.add("49"); secNumber.add("50"); secNumber.add("51"); secNumber.add("52"); secNumber.add("53"); secNumber.add("54");
        secNumber.add("55"); secNumber.add("56"); secNumber.add("57"); secNumber.add("58"); secNumber.add("59"); secNumber.add("60");

        ArrayAdapter<String> hourDataAdapter;
        hourDataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, hourNumber);

        hourDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        hourSpinner.setAdapter(hourDataAdapter);

        hourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                if (parent.getItemAtPosition(position).equals("Hour")){

                    ///do nothing

                }else{
                    String item = parent.getItemAtPosition(position).toString();

                    Toast.makeText(parent.getContext(), "Hour : " +item, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> minDataAdapter;
        minDataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, minNumber);

        minDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        minSpinner.setAdapter(minDataAdapter);

        minSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                if (parent.getItemAtPosition(position).equals("Minutes")) {

                }else{
                    String item = parent.getItemAtPosition(position).toString();

                    Toast.makeText(parent.getContext(), "Minutes : " +item, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> secDataAdapter;
        secDataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, secNumber);

        secDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        secSpinner.setAdapter(secDataAdapter);

        secSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                if (parent.getItemAtPosition(position).equals("Seconds")) {

                }else{
                    String item = parent.getItemAtPosition(position).toString();


                    Toast.makeText(parent.getContext(), "Seconds : " +item, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

}
