package edu.birzeit.advancecardealer.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

import edu.birzeit.advancecardealer.R;
import edu.birzeit.advancecardealer.RegSection;
import edu.birzeit.advancecardealer.SignPage;

public class AdminMainPage extends AppCompatActivity {
    Intent intent;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main_page);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);

        drawerToggle.syncState();
       Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    Toast.makeText(AdminMainPage.this, "Home", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.delete) {
                    intent = new Intent(AdminMainPage.this, DeleteCustomersActivity.class);
                    Toast.makeText(AdminMainPage.this, "Delete", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else if (item.getItemId() == R.id.AddAdmin) {
                    intent = new Intent(AdminMainPage.this, SignPage.class);
                    Toast.makeText(AdminMainPage.this, "Add Admin", Toast.LENGTH_SHORT).show();
                    intent.putExtra("Type","Admin" );
                    startActivity(intent);
                } else if (item.getItemId() == R.id.View) {
                    intent = new Intent(AdminMainPage.this, AllReserves.class);
                    intent.putExtra("email","Admin");
                    Toast.makeText(AdminMainPage.this, "View", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else if (item.getItemId() == R.id.logout) {
                    intent = new Intent(AdminMainPage.this, RegSection.class);
                    Toast.makeText(AdminMainPage.this, "Logout", Toast.LENGTH_SHORT).show();
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