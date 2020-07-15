package com.example.covid_part2;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

    public class HelpLineAdaptor extends ArrayAdapter<HelpLine_class> {

        //the list values in the List of type hero

        List<HelpLine_class> arraylist=new ArrayList<>();

        List<HelpLine_class> countryList;

        //activity context
        Context context;

        //the layout resource file for the list items
        int resource;

        //constructor initializing the values
        public HelpLineAdaptor(Context context, int resource, List<HelpLine_class> countryList) {
            super(context, resource, countryList);
            this.context = context;
            this.resource = resource;
            this.countryList = countryList;

        }

        //this will return the ListView Item as a View
        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater layoutInflater = LayoutInflater.from(context);

            //getting the view
            View view = layoutInflater.inflate(resource, null, false);

            //getting the view elements of the list from the view
            TextView name=view.findViewById(R.id.stateName);
            TextView number = view.findViewById(R.id.phone_no);



            //getting the hero of the specified position
            HelpLine_class country = countryList.get(position);

            name.setText(String.valueOf(country.getState_name()));
            number.setText(String.valueOf(country.getNumber()));

            return view;
        }

    }


