package com.example.covid_part2;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Mom on 03-04-2020.
 */

public  class fetchdata extends AsyncTask<Void, Void, Void> {

    String data="";
    String cases,deaths,recovered;



    @Override
    protected void onPostExecute(Void v) {
        super.onPostExecute(v);
        HomeFragment.cases1.setText(this.cases);
        HomeFragment.deaths.setText(this.deaths);
        HomeFragment.recovered.setText(this.recovered);
       // MainActivity.deaths=this.deaths;
       // MainActivity.recovered=this.recovered;
        System.out.print("544444444444444444444444444"+MainActivity.cases);

    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            URL url=new URL("https://disease.sh/v3/covid-19/all");
            HttpURLConnection h= (HttpURLConnection) url.openConnection();
            InputStream is=h.getInputStream();
            BufferedReader  bf=new BufferedReader(new InputStreamReader(is));
            String line="";
            while(line!=null)
            {
                line=bf.readLine();
                data=data+line;
            }
            //JSONArray ja=new JSONArray(data);
            JSONObject jo=new JSONObject(data);
            cases= jo.get("cases").toString();
            deaths= jo.get("deaths").toString();
            recovered=  jo.get("recovered").toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
