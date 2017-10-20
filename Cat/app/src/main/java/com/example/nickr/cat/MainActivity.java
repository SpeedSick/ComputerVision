package com.example.nickr.cat;

import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONObject;

import org.json.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.*;


import fr.arnaudguyon.xmltojsonlib.XmlToJson;


public class MainActivity extends AppCompatActivity implements RecyclerAdapter.RecyclerAdapterListener {

    private ArrayList<Image> list;
    private int position, orientation;

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private Button buttonPrev;
    private Button buttonNext;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        fragmentManager = getSupportFragmentManager();
        list = new ArrayList<Image>();





        if(savedInstanceState !=  null && savedInstanceState.containsKey("position"))
            position = savedInstanceState.getInt("position");
        else
            position = 0;

        orientation = getResources().getConfiguration().orientation;
        bindViews();
        try {

            JSONObject jsonObject = new JSONObject(getJson().toString());
            jsonObject = jsonObject.getJSONObject("response");
            jsonObject = jsonObject.getJSONObject("data");
            jsonObject = jsonObject.getJSONObject("images");
            JSONArray jsonArray = jsonObject.getJSONArray("image");
            for(int i = 0; i < jsonArray.length(); ++i)
            {
                JSONObject cur = jsonArray.getJSONObject(i);
                list.add(new Image(cur.getString("url"), cur.getString("id"), cur.getString("source_url")));
            }
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("position", String.valueOf(position));

        if(orientation == Configuration.ORIENTATION_LANDSCAPE && list.size() > 0)
            itemClicked(position);


    }

    private XmlToJson getJson() {
        InputStream inputStream = getResources().openRawResource(R.raw.xmlfile);
        return new XmlToJson.Builder(inputStream, null).build();
    }

    private void bindViews() {
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            buttonNext = (Button) findViewById(R.id.button_next);
            buttonPrev = (Button) findViewById(R.id.button_prev);
            buttonNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nextFragment();
                }
            });
            buttonPrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    previousFragment();
                }
            });
        }
        else if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            recyclerView = (RecyclerView) findViewById(R.id.recycler_fragment_landscape_left);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new RecyclerAdapter(list, this);
            recyclerView.setAdapter(adapter);
        }
    }

    private void previousFragment() {
        position = (position - 1 + list.size()) % list.size();
        CatFragment fragment = new CatFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("image", list.get(position));
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.container_fragment, fragment, null).commit();
    }

    private void nextFragment() {
        position = (position + 1) % list.size();
        CatFragment fragment = new CatFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("image", list.get(position));
        Log.d("FUCK1", list.get(position).getUrl());
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.container_fragment, fragment, null).commit();
    }
    public void itemClicked(int position) {
        CatFragment fragment = new CatFragment();
        Bundle args = new Bundle();
        args.putParcelable("image", list.get(position));
        fragment.setArguments(args);
        fragmentManager.beginTransaction().replace(R.id.container_fragment_landscape_right, fragment, null).commit();
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", position);
    }

}
