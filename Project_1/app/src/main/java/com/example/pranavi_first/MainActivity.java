package com.example.pranavi_first;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String phonenumber; // phone number to store
    boolean isValidNamePhone; // isValidNamePhone is a boolean variable to check we get correct number or not

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Main Activity which corresponds to activitymain xml file
        phonenumber = "";
        isValidNamePhone = false;
    }

    //Below Line executes when button 1 executes
    public void button1(View view) {
        Intent i = new Intent(this, MainActivity2.class); // explicit intent to class MainActivity.class
        startActivityForResult(i, 1); //start the activity

    }
//Button 2 will execute
    public void button2(View view) {
        if (isValidNamePhone) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_DIAL); // Action for what intent called for
            intent.setData(Uri.parse("tel:" + phonenumber)); // Data with intent respective action on intent
            startActivity(intent);

        }
        else if(phonenumber.length()==0)
        {
            // toast msg for printing static message when length=0
            Toast toast=Toast.makeText(this,"You have not provided a phonenumber yet"+phonenumber,Toast.LENGTH_LONG);
            toast.show();
        }
        else
        {
            // TOAST msg for invalid number
            Toast toast=Toast.makeText(this,"You provided an invalid phonenumber\n Given phonenumebr:"+phonenumber,Toast.LENGTH_LONG);
            toast.show();
        }


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 1
        if(requestCode==1)
        {
            phonenumber=data.getStringExtra("name");
            if(resultCode==RESULT_OK) {
                isValidNamePhone=true;
            }
            else {
                isValidNamePhone=false;
            }

        }
    }

    @Override
    //Orienation changefron potrait and landscape mode to handle
    //OnRestore and OnSaveInstanaceState
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        phonenumber=savedInstanceState.getString("name");
        isValidNamePhone=savedInstanceState.getBoolean("isValidPhoneNumber");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name",phonenumber);
        outState.putBoolean("isValidPhoneNumber",isValidNamePhone);
    }

}





