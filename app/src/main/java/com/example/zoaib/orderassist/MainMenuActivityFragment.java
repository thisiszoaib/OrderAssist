package com.example.zoaib.orderassist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainMenuActivityFragment extends Fragment {

    public MainMenuActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main_menu, container, false);

        Button vendorClick = (Button)rootView.findViewById(R.id.button_vendorlist);
        vendorClick.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent vendorIntent = new Intent(getActivity(),VendorListActivity.class);
                startActivity(vendorIntent);
            }

        });

        Button orderHistoryClick = (Button)rootView.findViewById(R.id.button_orderlist);
        orderHistoryClick.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent vendorIntent = new Intent(getActivity(),OrderViewActivity.class);
                startActivity(vendorIntent);
            }

        });



        return rootView;
    }
}
