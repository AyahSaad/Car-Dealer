package edu.birzeit.advancecardealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import edu.birzeit.advancecardealer.user.ContactUs;
import edu.birzeit.advancecardealer.user.HomePage;

public class MainActivity extends AppCompatActivity {
    // Checking SharedPreferences on app launch
    SharedPrefManager preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences= SharedPrefManager.getInstance(this);
        boolean rememberMe = preferences.readBoolean("rememberM",false);
        String type = preferences.readString("type","User");
        File dataBase = this.getDatabasePath("CarsDatabase");
        if (dataBase.exists()){
            this.deleteDatabase("CarsDatabase");
        }
        DataBaseHelper dataBaseMain = new DataBaseHelper(MainActivity.this, "CarsDatabase", null, 1);
        User user1 = new User("hala", "ziq", "hala@ziq.com", "h12345!", "female", "Palestine", "Ramallah", "Admin", "00970592687071");
        User user2= new User("ayah", "saad", "ayah@saad.com", "a12345!", "female", "Palestine", "Ramallah", "User", "00970592687071");


        dataBaseMain.insertUser(user1);
        dataBaseMain.insertUser(user2);

        if (rememberMe == true && type.equals("Admin") ){
            //TODO Add Admin Home Page Intent
            Intent home = new Intent(MainActivity.this, ContactUs.class);
            startActivity(home);
        }else if (rememberMe == true && type.equals("User") ){
            Intent home = new Intent(MainActivity.this, HomePage.class);
            startActivity(home);

        }

        Button getStarted = findViewById(R.id.getStarted);
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getStarted.setBackgroundColor(Color.BLACK);
                Intent regIntent = new Intent(MainActivity.this,RegSection.class);
                startActivity(regIntent);
            }
        });











    }
}