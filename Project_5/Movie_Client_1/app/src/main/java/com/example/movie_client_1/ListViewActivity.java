package com.example.movie_client_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {
    ListAdapter adapter;
    ListView listView;
    public  ArrayList<String> movieList;
    public  ArrayList<String> artistList;
     ArrayList<String> songsUrls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        listView=findViewById(R.id.listView);
        songsUrls=getIntent().getStringArrayListExtra("urls");
        artistList=getIntent().getStringArrayListExtra("names");
        movieList=getIntent().getStringArrayListExtra("titles");
        Log.d("tittles",movieList.toString());
        adapter = new ListAdapter(this, movieList,artistList);
      //  ArrayAdapter<String> adapter = new ArrayAdapter<>(ListViewActivity.this, android.R.layout.simple_list_item_1, movieList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
             Log.v("position",movieList.get(i));
             Intent intent=new Intent(ListViewActivity.this,PlayerActivity.class);
             intent.putExtra("urls",songsUrls.get(i));
             startActivity(intent);

            }
        });

    }
}