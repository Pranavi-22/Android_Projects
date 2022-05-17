package com.example.movie_client_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        String urls=getIntent().getStringExtra("urls");
       // String frameVideo = "<html><body>Video<br>" +
            //    "<iframe width=\"420\" height=\"315\" src=\"https://www.youtube.com/embad/eHp3MbsCbMg\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
       //https://www.youtube.com/watch?v=eHp3MbsCbMg
       // https://www.youtube.com/embed/47yJ2XCRLZs

        WebView displayYoutubeVideo = (WebView) findViewById(R.id.webView);
        displayYoutubeVideo.loadUrl(urls);
        displayYoutubeVideo.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        WebSettings webSettings = displayYoutubeVideo.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // not working
        //trying within the frame but not working
        // some links working not working
        // String frameVideo = "<html><body>Video<br>" +
        //    "<iframe width=\"420\" height=\"315\" src=\"https://www.youtube.com/embad/eHp3MbsCbMg\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
        //https://www.youtube.com/watch?v=eHp3MbsCbMg
        // https://www.youtube.com/embed/47yJ2XCRLZs
       // displayYoutubeVideo.loadData(frameVideo, "text/html", "utf-8");
    }
}