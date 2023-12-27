package edu.birzeit.advancecardealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

import edu.birzeit.advancecardealer.admin.AllReserves;
import edu.birzeit.advancecardealer.user.HomePage;


public class MainActivity extends AppCompatActivity {
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
        User user2= new User("ayah", "saad", "ayah@saad.com", "a12345!", "female", "Palestine", "Ramallah", "Admin", "00970592687071");
        User user3= new User("ayah", "abed", "ayah@22saad.com", "a12345!", "female", "Palestine", "Ramallah", "Admin", "00970592687071");
        User user4= new User("malak", "saad", "malak@saad.com", "a12345!", "female", "Palestine", "Ramallah", "User", "00970592687071");
        User user5= new User("hala", "ziq", "hala1@ziq.com", "a12345!", "female", "Palestine", "Ramallah", "User", "00970592687071");

        dataBaseMain.insertUser(user1);
        dataBaseMain.insertUser(user2);
        dataBaseMain.insertUser(user3);
        dataBaseMain.insertUser(user4);
        dataBaseMain.insertUser(user5);

        Car car1 = new Car(1, "Toyota", "Sedan", (long) 25000.0, "Camry", "Camry XLE", 2000, "2022", "Gasoline", (long)4.0, "No");
        Car car2 = new Car(2, "Honda", "SUV", (long)30000.0, "CR-V", "CR-V EX", 1500, "2023", "Hybrid", (long) 4.5, "No");
        Car car3 = new Car(3, "Ford", "Truck", (long)35000.0, "F-150", "F-150 Lariat", 3000, "2022", "Gasoline", (long)4.2, "Yes");

        dataBaseMain.insertCar(car1);
        dataBaseMain.insertCar(car2);
        dataBaseMain.insertCar(car3);

        Reserve reserve1 = new Reserve(1, "ayah@saad.com", 1, "2023-01-01", "10:00 AM");
        Reserve reserve2 = new Reserve(2, "ayah@saad.com", 2, "2023-02-02", "11:30 AM");
        Reserve reserve3 = new Reserve(3, "ayah@saad.com", 3, "2023-03-03", "02:15 PM");
        Reserve reserve4 = new Reserve(4, "ayah@saad.com", 1, "2023-04-04", "04:45 PM");
        Reserve reserve5 = new Reserve(5, "ayah@saad.com", 2, "2023-05-05", "08:00 AM");
        Reserve reserve6 = new Reserve(6, "ayah@saad.com", 3, "2023-06-06", "01:30 PM");
        Reserve reserve7 = new Reserve(7, "ayah@saad.com", 1, "2023-07-07", "03:45 PM");
        Reserve reserve8 = new Reserve(8, "ayah@saad.com", 2, "2023-08-08", "06:00 PM");
        dataBaseMain.insertReservation(reserve1);
        dataBaseMain.insertReservation(reserve2);
        dataBaseMain.insertReservation(reserve3);
        dataBaseMain.insertReservation(reserve4);
        dataBaseMain.insertReservation(reserve5);
        dataBaseMain.insertReservation(reserve6);
        dataBaseMain.insertReservation(reserve7);
        dataBaseMain.insertReservation(reserve8);

        Button getStarted = findViewById(R.id.getStarted);
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              getStarted.setBackgroundColor(Color.BLACK);
              Intent regIntent = new Intent(MainActivity.this, RegSection.class);
              startActivity(regIntent);
            }
        });
    }

//        if (rememberMe == true && type.equals("Admin") ){
//            //TODO Add Admin Home Page Intent
//            Intent home = new Intent(MainActivity.this, ContactUs.class);
//            startActivity(home);
//        }else if (rememberMe == true && type.equals("User") ){
//            Intent home = new Intent(MainActivity.this, HomePage.class);
//            startActivity(home);
//
//        }
//
//        Button getStarted = findViewById(R.id.getStarted);
//        getStarted.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getStarted.setBackgroundColor(Color.BLACK);
//                Intent regIntent = new Intent(MainActivity.this,RegSection.class);
//                startActivity(regIntent);
//            }
//        });

    public void submitData(View view){
        startActivity(new Intent(this,AllReserves.class));
    }

    }



