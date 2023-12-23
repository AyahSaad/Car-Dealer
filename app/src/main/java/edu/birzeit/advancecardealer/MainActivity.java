package edu.birzeit.advancecardealer;

import androidx.appcompat.app.AppCompatActivity;

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

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File dataBase = this.getDatabasePath("SIMPLE_LIBRARY");
        if (dataBase.exists()){
            this.deleteDatabase("CarsDatabase");
        }
        DataBaseHelper dataBaseMain = new DataBaseHelper(MainActivity.this, "CarsDatabase", null, 1);
        User user1 = new User("hala", "ziq", "hala@ziq.com", "h12345!", "female", "Palestine", "Ramallah", "Admin", "00970592687071");
        User user2= new User("ayah", "saad", "ayah@saad.com", "a12345!", "female", "Palestine", "Ramallah", "Admin", "00970592687071");
        dataBaseMain.insertUser(user1);
        dataBaseMain.insertUser(user2);
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