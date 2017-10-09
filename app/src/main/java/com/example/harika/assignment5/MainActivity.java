package com.example.harika.assignment5;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.facebook.drawee.backends.pipeline.Fresco;

import static com.example.harika.assignment5.R.id.toolbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private  NavigationView navigationView;;
    private DrawerLayout drawerLayout;
@Override
public void onCreate(Bundle savedInstanceState){



        Fresco.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
       navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
       /*drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
    ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string./,"Close Drawer");*/



        if(savedInstanceState==null){
        Intent rActivivty=new Intent(MainActivity.this,RecycleViewActivity.class);
        startActivity(rActivivty);
        }
        // private FragmentTransaction fragmentTransaction;
        // public final static String MAIN_FRAGMENT_TAG = "MAIN_FRAGMENT";

      /*  @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);



            setContentView(R.layout.activity_main);

            if(savedInstanceState == null) {
                mainFragment = new MainFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.containermain, mainFragment)
                        .commit();
            }    }*/

        }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
