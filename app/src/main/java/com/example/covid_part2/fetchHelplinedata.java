package com.example.covid_part2;

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
import java.util.List;

import static com.example.covid_part2.HomeFragment.context;


public class fetchHelplinedata extends AsyncTask<Void, Void, List<HelpLine_class>>{

    String data="";
    List<HelpLine_class> hp=new ArrayList<>();
    @Override
    protected void onPostExecute(List<HelpLine_class> a) {
        super.onPostExecute(a);

          HelpFragment.hp=a;
        //  MainActivity.deaths.setText(this.deaths);
        //  MainActivity.recovered.setText(this.recovered);

        //adapter = new MyListAdapter(HomeFragment.context , R.layout.my_custom_list, MainActivity.cList);

        //attaching adapter to the listview

        HelpLineAdaptor adapter = new HelpLineAdaptor(context, R.layout.my_custon_helpline, a);

        //attaching adapter to the listview

        HelpFragment.lv.setAdapter(adapter);


    }

    @Override
    protected List<HelpLine_class> doInBackground(Void...v ) {

        try {
            URL url=new URL("http://ac41bf31.ngrok.io/api/helpline");
            HttpURLConnection h= (HttpURLConnection) url.openConnection();
            InputStream is=h.getInputStream();
            BufferedReader bf=new BufferedReader(new InputStreamReader(is));
            String line="";
            while(line!=null)
            {
                line=bf.readLine();
                data=data+line;
            }
            Log.i("$$$$$$$$$$$$$$$$$$$$",data);



            JSONObject json = new JSONObject(data);
            Log.i("65555555555555555",json.getString("helpline"));
            JSONArray jArray = new JSONArray(json.getString("helpline"));

            System.out.println("*****JARRAY*****" + jArray.length());


            //  Thread.sleep(1000);


            for(int i=0; i<jArray.length(); i++){
                JSONObject json_data = jArray.getJSONObject(i);

               // Integer.toString((Integer) json_data.get("state"));

                //JSONObject json_data_img = json_data.getJSONObject("countryInfo");
                // Log.i("dsf$$$$$$$$$$$$$$$$$$","fdgd"+json_data_img.get("flag"));
                hp.add(new HelpLine_class(json_data.get("state")+"",json_data.get("phone")+""));
               //  Log.i("log_tag","    "+ json_data.get("state") +
               //          ",   cases " + json_data.get("phone") );
                //         ",   deaths " + json_data.get("deaths") +
                //         ", recovered " + json_data.get("recovered"));// +
                // ",----" + json_data.getString("balance") +
                // ",----" + json_data.getString("credit") +
                // ",----" + json_data.getString("displayName")
                //);

            }
            //System.out.println("*****JARRAY*****" + jArray.length());

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
        return hp;


    }

}

