package com.example.covid_part2;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.example.covid_part2.MainActivity.adapter;


/**
 * Created by Mom on 03-04-2020.
 */

public class fetchCountrydata extends AsyncTask<Void, Void, List<Country_class>> {

    String data="";
    String cases,deaths,recovered;







    @Override
    protected void onPostExecute(List<Country_class> a) {
        super.onPostExecute(a);
//        progressDialog.dismiss();
        Log.i("ds4$$$$$$$$$$$$$$$$$$", String.valueOf(a.size()));


       // Collections.sort(a);

        MainActivity.cList=a;
      //  MainActivity.deaths.setText(this.deaths);
      //  MainActivity.recovered.setText(this.recovered);

        adapter = new MyListAdapter(HomeFragment.context , R.layout.my_custom_list, MainActivity.cList);

        //attaching adapter to the listview

        HomeFragment.listView.setAdapter(adapter);


    }

    @Override
    protected List<Country_class> doInBackground(Void...v ) {

        MainActivity.cList.clear();

        try {
            URL url=new URL("https://disease.sh/v3/covid-19/countries");
            HttpURLConnection h= (HttpURLConnection) url.openConnection();
            InputStream is=h.getInputStream();
            BufferedReader bf=new BufferedReader(new InputStreamReader(is));
            String line="";
            while(line!=null)
            {
                line=bf.readLine();
                data=data+line;
            }
            //Log.i("$$$$$$$$$$$$$$$$$$$$",data);



            //JSONObject json = new JSONObject(data);
            JSONArray jArray = new JSONArray(data);

            System.out.println("*****JARRAY*****" + jArray.length());


          //  Thread.sleep(1000);
            for(int i=0; i<jArray.length(); i++){
                JSONObject json_data = jArray.getJSONObject(i);

                Integer.toString((Integer) json_data.get("cases"));

                JSONObject json_data_img = json_data.getJSONObject("countryInfo");
               // Log.i("dsf$$$$$$$$$$$$$$$$$$","fdgd"+json_data_img.get("flag"));
                MainActivity.cList.add(new Country_class(json_data.get("cases")+"",json_data.get("deaths")+"",json_data.get("recovered")+"",json_data.get("country")+"",""+json_data_img.get("flag")));
               // Log.i("log_tag","    "+ json_data.get("country") +
               //         ",   cases " + json_data.get("cases") +
               //         ",   deaths " + json_data.get("deaths") +
               //         ", recovered " + json_data.get("recovered"));// +
                       // ",----" + json_data.getString("balance") +
                       // ",----" + json_data.getString("credit") +
                       // ",----" + json_data.getString("displayName")
                //);







            }
            System.out.println("*****JARRAY*****" + jArray.length());

            /*
            JSONArray ja=new JSONArray(data);
            for(int i=0;i<ja.length();i++)
            {
                //JSONObject jo=new JSONObject((JSONTokener) ja.get(i));
                Log.i("####4444444444####", ja.get(i));
            }

            /*
            System.out.println("resultList.toString() " + data);
            org.json.JSONObject obj = new JSONObject(data);
            org.json.JSONArray jsonArray = obj.getJSONArray("country");

            for(int i=0;i<jsonArray.length();i++){
                System.out.println("array is " + jsonArray.get(i));

            }
            //JSONObject jo=new JSONObject(data);
            //cases= Integer.toString((Integer) jo.get("cases"));
            //deaths= Integer.toString((Integer)jo.get("deaths"));
            //recovered= Integer.toString((Integer) jo.get("recovered"));

            */

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return MainActivity.cList;
    }

}

