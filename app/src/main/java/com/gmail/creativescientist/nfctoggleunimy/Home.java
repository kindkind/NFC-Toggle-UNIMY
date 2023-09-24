package com.gmail.creativescientist.nfctoggleunimy;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;

import static com.gmail.creativescientist.nfctoggleunimy.R.layout.nfc_write_pending_popup;

public class Home extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private NFCManager nfcMger;
    private View v;

    Dialog pendDialog, writeDialog, failedDialog;
    Button addRecord;
    Button writeBtn;

    private static final String TAG = "NFCWriteTag";
    private NfcAdapter mNfcAdapter;
    private IntentFilter[] mWriteTagFilters;
    private PendingIntent mNfcPendingIntent;
    private boolean silent = false;
    private boolean writeProtect = false;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        context = getApplicationContext();
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        mNfcPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TOP), 0);
        IntentFilter discovery = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        IntentFilter ndefDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        IntentFilter techDetected = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        // Intent filters for writing to a tag
        mWriteTagFilters = new IntentFilter[]{discovery};


        FirebaseApp.initializeApp(this);

        nfcMger = new NFCManager(this);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        progressBar = findViewById(R.id.progressBar3);

        Toolbar toolbar = findViewById(R.id.toolbar_signup);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        progressBar.setVisibility(View.GONE);

        if (!(firebaseUser != null && firebaseUser.isEmailVerified())) {
            startActivity(new Intent(Home.this, MainActivity.class));
        }

        addRecord = findViewById(R.id.add_record);

        addRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Intent intToAddRecord = new Intent(Home.this, AddRecord2.class);
                startActivity(intToAddRecord);
            }
        });

        pendDialog = new Dialog(this);


    }

    public void ShowPopupPend(View view){
        Button cancelPendBtn;

        pendDialog.setContentView(nfc_write_pending_popup);
        cancelPendBtn = pendDialog.findViewById(R.id.btnCancelWrite);

        cancelPendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pendDialog.dismiss();
            }
        });
        pendDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mNfcAdapter != null) {
            if (!mNfcAdapter.isEnabled()) {
                LayoutInflater inflater = getLayoutInflater();
                View nfcdialoglayout = inflater.inflate(R.layout.nfc_settings_layout, (ViewGroup) findViewById(R.id.nfc_settings_layout));
                new AlertDialog.Builder(this).setView(nfcdialoglayout)
                        .setPositiveButton("Update Settings", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent setnfc = new Intent(Settings.ACTION_NFC_SETTINGS);
                                startActivity(setnfc);
                            }
                        })
                        .setOnCancelListener(new DialogInterface.OnCancelListener() {
                            public void onCancel(DialogInterface dialog) {
                                finish(); // exit application if user cancels
                            }
                        }).create().show();
            }else {
                Toast.makeText(context, "NFC Enabled", Toast.LENGTH_SHORT).show();
            }
            mNfcAdapter.enableForegroundDispatch(this, mNfcPendingIntent, mWriteTagFilters, null);
        } else {
            Toast.makeText(context, "Sorry, No NFC detected in this device", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mNfcAdapter != null) mNfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
            // validate that this tag can be written
            Tag detectedTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            if (supportedTechs(detectedTag.getTechList())) {
                // check if tag is writable (to the extent that we can
                if (writableTag(detectedTag)) {
                    //writeTag here
                    WriteResponse wr = writeTag(getTagAsNdef(), detectedTag);
                    String message = (wr.getStatus() == 1 ? "Success: " : "Failed: ") + wr.getMessage();
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "This tag is not writable", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "This tag type is not supported", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private NdefMessage getTagAsNdef() {
        boolean addAAR = false;
        String uniqueId = "smartwhere.com/nfc.html";
        byte[] uriField = uniqueId.getBytes(Charset.forName("US-ASCII"));
        byte[] payload = new byte[uriField.length + 1];       //add 1 for the URI Prefix
        payload[0] = 0x01;                        //prefixes http://www. to the URI
        System.arraycopy(uriField, 0, payload, 1, uriField.length); //appends URI to payload
        NdefRecord rtdUriRecord = new NdefRecord(
                NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_URI, new byte[0], payload);
        if (addAAR) {
            // note: returns AAR for different app (nfcreadtag)
            return new NdefMessage(new NdefRecord[]{
                    rtdUriRecord, NdefRecord.createApplicationRecord("com.tapwise.nfcreadtag")
            });
        } else {
            return new NdefMessage(new NdefRecord[]{
                    rtdUriRecord});
        }
    }

    public WriteResponse writeTag(NdefMessage message, Tag tag) {
        int size = message.toByteArray().length;
        String mess = "";
        try {
            Ndef ndef = Ndef.get(tag);
            if (ndef != null) {
                ndef.connect();
                if (!ndef.isWritable()) {
                    return new WriteResponse(0, "Tag is read-only");
                }
                if (ndef.getMaxSize() < size) {
                    mess = "Tag capacity is " + ndef.getMaxSize() + " bytes, message is " + size
                            + " bytes.";
                    return new WriteResponse(0, mess);
                }
                ndef.writeNdefMessage(message);
                if (writeProtect) ndef.makeReadOnly();
                mess = "Wrote message to pre-formatted tag.";
                return new WriteResponse(1, mess);
            } else {
                NdefFormatable format = NdefFormatable.get(tag);
                if (format != null) {
                    try {
                        format.connect();
                        format.format(message);
                        mess = "Formatted tag and wrote message";
                        return new WriteResponse(1, mess);
                    } catch (IOException e) {
                        mess = "Failed to format tag.";
                        return new WriteResponse(0, mess);
                    }
                } else {
                    mess = "Tag doesn't support NDEF.";
                    return new WriteResponse(0, mess);
                }
            }
        } catch (Exception e) {
            mess = "Failed to write tag";
            return new WriteResponse(0, mess);
        }
    }

    private class WriteResponse {
        int status;
        String message;

        WriteResponse(int Status, String Message) {
            this.status = Status;
            this.message = Message;
        }

        public int getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }
    }

    public static boolean supportedTechs(String[] techs) {
        boolean ultralight = false;
        boolean nfcA = false;
        boolean ndef = false;
        for (String tech : techs) {
            if (tech.equals("android.nfc.tech.MifareUltralight")) {
                ultralight = true;
            } else if (tech.equals("android.nfc.tech.NfcA")) {
                nfcA = true;
            } else if (tech.equals("android.nfc.tech.Ndef") || tech.equals("android.nfc.tech.NdefFormatable")) {
                ndef = true;
            }
        }

         if(ultralight && nfcA && ndef) {
        return true;
    } else {
        return false;
    }
}

    private boolean writableTag(Tag tag) {
        try {
            Ndef ndef = Ndef.get(tag);
            if (ndef != null) {
                ndef.connect();
                if (!ndef.isWritable()) {
                    Toast.makeText(context, "Tag is read-only.", Toast.LENGTH_SHORT).show();
                    ndef.close();
                    return false;
                }
                ndef.close();
                return true;
            }
        } catch (Exception e) {
            Toast.makeText(context, "Failed to read tag", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option1, menu);
        return true;
    }


        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            progressBar.setVisibility(View.GONE);
            switch (item.getItemId()) {
                case R.id.History:
                    Toast.makeText(this, "History Selected", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.logout:
                    progressBar.setVisibility(View.VISIBLE);
                    FirebaseAuth.getInstance().signOut();
                    Intent intToMain = new Intent(Home.this, MainActivity.class);
                    startActivity(intToMain);
                    Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }

    }

