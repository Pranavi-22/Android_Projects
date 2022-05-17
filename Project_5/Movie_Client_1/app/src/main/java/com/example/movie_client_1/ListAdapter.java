package com.example.movie_client_1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListAdapter extends BaseAdapter {
    List<String> movieList;
    List<String> artistList;
    Context context;
    public ListAdapter(@NonNull Context context, @NonNull List<String> objects,@NonNull List<String> data) {

        movieList=objects;
        artistList=data;
        this.context=context;
    }
    private class ViewHolder {
        TextView txtTitle;
        TextView txtDesc;
        ViewHolder(View view){
            txtTitle= (TextView) view.findViewById(R.id.txtTitleSong);
            txtDesc=(TextView) view.findViewById(R.id.txtArtist);
        }


    }
    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int i) {
        return movieList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;




       // var userDto = items[position]
      //  viewHolder.txtName?.text = userDto.name
      //  viewHolder.txtComment?.text = userDto.comment

      //  return view as View
        View v = convertView;
        if(convertView == null) {
            LayoutInflater inf = LayoutInflater.from(context);
            v = inf.inflate(R.layout.item_listview, parent, false);
             viewHolder=new ViewHolder(v);
            v.setTag(viewHolder);
        }else{
    v = convertView;
            viewHolder = (ViewHolder) v.getTag();

    }
        String item =  movieList.get(position);

Log.d("item",item);
        //TextView txtTitle = (TextView) v.findViewById(R.id.txtTitleSong);
        //TextView txtDesc = (TextView) v.findViewById(R.id.txtArtist);

        if(item != null) {
            viewHolder. txtTitle.setText(item);
            viewHolder. txtDesc.setText(artistList.get(position));
        }

        return v;
    }
}
