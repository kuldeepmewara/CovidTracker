package com.example.covid_part2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.CALL_PHONE;


public class HelpFragment extends Fragment {
    private static Context context;

    static List<HelpLine_class> hp= new ArrayList<>();
    public  static ListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_help,container,false);

        lv=view.findViewById(R.id.helpline);

        context=getActivity();




        fetchHelplinedata processcountry = new fetchHelplinedata();
        processcountry.execute();

        Log.i("sdffgsvsdv",hp.size()+"");



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick (AdapterView < ? > adapter, View view,int position, long arg){
                                            // TODO Auto-generated method stub
                                            TextView v = (TextView) view.findViewById(R.id.phone_no);
                                           // Toast.makeText(context, "selected Item Name is " + v.getText(), Toast.LENGTH_LONG).show();

                                            String phone = "tel:" +v.getText();
                                            Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse(phone));

                                            if (ContextCompat.checkSelfPermission(getActivity(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                                                startActivity(i);
                                            } else {
                                                requestPermissions(new String[]{CALL_PHONE}, 1);
                                            }
                                            //startActivity(i);
                                           /* if (ContextCompat.checkSelfPermission(context,
                                                    Manifest.permission.CALL_PHONE)
                                                    != PackageManager.PERMISSION_GRANTED) {

                                                ActivityCompat.requestPermissions(context,
                                                        new String[]{Manifest.permission.CALL_PHONE},
                                                        MY_PERMISSIONS_REQUEST_CALL_PHONE);

                                                // MY_PERMISSIONS_REQUEST_CALL_PHONE is an
                                                // app-defined int constant. The callback method gets the
                                                // result of the request.
                                            } else {
                                                //You already have permission
                                                try {
                                                    startActivity(mIntent);
                                                } catch(SecurityException e) {
                                                    e.printStackTrace();
                                                }
                                            }*/

                                        }
                                    }
        );

       // HelpLineAdaptor adapter = new HelpLineAdaptor(HelpFragment.context , R.layout.my_custon_helpline, hp);

        //attaching adapter to the listview

        //HelpFragment.lv.setAdapter(adapter);

        return view;

    }


}