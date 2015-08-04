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
public class OrderViewActivityFragment extends Fragment {

    private ArrayAdapter<String> mOrderAdapter;

    public OrderViewActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_order_view, container, false);

        String[] dummyData1 = {
                "Order 1",
                "Order 2",
                "Order 3",
                "Order 4"
        };

        List<String> orderList = new ArrayList<String>(Arrays.asList(dummyData1));

        mOrderAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.list_item_order,
                R.id.list_item_order_textview,
                orderList
        );

        ListView listView1 = (ListView)rootView.findViewById(R.id.listview_orderview);
        listView1.setAdapter(mOrderAdapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Selected this Order", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),MainMenuActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
