package com.gmail.creativescientist.nfctoggleunimy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class pageUrl extends AppCompatActivity {

    private Spinner urlSpinner;
    private NdefMessage urlMessage = null;
    Button urlConfbtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_url);

        urlSpinner = findViewById(R.id.urlSpinner2);
        urlConfbtn = findViewById(R.id.confirm_url);

        List<String> urlType = new ArrayList<>();
        urlType.add(0, "Pick One");
        urlType.add("http://");
        urlType.add("https://");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, urlType);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        urlSpinner.setAdapter(dataAdapter);

        urlSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                if (parent.getItemAtPosition(position).equals("Pick One")) {

                    ///do nothing

                } else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        urlConfbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

}
