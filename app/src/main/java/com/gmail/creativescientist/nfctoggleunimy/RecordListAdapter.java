package com.gmail.creativescientist.nfctoggleunimy;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import  java.util.List;

public class RecordListAdapter extends ArrayAdapter<Record> {

    private static final String TAG = "RecordListAdapter";

    private Context mContext;
    int mResource;

    /**
     * Default constructor for the RecordListAdapter
     * @param context
     * @param resource
     * @param objects
     */

    public RecordListAdapter(Context context, int resource, ArrayList<Record> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get record information
        String record = getItem(position).getRecordName();

        //Create the record object with the information
        Record record1 = new Record(record);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        Button btnRecord = convertView.findViewById(R.id.recordbtn);

        btnRecord.setText(record);

        return convertView;
    }

}
