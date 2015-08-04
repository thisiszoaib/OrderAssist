package com.example.zoaib.orderassist;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class CheckoutActivityFragment extends Fragment {

    ArrayAdapter<String> mOrderAdapter;

    public CheckoutActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_checkout, container, false);

        String[] dummyData = {
                "Chicken Tikka",
                "Naan",
                "Raita"
        };

        List<String> orderList = new ArrayList<String>(Arrays.asList(dummyData));

        mOrderAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.list_item_vendor,
                R.id.list_item_vendor_textview,
                orderList
        );

        ListView listView = (ListView)rootView.findViewById(R.id.listview_checkout);
        listView.setAdapter(mOrderAdapter);

        return rootView;
    }
}
