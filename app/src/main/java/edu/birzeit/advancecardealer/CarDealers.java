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
                        System.out.println(car.getId());
                        dealers.add(car);
                    }
                }
            }

        }
        //////////////////////////////

//        for (int i =0;i<dealers.size();i++){
//            Button chosen = new Button(CarDealers.this);
//            chosen.setTextSize(16);
//            chosen.setText(dealers.get(i).getCompany());
//            chosen.setWidth(150);
//            chosen.setHeight(80);
//            chosen.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//            chosen.setBackgroundColor(Color.parseColor("#000000"));
//            chosen.setTextColor(Color.WHITE);
//            chosen.setTypeface(null, Typeface.BOLD);
//            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
//                    RelativeLayout.LayoutParams.WRAP_CONTENT,
//                    RelativeLayout.LayoutParams.WRAP_CONTENT
//            );
//
//            // Customize the layout parameters as needed
//            layoutParams.setMargins(70, 70, 150, 0);
//            chosen.setLayoutParams(layoutParams);
//            scrollView.addView(chosen);
//            String company = dealers.get(i).getCompany();
//            chosen.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    sharedPrefManager.writeString("currentCompany",company);
//                    System.out.println("=========================================== company"+company);
//
//                    Intent intent = new Intent(CarDealers.this, HomePage.class);
//                    startActivity(intent);
//
//                }
//            });
//
//
//        }



    }
}