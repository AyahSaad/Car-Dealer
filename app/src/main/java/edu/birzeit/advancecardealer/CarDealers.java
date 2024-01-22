package edu.birzeit.advancecardealer;

import static edu.birzeit.advancecardealer.CarJsonParser.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.util.List;

public class CarDealers extends AppCompatActivity {
    public  static List<Car>dealers;
    SharedPrefManager sharedPrefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_dealers);
        ScrollView scrollView = findViewById(R.id.mainLayout);
        sharedPrefManager = SharedPrefManager.getInstance(this);
        DataBaseHelper dataBaseDealer = new DataBaseHelper(CarDealers.this, "CarsDatabase", null, 1);
        Cursor cursor = dataBaseDealer.getCar();
        scrollView.removeAllViews();
        while (cursor.moveToNext()) {
            for (Car car : cars){
                if (cursor.getString(cursor.getColumnIndex("COMPANY")).equals(car.getCompany())){
                    if (dealers.size() == 0){
                        dealers.add(car);
                    }
                }
            }

        }

    }
}