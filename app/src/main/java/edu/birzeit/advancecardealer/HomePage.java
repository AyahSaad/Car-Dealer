package edu.birzeit.advancecardealer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.List;
import java.util.Objects;

public class HomePage extends AppCompatActivity {
    Intent intent;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        DataBaseHelper dataBaseHome = new DataBaseHelper(HomePage.this, "CarsDatabase", null, 1);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);

        drawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.Custhome) {
                    intent = new Intent(HomePage.this, HomePage.class);
                    Toast.makeText(HomePage.this, "Home page", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else if (item.getItemId() == R.id.CarMenu) {
                    intent = new Intent(HomePage.this, CarsMenu.class);
                    Toast.makeText(HomePage.this, "CarMenu", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else if (item.getItemId() == R.id.Reservations) {
                    intent = new Intent(HomePage.this, AllReserves.class);
                    Toast.makeText(HomePage.this, "Reservation", Toast.LENGTH_SHORT).show();

                    startActivity(intent);
                } else if (item.getItemId() == R.id.Favorites) {
                    intent = new Intent(HomePage.this, HomePage.class);

                    Toast.makeText(HomePage.this, "Favorites", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else if (item.getItemId() == R.id.Offer) {
                    intent = new Intent(HomePage.this, offeredCars.class);

                    Toast.makeText(HomePage.this, "Offers", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else if (item.getItemId() == R.id.Profile) {
                    intent = new Intent(HomePage.this, CustomerProfile.class);

                    Toast.makeText(HomePage.this, "Profile", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else if (item.getItemId() == R.id.CallUs) {
                    intent = new Intent(HomePage.this, ContactUs.class);

                    Toast.makeText(HomePage.this, "CallUs", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else if (item.getItemId() == R.id.logout) {
                    intent = new Intent(HomePage.this, RegSection.class);
                    Toast.makeText(HomePage.this, "Logout", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }
}