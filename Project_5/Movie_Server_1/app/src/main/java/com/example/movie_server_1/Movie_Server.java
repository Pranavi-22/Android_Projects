package com.example.movie_server_1;

import android.app.Service;
import android.content.Intent;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;



import androidx.annotation.Nullable;


import com.example.common.MovieCentralInterface;

import static com.example.movie_server_1.information.movieTitles;
import static com.example.movie_server_1.information.directorNames;
import static com.example.movie_server_1.information.movieURL;


import java.util.ArrayList;
import java.util.List;

public class Movie_Server extends Service {

    public Movie_Server()
    {

    }

    public static ArrayList<String> listToArrayList(List<String> myList) {
        ArrayList<String> arraylist = new ArrayList<String>();
        for (Object object : myList) {
            arraylist.add((String) object);
        }
        return arraylist;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    private final MovieCentralInterface.Stub mBinder= new MovieCentralInterface.Stub() {
        @Override
        public Bundle getMovieInformation() throws RemoteException {

            Bundle result = new Bundle();
            synchronized (result) {
                result.putStringArrayList("titles", listToArrayList(movieTitles));
                result.putStringArrayList("names", listToArrayList(directorNames));
                result.putStringArrayList("urls", listToArrayList(movieURL));
            }
            return result;



        }

        @Override
        public Bundle getMoviewithId(int id) throws RemoteException {

            Bundle result = new Bundle();
            synchronized (result) {
                result.putString("title", listToArrayList(movieTitles).get(id));
                result.putString("name", listToArrayList(directorNames).get(id));
                result.putString("url", listToArrayList(movieURL).get(id));
            }
            return result;
        }


        @Override
        public String getMovieURL(int id) throws RemoteException {
            return listToArrayList(movieURL).get(id);
        }


    };



    @Override
    public void onDestroy() {
        super.onDestroy();
        startService(new Intent(Movie_Server.this,Movie_Server.class));
    }
}
