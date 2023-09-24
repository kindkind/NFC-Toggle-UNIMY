package com.gmail.creativescientist.nfctoggleunimy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class pageWifitog extends AppCompatActivity {

    private Spinner wifitogSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_wifitog);

        wifitogSpinner = findViewById(R.id.wifitogSpinner2);

        List<String> wifitogOption = new ArrayList<>();
        wifitogOption.add(0, "Choose One");
        wifitogOption.add("Disable");
        wifitogOption.add("Enable");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, wifitogOption);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        wifitogSpinner.setAdapter(dataAdapter);

        wifitogSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                if (parent.getItemAtPosition(position).equals("Choose One")){

                    ///do nothing

                }else{
                    String item = parent.getItemAtPosition(position).toString();

                    Toast.makeText(parent.getContext(), "Selected: " +item, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
