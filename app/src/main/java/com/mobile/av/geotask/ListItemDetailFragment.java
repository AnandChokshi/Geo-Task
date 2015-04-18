package com.mobile.av.geotask;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.av.geotask.adapters.ItemListArrayAdapter;
import com.mobile.av.geotask.adapters.LocationListArrayAdapter;
import com.mobile.av.geotask.model.Task;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListItemDetailFragment extends Fragment {

    private Task task;

    public ListItemDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        task = bundle.getParcelable(".model.Task");

        View taskDetailView = inflater.inflate(R.layout.fragment_list_item_detail, container, false);

        TextView expirationDate = (TextView) taskDetailView.findViewById(R.id.detail_view_expirationDate_textView);
        TextView range = (TextView) taskDetailView.findViewById(R.id.detail_view_range_textView);
        TextView repeat = (TextView) taskDetailView.findViewById(R.id.detail_view_repeat_textView);
        ListView itemsListView = (ListView) taskDetailView.findViewById(R.id.detail_view_item_listView);
        ListView locationListView = (ListView) taskDetailView.findViewById(R.id.detail_view_location_listView);

        expirationDate.setText(task.getExpr_date());
        range.setText(String.valueOf(task.getRange())+" mts");

        if(task.getRepeat() == 0){
            repeat.setText("NO");
        }else{
            repeat.setText("YES");
        }

        ItemListArrayAdapter itemListArrayAdapter = new ItemListArrayAdapter(
                getActivity(), R.id.detail_view_item_listView, task.getItems());
        itemsListView.setAdapter(itemListArrayAdapter);

        LocationListArrayAdapter locationListArrayAdapter = new LocationListArrayAdapter(
                getActivity(), R.id.detail_view_location_listView, task.getLocation());
        locationListView.setAdapter(locationListArrayAdapter);

        return taskDetailView;
    }


}
