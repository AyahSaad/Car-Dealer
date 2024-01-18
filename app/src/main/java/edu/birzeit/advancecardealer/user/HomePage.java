package edu.birzeit.advancecardealer.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Layout;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

import edu.birzeit.advancecardealer.DataBaseHelper;
import edu.birzeit.advancecardealer.LoginPage;
import edu.birzeit.advancecardealer.MainActivity;
import edu.birzeit.advancecardealer.R;
import edu.birzeit.advancecardealer.RegSection;
import edu.birzeit.advancecardealer.SignPage;
import edu.birzeit.advancecardealer.admin.AdminMainPage;
import edu.birzeit.advancecardealer.admin.AdminOffers;
import edu.birzeit.advancecardealer.admin.AllReserves;
import edu.birzeit.advancecardealer.admin.DeleteCustomersActivity;

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
                    intent = new Intent(edu.birzeit.advancecardealer.user.HomePage.this, HomePage.class);
                    Toast.makeText(edu.birzeit.advancecardealer.user.HomePage.this, "Home page", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else if (item.getItemId() == R.id.CarMenu) {
                    intent = new Intent(edu.birzeit.advancecardealer.user.HomePage.this, HomePage.class);
                    Toast.makeText(edu.birzeit.advancecardealer.user.HomePage.this, "CarMenu", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else if (item.getItemId() == R.id.Reservations) {
                    intent = new Intent(edu.birzeit.advancecardealer.user.HomePage.this, AllReserves.class);
                    Toast.makeText(edu.birzeit.advancecardealer.user.HomePage.this, "Reservation", Toast.LENGTH_SHORT).show();

                    startActivity(intent);
                } else if (item.getItemId() == R.id.Favorites) {
                    intent = new Intent(edu.birzeit.advancecardealer.user.HomePage.this, HomePage.class);

                    Toast.makeText(edu.birzeit.advancecardealer.user.HomePage.this, "Favorites", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else if (item.getItemId() == R.id.Offer) {
                    intent = new Intent(edu.birzeit.advancecardealer.user.HomePage.this, HomePage.class);

                    Toast.makeText(edu.birzeit.advancecardealer.user.HomePage.this, "Offers", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else if (item.getItemId() == R.id.Profile) {
                    intent = new Intent(edu.birzeit.advancecardealer.user.HomePage.this, CustomerProfile.class);

                    Toast.makeText(edu.birzeit.advancecardealer.user.HomePage.this, "Profile", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else if (item.getItemId() == R.id.CallUs) {
                    intent = new Intent(edu.birzeit.advancecardealer.user.HomePage.this, ContactUs.class);

                    Toast.makeText(edu.birzeit.advancecardealer.user.HomePage.this, "CallUs", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else if (item.getItemId() == R.id.logout) {
                    intent = new Intent(edu.birzeit.advancecardealer.user.HomePage.this, RegSection.class);
                    Toast.makeText(edu.birzeit.advancecardealer.user.HomePage.this, "Logout", Toast.LENGTH_SHORT).show();
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