package edu.birzeit.advancecardealer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import edu.birzeit.advancecardealer.R;
import edu.birzeit.advancecardealer.SharedPrefManager;
import edu.birzeit.advancecardealer.SharedViewModel;

public class CarsMenu extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_menu);
        sharedPrefManager = SharedPrefManager.getInstance(this);
        System.out.println("The car MENU Current User Email is: " +sharedPrefManager.readString("currentUserEmail", "")+"\n"+"The current user Type "+sharedPrefManager.readString("currentUserType", ""));
    }
}