package com.example.covid_part2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Mom on 03-04-2020.
 */

public class MyListAdapter extends ArrayAdapter<Country_class> {

    //the list values in the List of type hero

    List<Country_class> arraylist=new ArrayList<>();

    List<Country_class> countryList;

    //activity context
    Context context;

    //the layout resource file for the list items
    int resource;

    //constructor initializing the values
    public MyListAdapter(Context context, int resource, List<Country_class> countryList) {
        super(context, resource, countryList);
        this.context = context;
        this.resource = resource;
        this.countryList = countryList;

       // this.arraylist = new ArrayList<>();
       // this.arraylist.addAll(countryList);
    }

    //this will return the ListView Item as a View
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //we need to get the view of the xml for our list item
        //And for this we need a layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //getting the view
        View view = layoutInflater.inflate(resource, null, false);

        //getting the view elements of the list from the view
        TextView name=view.findViewById(R.id.cname);
        TextView cases = view.findViewById(R.id.ccases);
        TextView deaths = view.findViewById(R.id.cdeaths);
        TextView recovered = view.findViewById(R.id.crecovered);
        ImageView flag=view.findViewById(R.id.flag);


        //getting the hero of the specified position
        try {


            Country_class country = countryList.get(position);

            //adding values to the list item
            //flag.setImageDrawable(context.getResources().getDrawable(hero.getImage()));
            //Picasso.get().load(country.getImg()).into(flag);
            //Log.i("cases###########3", String.valueOf(country.getTotalcases()));
            cases.setText(String.valueOf(country.getTotalcases()));
            deaths.setText(String.valueOf(country.getDeaths()));
            recovered.setText(String.valueOf(country.getRecovered()));
            name.setText(String.valueOf(country.getCountry()));
            Picasso.with(context).load(country.getImg()).placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round)
                    .into(flag, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {

                        }
                    });

        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.print(e.getMessage());
        }


        //textViewTeam.setText(hero.getTeam());

        //adding a click listener to the button to remove item from the list

        //finally returning the view
        return view;
    }

    //this method will remove the item from the list
    /*
    private void removeHero(final int position) {
        //Creating an alert dialog to confirm the deletion
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Are you sure you want to delete this?");

        //if the response is positive in the alert
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //removing the item
                countryList.remove(position);

                //reloading the list
                notifyDataSetChanged();
            }
        });

        //if response is negative nothing is being done
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        //creating and displaying the alert dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    */




    public List<Country_class> filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        arraylist.clear();
        if (charText.length() == 0) {
            arraylist.addAll(countryList);
        }
        else
        {
            for (Country_class wp : countryList) {
                if (wp.getCountry().toLowerCase(Locale.getDefault()).contains(charText)) {
                    arraylist.add(wp);
                }
            }
        }
        return arraylist;
       // MainActivity.adapter=new MyListAdapter(context,resource,arraylist);

    }

}
