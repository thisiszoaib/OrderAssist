package com.example.zoaib.orderassist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A placeholder fragment containing a simple view.
 */
public class FoodListActivityFragment extends Fragment {

    public FoodListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_food_list, container, false);

        Button foodClick = (Button)rootView.findViewById(R.id.button_food);
        foodClick.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getActivity(), "Added Item to Order", Toast.LENGTH_SHORT).show();
//                Intent vendorIntent = new Intent(getActivity(),VendorListActivity.class);
//                startActivity(vendorIntent);
            }

        });

        return rootView;
    }
}
