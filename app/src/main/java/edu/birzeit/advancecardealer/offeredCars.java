package edu.birzeit.advancecardealer;

import static edu.birzeit.advancecardealer.CarJsonParser.cars;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class offeredCars extends AppCompatActivity {

    public static List<Car> offers = new ArrayList<>();
    List<Car>  carOffers;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offered_cars);
        recyclerView = findViewById(R.id.recyclerView2);


        for (Car car : cars) {
            if (car.getOffer() > 0) {
                offers.add(car);

            }
        }

        setuprecyclerview();

        }

    private void setuprecyclerview(){

        MyAdapter myAdapter = new MyAdapter(this,offers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);


    }
    }
