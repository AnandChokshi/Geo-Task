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
public class ItemDetailFragment extends Fragment {

    private Task task;

    public ItemDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        task = bundle.getParcelable(".model.Task");

        View taskDetailView = inflater.inflate(R.layout.fragment_list_item_detail, container, false);


        return taskDetailView;
    }
}
