package com.example.covid_part2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<Country_class> cList=new ArrayList<>();

    public static String cases="000",deaths,recovered;
    public  static MyListAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomnav=findViewById(R.id.bnav);
        bottomnav.setOnNavigationItemSelectedListener(navListner);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont,new HomeFragment()).commit();

    }
    BottomNavigationView.OnNavigationItemSelectedListener navListner=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selected=null;


                    switch(item.getItemId())
                    {
                        case R.id.home:
                            selected=new HomeFragment();
                            break;
                        case R.id.fav:
                            selected=new FavFragment();
                            break;

                            /*
                        case R.id.helpline:
                            selected=new HelpFragment();
                            break;*/
                        //case R.id.Symptoms:
                        //    selected=new SymptomsFragment();
                        //    break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont,selected).commit();

                    return true;
                }
            };
}
