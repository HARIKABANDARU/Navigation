package com.example.harika.assignment5;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import java.util.HashMap;

public class RecycleViewActivity extends AppCompatActivity implements RecyclerViewFragment.onIClickListener {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        Toolbar mtoolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setLogo(R.drawable.circular);
        RecyclerViewFragment rFragment = new RecyclerViewFragment();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, rFragment).addToBackStack(null).commit();
        }

        // MovieDetailFragment mfragment = new MovieDetailFragment();
        //getSupportFragmentManager().beginTransaction().add(R.id.container,mfragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflate = getMenuInflater();
        inflate.inflate(R.menu.activity_option_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id)
        {

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onResume() {
        super.onResume();
    }

        @Override
    public void onListItemSelected(int position, HashMap<String, ?> movie) {

        getSupportFragmentManager().beginTransaction().replace(R.id.container,MovieDetailFragment.newInstance(movie)).addToBackStack(null).commit();
    }
}
