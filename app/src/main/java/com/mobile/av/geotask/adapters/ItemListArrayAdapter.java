package com.mobile.av.geotask.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.mobile.av.geotask.R;
import com.mobile.av.geotask.model.Item;

import java.util.ArrayList;

/**
 * Created by VIRAL on 4/14/2015.
 */
public class ItemListArrayAdapter extends ArrayAdapter<Item> {

    private Context context;
    private ArrayList<Item> itemsList;


    public ItemListArrayAdapter(Context context, int resource, ArrayList<Item> itemsList) {
        super(context, resource, itemsList);
        this.context = context;
        this.itemsList = itemsList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.detail_view_item_listview_row, null, true);

        CheckBox status = (CheckBox) convertView.findViewById(R.id.detail_view_item_list_checkBox);
        Button notesButton = (Button) convertView.findViewById(R.id.detail_view_item_list_notesButton);

        status.setText(itemsList.get(position).getName());
        if(itemsList.get(position).getStatus() == 0){
            status.setChecked(false);
        }else {
            status.setChecked(true);
        }
        status.setClickable(false);

        notesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("NOTE");
                builder.setMessage(itemsList.get(position).getNote());
                builder.create().show();
            }
        });

        return convertView;
    }
}
