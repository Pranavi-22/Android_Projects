package com.example.last_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button continuous_mode;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        continuous_mode = findViewById(R.id.continuous_mode);


        continuous_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open CONTINUOUS MODE
                Intent intent = new Intent(MainActivity.this, ContinuousActivity.class);
                startActivity(intent);
            }
        });
    }
}