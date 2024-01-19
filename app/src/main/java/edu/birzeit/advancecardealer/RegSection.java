package edu.birzeit.advancecardealer;

import static edu.birzeit.advancecardealer.CarJsonParser.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegSection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_section);
        DataBaseHelper dataBaseReg = new DataBaseHelper(RegSection.this, "CarsDatabase", null, 1);
        Cursor cursor = dataBaseReg.getCar();

        for (int i =0; i<cars.size();i++){
           Car car = new Car(cars.get(i).getId(),cars.get(i).getFactoryName(),cars.get(i).getType(),cars.get(i).getPrice(),cars.get(i).getModel(),cars.get(i).getName(),cars.get(i).getOffer(),cars.get(i).getYear(),cars.get(i).getFuelType(),cars.get(i).getRating(),cars.get(i).getAccident(),cars.get(i).getColor(),cars.get(i).getHasAspare(),cars.get(i).getDoorsCount(),cars.get(i).getImage(),cars.get(i).getCompany());
            dataBaseReg.insertCar(car);
        }





        Button signup = findViewById(R.id.signUp);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupIntent = new Intent(RegSection.this,SignPage.class);
                signupIntent.putExtra("Type","User");
                startActivity(signupIntent);
            }
        });

        Button login = findViewById(R.id.logIn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(RegSection.this,
                        LoginPage.class);
                startActivity(loginIntent);
            }
        });







    }
}


