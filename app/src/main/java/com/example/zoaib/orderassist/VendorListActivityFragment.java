package com.example.zoaib.orderassist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class VendorListActivityFragment extends Fragment {

    ArrayAdapter<String> mVendorAdapter;

    public VendorListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_vendor_list, container, false);

        String[] dummyData = {
                "Faisal Foods",
                "Indian Kitchen",
                "Dunkin Donuts"
        };

        List<String> vendorList = new ArrayList<String>(Arrays.asList(dummyData));

        mVendorAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.list_item_vendor,
                R.id.list_item_vendor_textview,
                vendorList
        );

        ListView listView = (ListView)rootView.findViewById(R.id.listview_vendor);
        listView.setAdapter(mVendorAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getActivity(), "Clicked List", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),FoodListActivity.class);
                startActivity(intent);
            }
        });



        return rootView;
    }
}
