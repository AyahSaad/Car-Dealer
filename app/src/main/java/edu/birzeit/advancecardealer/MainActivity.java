package edu.birzeit.advancecardealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

import edu.birzeit.advancecardealer.admin.AdminMainPage;

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
        User user3= new User("ayah", "abed", "ayah@22saad.com", "a12345!", "female", "Palestine", "Ramallah", "Admin", "00970592687071");
        User user4= new User("malak", "saad", "malak@saad.com", "a12345!", "female", "Palestine", "Ramallah", "User", "00970592687071");
        User user5= new User("hala", "ziq", "hala1@ziq.com", "a12345!", "female", "Palestine", "Ramallah", "User", "00970592687071");

        dataBaseMain.insertUser(user1);
        dataBaseMain.insertUser(user2);
        dataBaseMain.insertUser(user3);
        dataBaseMain.insertUser(user4);
        dataBaseMain.insertUser(user5);

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