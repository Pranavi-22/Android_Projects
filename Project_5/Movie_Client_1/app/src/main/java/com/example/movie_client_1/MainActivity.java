package com.example.movie_client_1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.common.MovieCentralInterface;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    protected static final String TAG = "MainActivity";
    /* Boolean to store the status of the service connection*/
    private boolean bound = false;
    /* text view to store the service status*/
    TextView serviceStatus;
    /* text view to store the titles*/
    TextView headingTitle;
    TextView headingTitle2;
    TextView movieInfoText;
    /* edit text */
    EditText movieIdText;
    /* buttons to store bind and unbind*/
    Button bindService;
    Button unBindService;
    /*movies and all movies */
    Button showAllmovies;
    Button showAllmoviesListView;
    RadioGroup movieGroup;
    /*webview to play the url */
    WebView webView;
    /* Array list to store movies,directors and urls*/
    public  ArrayList<String> movieList;
    public  ArrayList<String> artistList;
    public ArrayList<String> moviesUrls;
    /*aidl reference */
    private MovieCentralInterface MovieCentralService;
    /*information of bundle  */
    Bundle info;
    /*information of oncreate */
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            /*getting information about the service*/
            if(MovieCentralService!=null)
                /*get the information */
             info = MovieCentralService.getMovieInformation();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        /*service status */
        serviceStatus = findViewById(R.id.textView);
        /*bind status */
        bindService = findViewById(R.id.button);
        /*unbind status */
        unBindService = findViewById(R.id.button2);
        /*unbind status is set to false*/
        unBindService.setEnabled(bound);
        /*smovies button */
        showAllmovies = findViewById(R.id.button3);
        /*webview status */
        webView = findViewById(R.id.web);
        /*show all movies is the status and its bound is false */
        showAllmovies.setEnabled(bound);
        showAllmoviesListView=findViewById(R.id.button4);
        /*invisible the buttons */
        showAllmoviesListView.setVisibility(View.INVISIBLE);
        serviceStatus.setText("Service Not Binded");
        movieGroup = findViewById(R.id.radioGroup2);
        /*making the bound false */
        movieGroup.setEnabled(bound);
        headingTitle = findViewById(R.id.headingtitle);
        /*making all the buttonds invisible */
        headingTitle.setVisibility(View.INVISIBLE);
        headingTitle2 = findViewById(R.id.headingtitle2);
        headingTitle2.setVisibility(View.INVISIBLE);
        movieInfoText = findViewById(R.id.movieInfo);
        movieInfoText.setVisibility(View.INVISIBLE);
        movieIdText = findViewById(R.id.movieIdText);
        movieIdText.setVisibility(View.INVISIBLE);
        /*click on the bind service status */
        bindService.setOnClickListener(v -> {
            try {
                /*bindToService method is called*/
                bindToService();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        /*click on the unbind service status */
        unBindService.setOnClickListener(v -> {
            try {
                /*unbindToService method is called */
                unBindFromService();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
        //all movies detail view of all the movies ,director, titles are taken in this
        showAllmoviesListView.setOnClickListener(v->{
            if (info==null){
                try {
                    info = MovieCentralService.getMovieInformation();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            //launch another activity listviewactivity class which displays the list
            Intent intent=new Intent(this,ListViewActivity.class);
            //intent to send the information about the titles,names,urls of the movie
            intent.putStringArrayListExtra("titles",info.getStringArrayList("titles"));
            intent.putStringArrayListExtra("names",info.getStringArrayList("names"));
            intent.putStringArrayListExtra("urls",info.getStringArrayList("urls"));
            startActivity(intent);
        });
        // while pressing the id the paticular movie information will be executed
        movieIdText.setOnEditorActionListener((v, actionId, event) ->
        {
            int id = Integer.parseInt(movieIdText.getText().toString());
            if (id <= 5) {
                try {
                    Bundle movieInfo = MovieCentralService.getMoviewithId(id);
                    String result = "Movie Name: " + movieInfo.getString("title") + "\n \n Director Name: " + movieInfo.getString("name");
                    movieInfoText.setText(result);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, "Please Enter a Valid movie ID between 0 and 5 ", Toast.LENGTH_SHORT).show();
            }
            return true;
        });
    }
//unbind method will be called then thingsToDoOnServiceUnbind
    private void unBindFromService() throws RemoteException {
        if (bound) {
            unbindService(mConnection);
            serviceStatus.setText("Service Not Binded");
            bound = false;
            thingsToDoOnServiceUnbind();
        }
    }
    //thingsToDoOnServiceUnbind which will unblur everything
    private void thingsToDoOnServiceUnbind() throws RemoteException {
        if (!bound) {
            serviceStatus.setText("Service Not Binded");
            unBindService.setEnabled(bound);
            showAllmovies.setEnabled(bound);
            headingTitle.setVisibility(View.INVISIBLE);
            movieIdText.setVisibility(View.INVISIBLE);
            movieInfoText.setVisibility(View.INVISIBLE);
            headingTitle2.setVisibility(View.INVISIBLE);
            showAllmoviesListView.setVisibility(View.INVISIBLE);
            movieInfoText.setText("");
            movieIdText.setText("");
          // movieGroup.setVisibility(View.GONE);
            Intent i = new Intent(MovieCentralInterface.class.getName());
            ResolveInfo info = getPackageManager().resolveService(i, 0);
            i.setComponent(new ComponentName(info.serviceInfo.packageName, info.serviceInfo.name));
           // i.setComponent(new ComponentName("com.example.movie_server_1", "com.example.movie_server_1.Movie_Server"));
            movieGroup.removeAllViews();
            movieList = new ArrayList<String>();
            artistList = new ArrayList<String>();
            moviesUrls = new ArrayList<String>();
            stopService(i);
            //movieGroup.removeOnLayoutChangeListener();
            /*int count = movieGroup.getChildCount();
            if(count>0) {
                for (int i1=count-1;i1>=0;i1--) {
                    View o = movieGroup.getChildAt(i1);
                    if (o instanceof RadioButton) {
                        movieGroup.removeViewAt(i1);
                    }
                }
            }*/
           // getAllmovieInfo(0);
        }

    }
    //unbind method will be called then thingsToDoOnServiceUnbind
    private void getAllMovieInfo(int i) throws RemoteException {

            if (info == null) {
                info = MovieCentralService.getMovieInformation();
            }
            movieList = info.getStringArrayList("titles");
            artistList = info.getStringArrayList("names");
        moviesUrls = info.getStringArrayList("urls");
            if (i == 0) {
                addRadioButtons(movieList);
            }
        }

    //add radio button and display the information
    public void addRadioButtons(ArrayList<String> titlesList) throws RemoteException {
        for (int i = 0; i < titlesList.size(); i++) {
            RadioButton radio = new RadioButton(this);
            Bundle movieTitle = MovieCentralService.getMoviewithId(i);
            radio.setText(movieTitle.getString("title"));
            radio.setId(i);
            radio.setText(titlesList.get(i));
            movieGroup.addView(radio);
        }
        //add radio button and display the information
        movieGroup.setOnCheckedChangeListener((radioGroup, id) -> {
            int k = 0;

            for (int i = 0; i < radioGroup.getChildCount(); i++) {

                if (radioGroup.getChildAt(i).getId() == id) {
                    k = i;
                    Log.i(TAG, "onCheckedChanged: id" + id);
                    Log.i(TAG, "onCheckedChanged: hwllo" + k);
                    if (id <= titlesList.size()) {

                        Bundle movieInfo = null;
                        try {
                            movieInfo = MovieCentralService.getMoviewithId(id);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }

                        String result = "Movie Name: " + movieInfo.getString("title") + "\n \n Director Name: " + movieInfo.getString("name");
                        Log.d("result",result);
                        movieInfoText.setText(result);
                        movieIdText.setText(String.valueOf(i));
                    } else {
                        movieInfoText.setText("");
                        movieIdText.setText("");
                    }
                    break;
                }
            }
            try {
                playMovies(k);
            } catch (RemoteException | IOException e) {
                e.printStackTrace();
            }

        });

    }
    //play movies in the radio button to the particular radio button
    private void playMovies(int index) throws RemoteException, IOException{

            if (index < moviesUrls.size()) {

                String url = MovieCentralService.getMovieURL(index);
                if (index == 0) {
                    webView.loadUrl(url);
                } else if (index == 1) {
                    webView.loadUrl(url);
                } else if (index == 2) {
                    webView.loadUrl(url);
                } else if (index == 3) {
                    webView.loadUrl(url);

                } else if (index == 4) {
                    webView.loadUrl(url);

                } else if (index == 5) {
                    webView.loadUrl(url);

                }
                else if (index == 6) {
                    webView.loadUrl(url);

                }
                else {
                    webView.loadUrl(url);

                }


            }
        }



    @RequiresApi(api = Build.VERSION_CODES.O)
    private void bindToService() throws RemoteException {
        if (!bound) {

            boolean b = false;
            Intent i = new Intent(MovieCentralInterface.class.getName());

            // UB:  Stoooopid Android API-21 no longer supports implicit intents
            // to bind to a service #@%^!@..&**!@
            // Must make intent explicit or lower target API level to 20.
            ResolveInfo info = getPackageManager().resolveService(i, 0);
            i.setComponent(new ComponentName(info.serviceInfo.packageName, info.serviceInfo.name));
           // i.setComponent(new ComponentName("com.example.movie_server_1", "com.example.movie_server_1.Movie_Server"));

            b = bindService(i, this.mConnection, Context.BIND_AUTO_CREATE);
            if (b) {
                Log.i(TAG, "Ugo says bindService() succeeded!");
                bindService(i, this.mConnection, Context.BIND_AUTO_CREATE);
            } else {
                Log.i(TAG, "Ugo says bindService() failed!");
            }

        }
    }

    private final ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder iservice) {
            Log.i(TAG, "onServiceConnected: The service is connected");
            MovieCentralService = MovieCentralInterface.Stub.asInterface(iservice);
            Log.i(TAG, "service");

            bound = true;
            try {
                thingsToDoOnServiceBind();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        //methos calls when server gets crashes

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(TAG, "onServiceDisconnected: DIsconnected");
            MovieCentralService = null;
            bound = false;
            try {
                thingsToDoOnServiceUnbind();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }
    };
    //thingstoOn serviceBind and makes everything visible
    public void thingsToDoOnServiceBind() throws RemoteException {
        if (bound) {
            Log.i(TAG, "visible");
            serviceStatus.setText("Service Binded");
            unBindService.setEnabled(bound);
            showAllmovies.setEnabled(bound);
            headingTitle.setVisibility(View.VISIBLE);
            movieIdText.setVisibility(View.VISIBLE);
            movieInfoText.setVisibility(View.VISIBLE);
            headingTitle2.setVisibility(View.VISIBLE);
            movieGroup.setVisibility(View.VISIBLE);
            movieGroup= findViewById(R.id.radioGroup2);
            showAllmoviesListView.setVisibility(View.VISIBLE);
            getAllMovieInfo(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}