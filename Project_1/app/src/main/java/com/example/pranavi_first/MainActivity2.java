package com.example.pranavi_first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    //function to check whether the phone number is correct or not
    public static boolean isValidPhoneNumber(String phonenumber)
    {
        return !phonenumber.equals("")
                && phonenumber != null
                && phonenumber.matches("\\(?\\d{3}\\)?[-\\s]?\\d{3}?[-\\s]?\\d{3}");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2); //corresponds to the activitymain2.xml file
        Intent i = new Intent(); // Implicit Intent
        EditText editText = (EditText) findViewById(R.id.name); //EditText and we get the the "name" from activitymain2
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                // string for the phonr number
                String phonenumber = editText.getText().toString().trim();

                // it will check ValidPhoneNumber
                if(isValidPhoneNumber(phonenumber)) {
                    i.putExtra("name", phonenumber);
                    setResult(RESULT_OK, i);
                }
                else{
                    i.putExtra("name", phonenumber);
                    setResult(RESULT_CANCELED, i);
                }

                finish();
                return true;
            }
        });
    }

    // backpress
    @Override
    public void onBackPressed() {

        Intent i = new Intent();
        i.putExtra("name","");
        setResult(RESULT_CANCELED,i);
        super.onBackPressed();
    }
}