package com.gmail.creativescientist.nfctoggleunimy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class pageBright extends AppCompatActivity {
    TextView percent;
    SeekBar brightSeekbar;
    int value=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_bright);

        percent = findViewById(R.id.progressPercentBright);
        brightSeekbar = findViewById(R.id.seekBarBright);

        brightSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar brightSeekbar, int i, boolean b) {
                percent.setText(""+i+"%");
                value=i;

            }

            @Override
            public void onStartTrackingTouch(SeekBar brightSeekbar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar brightSeekbar) {
                Toast.makeText(pageBright.this,"Brightness : "+value, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
