package edu.birzeit.advancecardealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.birzeit.advancecardealer.user.ContactUs;

public class RegSection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_section);
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
                Intent loginIntent = new Intent(RegSection.this, LoginPage.class);
                startActivity(loginIntent);
            }
        });
    }
}


