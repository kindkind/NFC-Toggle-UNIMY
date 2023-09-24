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

public class pageWificonf extends AppCompatActivity {

    private Spinner authSpinner, encryptSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_wificonf);

        authSpinner = findViewById(R.id.wificonfAuthSpinner2);
        encryptSpinner = findViewById(R.id.wificonfEncryptSpinner2);


        List<String> authOption = new ArrayList<>();
        authOption.add(0, "Authentication");
        authOption.add("Open");
        authOption.add("WPA-Personal");
        authOption.add("Shared");
        authOption.add("WPA-Enterprise");
        authOption.add("WPA2-Enterprise");
        authOption.add("WPA2-Enterprise");
        authOption.add("WPA/WPA2-Personal");

        List<String> encryptOption = new ArrayList<>();
        encryptOption.add(0, "Encryption");
        encryptOption.add("None");
        encryptOption.add("WEP");
        encryptOption.add("TKIP");
        encryptOption.add("AES");
        encryptOption.add("AES/TKIP");


        ArrayAdapter<String> authDataAdapter;
        authDataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, authOption);

        authDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        authSpinner.setAdapter(authDataAdapter);

        authSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                if (parent.getItemAtPosition(position).equals("Authentication")){

                    ///do nothing

                }else{
                    String item = parent.getItemAtPosition(position).toString();

                    Toast.makeText(parent.getContext(), "Authentication : " +item, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> encryptDataAdapter;
        encryptDataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, encryptOption);

        encryptDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        encryptSpinner.setAdapter(encryptDataAdapter);

        encryptSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                if (parent.getItemAtPosition(position).equals("Authentication")){

                    ///do nothing

                }else{
                    String item = parent.getItemAtPosition(position).toString();

                    Toast.makeText(parent.getContext(), "Authentication : " +item, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
