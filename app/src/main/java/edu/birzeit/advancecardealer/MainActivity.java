package edu.birzeit.advancecardealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

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



        User user1 = new User("hala", "ziq", "hala@ziq.com", "h12345!", "female", "Palestine", "Ramallah", "Admin", "00970592687071","https://th.bing.com/th/id/OIP.d_V5Ti60n3mJuPheks-k4AHaHa?rs=1&pid=ImgDetMain");
        User user2= new User("ayah", "saad", "ayah@saad.com", "a12345!", "female", "Palestine", "Ramallah", "User", "00970592687071","https://th.bing.com/th/id/OIP.d_V5Ti60n3mJuPheks-k4AHaHa?rs=1&pid=ImgDetMain");

        dataBaseMain.insertUser(user1);
        dataBaseMain.insertUser(user2);

        Button getStarted = findViewById(R.id.getStarted);
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getStarted.setBackgroundColor(Color.BLACK);
                ConnectionAsyncTask connectionAsyncTask = new ConnectionAsyncTask(MainActivity.this,MainActivity.this,dataBaseMain);
                //connectionAsyncTask.execute("https://cardealer.free.beeceptor.com/cars");
               connectionAsyncTask.execute("https://carss.free.beeceptor.com/cars1");
            }
        });













    }



}