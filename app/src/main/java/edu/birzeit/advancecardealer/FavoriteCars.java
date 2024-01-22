package edu.birzeit.advancecardealer;

import static edu.birzeit.advancecardealer.CarJsonParser.cars;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class FavoriteCars extends AppCompatActivity {
    public static List<Car> liked = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBaseHelper dataBaseLike = new DataBaseHelper(FavoriteCars.this, "CarsDatabase", null, 1);
        setContentView(R.layout.activity_favorite_cars);
        recyclerView = findViewById(R.id.recyclerViewFav);

        Cursor favCursor = dataBaseLike.getFav();
        while (favCursor.moveToNext()){
            for(Car car : cars){
                if ( !liked.contains(car) && car.getId() == favCursor.getInt(favCursor.getColumnIndex("CAR_ID"))){
                    liked.add(car);
                }

            }
        }
        setuprecyclerview();


    }
    private void setuprecyclerview(){

        MyAdapter myAdapter = new MyAdapter(this,liked);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);


    }
    public void onBackButtonClick(View view) {
        // Handle the back button click
        onBackPressed();
    }
}