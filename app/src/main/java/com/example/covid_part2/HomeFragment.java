package com.example.covid_part2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.covid_part2.MainActivity.adapter;

/**
 * Created by Mom on 05-04-2020.
 */

public class HomeFragment extends Fragment {

    public static TextView cases1,deaths,recovered;
    public static Context context;
    //the listview
    EditText sv;
    public static ListView listView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


            //super.onCreate(savedInstanceState);
           // setContentView(R.layout.activity_main);
            View view= inflater.inflate(R.layout.fragment_home,container,false);
        listView = view.findViewById(R.id.lv);

            sv =view.findViewById(R.id.search);
//           sv.setFocusableInTouchMode(true);

            sv.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                String s=sv.getText().toString().toLowerCase(Locale.getDefault());

                List<Country_class> ad=MainActivity.adapter.filter(s.toString());

                HomeFragment.listView.setAdapter(new MyListAdapter(HomeFragment.context,R.layout.my_custom_list,ad));


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub

                //adapter = new ListViewAdapter(MainActivity.this, array_sort);
                //list.setAdapter(adapter);
            }
        });











            context = getActivity();

            cases1=view.findViewById(R.id.tc);
            deaths=view.findViewById(R.id.td);
            recovered=view.findViewById(R.id.tr);

            //MainActivity.cases=(cases.getText().toString());
           // MainActivity.deaths=(deaths.getText().toString());
           // MainActivity.recovered=(recovered.getText().toString());
            fetchdata process=new fetchdata();
            process.execute();
           // cases1.setText(MainActivity.cases);
           // deaths.setText(MainActivity.deaths);
           // recovered.setText(MainActivity.recovered);

            /*
            System.out.print("333333333333333333333333333"+MainActivity.cases.equals("000"));
            if(MainActivity.cases.equals("000"))
            {

            }
            cases1.setText(MainActivity.cases);
            deaths.setText(MainActivity.deaths);
            recovered.setText(MainActivity.recovered);
            */




            if(MainActivity.cList.size()==0) {
                fetchCountrydata processcountry = new fetchCountrydata();
                processcountry.execute();

            }
            else
            {
                adapter = new MyListAdapter(HomeFragment.context , R.layout.my_custom_list, MainActivity.cList);

                //attaching adapter to the listview

                HomeFragment.listView.setAdapter(adapter);
            }

        ImageView refresh=view.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.cList.clear();
                fetchCountrydata processcountry = new fetchCountrydata();
                processcountry.execute();
                Toast.makeText(context,"Reload...",Toast.LENGTH_LONG).show();
            }
        });

     return view;
    }



}
