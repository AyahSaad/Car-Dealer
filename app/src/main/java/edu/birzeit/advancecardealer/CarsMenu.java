package edu.birzeit.advancecardealer;

import static edu.birzeit.advancecardealer.CarJsonParser.cars;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import edu.birzeit.advancecardealer.R;
import edu.birzeit.advancecardealer.SharedPrefManager;
import edu.birzeit.advancecardealer.SharedViewModel;

public class CarsMenu extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_menu);
        sharedPrefManager = SharedPrefManager.getInstance(this);
        recyclerView = findViewById(R.id.recyclerView);


        setuprecyclerview();


    }

    private void setuprecyclerview(){

        MyAdapter myAdapter = new MyAdapter(this,cars);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);


    }
    public void onBackButtonClick(View view) {
        // Handle the back button click
        onBackPressed();
    }
}