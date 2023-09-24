
package com.gmail.creativescientist.nfctoggleunimy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

public class AddRecord extends AppCompatActivity {

    private static final String TAG = "AddRecord";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        Toolbar toolbar = findViewById(R.id.toolbar_signup);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);


        Log.d(TAG, "onCreate: Started.");
        ListView mListView = findViewById(R.id.listViewRecord);

        //Create the record objects
        Record text = new Record("Text           | Add a text record");
        Record url = new Record("URL            | Add a URL record");
        Record search = new Record("Search         | Add a link to a search");
        Record socmed = new Record("Social media   | Add a social network link");
        Record vid = new Record("Video          | Add a video link");
        Record mail = new Record("E-mail         | Add e-mail record");
        Record contact = new Record("Contact        | Add contact");
        Record sms = new Record("SMS            | Add SMS");
        Record lokasi = new Record("Location       | Add a location");
        Record wificonf = new Record("Wi-Fi network  | Configure a Wi-Fi network");
        Record phone = new Record("*Phone dial     | Dial a number");
        Record wifitog = new Record("*Wi-Fi Toggle   | On/off Wi-Fi");
        Record bright = new Record("*Brightness     | Set brightness");
        Record soundprof = new Record("*Sound profile  | Mute / Vibrate / Normal");
        Record timer = new Record("*Timer          | Set timer");

        //Add the record objects to an ArrayList
        ArrayList<Record> recordList = new ArrayList<>();
        recordList.add(text);
        recordList.add(url);
        recordList.add(search);
        recordList.add(socmed);
        recordList.add(vid);
        recordList.add(mail);
        recordList.add(contact);
        recordList.add(sms);
        recordList.add(lokasi);
        recordList.add(wificonf);
        recordList.add(phone);
        recordList.add(wifitog);
        recordList.add(bright);
        recordList.add(soundprof);
        recordList.add(timer);

        RecordListAdapter adapter = new RecordListAdapter(this, R.layout.adapter_view_layout, recordList);
        mListView.setAdapter(adapter);


    }
}
