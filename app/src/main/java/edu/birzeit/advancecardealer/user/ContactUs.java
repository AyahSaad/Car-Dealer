package edu.birzeit.advancecardealer.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.birzeit.advancecardealer.LoginPage;
import edu.birzeit.advancecardealer.R;

public class ContactUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        Button call = findViewById(R.id.callUs);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dialIntent =new Intent();
                dialIntent.setAction(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:0599000000"));
                startActivity(dialIntent);
            }
        });

        Button email = findViewById(R.id.chat);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gmailIntent =new Intent();
                gmailIntent.setAction(Intent.ACTION_SENDTO);
                gmailIntent.setType("message/rfc822");
                gmailIntent.setData(Uri.parse("mailto:"));
                gmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"CarDealer@cars.com"});
                startActivity(gmailIntent);
            }
        });

        Button find = findViewById(R.id.findUs);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent mapsIntent =new Intent(ContactUs.this, LoginPage.class);
                    mapsIntent.setAction(Intent.ACTION_VIEW);
                    startActivity(mapsIntent);
                }
        });




    }
    public void onBackButtonClick(View view) {
        // Handle the back button click
        onBackPressed();
    }
}