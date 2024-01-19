package edu.birzeit.advancecardealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddAdminActivity extends AppCompatActivity {

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);
        intent = new Intent(AddAdminActivity.this, SignPage.class);

    }

    public void onBackButtonClick(View view) {
        // Handle the back button click
        onBackPressed();
    }
}