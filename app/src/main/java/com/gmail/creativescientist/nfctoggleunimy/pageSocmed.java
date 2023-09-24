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

public class pageSocmed extends AppCompatActivity {

    private Spinner socmedSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_socmed);

        socmedSpinner = findViewById(R.id.socmedSpinner2);

        List<String> socmedType = new ArrayList<>();
        socmedType.add(0, "Choose One");
        socmedType.add("Instagram");
        socmedType.add("Twitter");
        socmedType.add("Facebook");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, socmedType);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        socmedSpinner.setAdapter(dataAdapter);

        socmedSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
