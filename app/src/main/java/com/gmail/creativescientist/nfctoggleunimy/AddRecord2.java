package com.gmail.creativescientist.nfctoggleunimy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class AddRecord2 extends AppCompatActivity {

    Button textPage, urlPage, searchPage, socmedPage, vidPage, emailPage, contactPage, smsPage, lokasiPage, wificonfPage, phonePage, wifitogPage, brightPage, soundprofPage, timerPage;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record2);

        Toolbar toolbar = findViewById(R.id.toolbar_signup);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        textPage = findViewById(R.id.textRec);
        urlPage = findViewById(R.id.URLRec);
        searchPage = findViewById(R.id.searchRec);
        socmedPage = findViewById(R.id.socmedRec);
        vidPage = findViewById(R.id.vidRec);
        emailPage = findViewById(R.id.emailRec);
        contactPage = findViewById(R.id.contactRec);
        smsPage = findViewById(R.id.smsRec);
        lokasiPage = findViewById(R.id.lokasiRec);
        wificonfPage = findViewById(R.id.wificonfRec);
        phonePage = findViewById(R.id.phoneRec);
        wifitogPage = findViewById(R.id.wifitogRec);
        brightPage = findViewById(R.id.brightRec);
        soundprofPage = findViewById(R.id.soundprofRec);
        timerPage = findViewById(R.id.timerRec);

        textPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToAddRecord = new Intent(AddRecord2.this,pageText.class);
                startActivity(intToAddRecord);
            }
        });

        urlPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToAddRecord = new Intent(AddRecord2.this,pageUrl.class);
                startActivity(intToAddRecord);
            }
        });

        searchPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToAddRecord = new Intent(AddRecord2.this,pageSearch.class);
                startActivity(intToAddRecord);
            }
        });

        socmedPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToAddRecord = new Intent(AddRecord2.this,pageSocmed.class);
                startActivity(intToAddRecord);
            }
        });

        vidPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToAddRecord = new Intent(AddRecord2.this,pageVid.class);
                startActivity(intToAddRecord);
            }
        });

        emailPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToAddRecord = new Intent(AddRecord2.this,pageEmail.class);
                startActivity(intToAddRecord);
            }
        });

        contactPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToAddRecord = new Intent(AddRecord2.this,pageContact.class);
                startActivity(intToAddRecord);
            }
        });

        smsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToAddRecord = new Intent(AddRecord2.this,pageSms.class);
                startActivity(intToAddRecord);
            }
        });

        lokasiPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToAddRecord = new Intent(AddRecord2.this,pageLokasi.class);
                startActivity(intToAddRecord);
            }
        });

        wificonfPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToAddRecord = new Intent(AddRecord2.this,pageWificonf.class);
                startActivity(intToAddRecord);
            }
        });

        phonePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToAddRecord = new Intent(AddRecord2.this,pagePhone.class);
                startActivity(intToAddRecord);
            }
        });

        wifitogPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToAddRecord = new Intent(AddRecord2.this,pageWifitog.class);
                startActivity(intToAddRecord);
            }
        });

        brightPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToAddRecord = new Intent(AddRecord2.this,pageBright.class);
                startActivity(intToAddRecord);
            }
        });

        soundprofPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToAddRecord = new Intent(AddRecord2.this,pageSoundprof.class);
                startActivity(intToAddRecord);
            }
        });

        timerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToAddRecord = new Intent(AddRecord2.this,pageTimer.class);
                startActivity(intToAddRecord);
            }
        });

    }


}