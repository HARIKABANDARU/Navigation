package com.example.harika.assignment5;

import android.content.Context;
import android.support.annotation.BoolRes;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.Toast;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.ScaleInAnimator;

public class RecyclerViewFragment extends Fragment {
    MyRecyclerViewAdapter rviewAdatper;
    MovieData mData = new MovieData();;
    RecyclerView recList;
    public onIClickListener mListenr;
    Button ClearAll;
    Button SelectAll;
    Button Delete;
    Button Sort;
    Context context;
    HashMap<String,Boolean> item;
    public interface onIClickListener{
        public void onListItemSelected(int position,HashMap<String,?> movie);
    }

    @Override
    public  void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle SavedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recycler_view,container,false);
        recList = (RecyclerView) rootView.findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(mLayoutManager);
        rviewAdatper = new MyRecyclerViewAdapter(mData.getMoviesList(),false);
        recList.setAdapter(rviewAdatper);
        mListenr = (onIClickListener)getContext();
        setAdapterItemClickListener(rviewAdatper);
        itemAnimation();
        adapterAnimattion();
        /*ClearAll = (Button)rootView.findViewById(R.id.clear);
        ClearAll.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v){
                        for(int i = 0;i<rviewAdatper.getItemCount();i++) {
                            item = (HashMap<String, Boolean>) mData.getItem(i);
                            item.put("selection", false);
                        }

                    }
                });
        SelectAll = (Button)rootView.findViewById(R.id.select);
        SelectAll.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < rviewAdatper.getItemCount(); i++) {
                    Log.d("inside select all ", String.valueOf(i));
                    item = (HashMap<String, Boolean>) mData.getItem(i);
                    item.put("selection", true);
                }
            }
        });
        Delete = (Button)rootView.findViewById(R.id.delete);
        Delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Log.d("LIST_SELECTED",mData.moviesList.toString());
                for(int i = rviewAdatper.getItemCount()-1;i>=0;i--)
                {
                    item = (HashMap<String,Boolean>)mData.getItem(i);
                    boolean b = item.get("selection");
                    Log.d("boolvalue",String.valueOf(b));
                    if(b == true)
                    {
                        mData.moviesList.remove(item);
                        rviewAdatper.notifyItemRemoved(i);
                    }
                }
            }
        });
        Sort = (Button)rootView.findViewById(R.id.sort);
       Sort.setOnClickListener(new Button.OnClickListener()
        {
            @Override
           public void onClick(View v){
                rviewAdatper.setOnSortClicked(true);

                List<Map<String,?>> mDataset = mData.getMoviesList();
                Log.d("LIST",mDataset.toString());

                Collections.sort(mDataset, new Comparator<Map<String, ?>>() {
                    @Override
                    public int compare(Map<String, ?> item1, Map<String, ?> item2) {
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date date1 = new Date(0),date2 = new Date(0);
                        try {
                            Log.d("LIST_ITEM",item1.get("release").toString());
                            date1 = new Date(dateFormat.parse(item1.get("release").toString()).getTime());
                            date2 = new Date(dateFormat.parse(item2.get("release").toString()).getTime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        return date1.compareTo(date2);
                    }
                });

                Log.d("LIST_SORT",mDataset.toString());
                rviewAdatper = new MyRecyclerViewAdapter(mDataset,true);
                setAdapterItemClickListener(rviewAdatper);
                recList.setAdapter(rviewAdatper);
                //rviewAdatper.setmDataset(mDataset);
                //rviewAdatper.notifyDataSetChanged();

            }
        });*/
        return rootView;
    }

  /*  public void addItem(int position,HashMap<String,?> movie)
    {
        List<Map<String,?>> movieList = (List<Map<String,?>>)mData;
        movieList.add(position,movie);
        Log.d("value",String.valueOf(movieList.size()));
    }*/
  @Override
  public void onCreateOptionsMenu(Menu menu,MenuInflater inflater)
  {
      SearchView search = null;
      if(menu.findItem(R.id.search)==null)
          inflater.inflate(R.menu.recycleviewoptionmenu,menu);
      MenuItem menuItem = menu.findItem(R.id.search);
      if(menuItem!=null)
          search = (SearchView)menuItem.getActionView();
      if(search!=null){
          search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
              @Override
              public boolean onQueryTextSubmit(String query) {
                  int position = mData.findFirst(query);
                  if (position >= 0 && position< mData.getSize())
                      recList.scrollToPosition(position);
                  return true;
              }
              @Override
              public boolean onQueryTextChange(String query){

                  return true;
              }
          });
      }
      super.onCreateOptionsMenu(menu,inflater);
  }
 /* @Override
  public  boolean onOptionsItemSelected(MenuItem item){
      int id = item.getItemId();
      switch(id){
          case R.id.search:
              Toast.makeText(getActivity(),"inside fragment action bar",Toast.LENGTH_SHORT).show();
              return true;
      }
   return true;
  }*/
    private void defaultAnimations(){
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(5000);
        animator.setRemoveDuration(100);
        recList.setItemAnimator(animator);
    }
    private void itemAnimation(){
        ScaleInAnimator animator = new ScaleInAnimator();
        animator.setInterpolator(new OvershootInterpolator());
        animator.setAddDuration(300);
        animator.setRemoveDuration(300);
        recList.setItemAnimator(animator);
    }

    private void adapterAnimattion(){
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(rviewAdatper);
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(rviewAdatper);
        recList.setAdapter(scaleAdapter);
    }

    private void setAdapterItemClickListener(MyRecyclerViewAdapter adapter)
    {
        adapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener()
        {

            @Override
            public void onItemClick(View v,int position)
            {
                HashMap<String,?> movie = mData.getItem(position);
                mListenr.onListItemSelected(position,movie);
            }
            @Override
            public void onItemLongClick(View v,int position)
            {
               getActivity().startActionMode((android.view.ActionMode.Callback) new ActionBarCallBack(position));
            }
            @Override
            public void onOverflowMenuClick(View v,final int position){
                final List<Map<String,?>> movieList = mData.getMoviesList();
                final PopupMenu popup = new PopupMenu(getActivity(),v);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                    @Override
                    public boolean onMenuItemClick(MenuItem item){
                        switch (item.getItemId())
                        {
                            case R.id.item_delete:
                                movieList.remove(position);
                                rviewAdatper.notifyItemRemoved(position);
                                return true;
                            case R.id.item_duplicate:
                                HashMap<String,?> movie = (HashMap<String,?>)mData.getItem(position).clone();
                                movieList.add(position+1,movie);
                                rviewAdatper.notifyItemInserted(position+1);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.contextual_pop_menu,popup.getMenu());
                popup.show();
            }
        });
    }
    public class ActionBarCallBack implements ActionMode.Callback {
        int position;
        public ActionBarCallBack(int position){this.position = position;}
        MovieData mData = new MovieData();


        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.contextual_pop_menu,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            HashMap hm = (HashMap)mData.getItem(position);
            mode.setTitle((String)hm.get("title"));
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode,MenuItem item){
            int id = item.getItemId();
            List<Map<String,?>> movieList = mData.getMoviesList();
            switch (id){
                case R.id.item_delete:
                    movieList.remove(position);
                    rviewAdatper.notifyItemRemoved(position);
                    mode.finish();
                case R.id.item_duplicate:
                    HashMap<String,?> movie = (HashMap<String,?>)mData.getItem(position).clone();
                    movieList.add(position,movie);
                    rviewAdatper.notifyItemInserted(position+1);
                    mode.finish();;
                    break;
                default:
                    break;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    }
}


