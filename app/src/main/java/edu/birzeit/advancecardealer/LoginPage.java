package edu.birzeit.advancecardealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import edu.birzeit.advancecardealer.user.HomePage;

public class LoginPage extends AppCompatActivity {
    EditText Email;
    EditText Password;
    Button logIn;
    CheckBox RememberMe;
    SharedPrefManager sharedPrefManager;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        Email = (EditText) findViewById(R.id.Email);
        Password = (EditText) findViewById(R.id.Password);
        logIn = (Button) findViewById(R.id.logIn);
        RememberMe = findViewById(R.id.RememberMe);
        DataBaseHelper dataBaseLogin = new DataBaseHelper(LoginPage.this, "CarsDatabase", null, 1);
        sharedPrefManager = SharedPrefManager.getInstance(this);
        intent = new Intent(LoginPage.this, HomePage.class);

        RememberMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 //checkbox labeled "Remember Me," which will save the email in shared preferences.
                if (RememberMe.isChecked()) {
                    sharedPrefManager.writeString("Email",Email.getText().toString());
                    sharedPrefManager.writeString("Password",Password.getText().toString());
                    Toast.makeText(LoginPage.this, "Values written to shared Preferences", Toast.LENGTH_SHORT).show();
                }

            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Todo Check if email and password match a user in the database, if in the database then startActivity(intent);
                User user = dataBaseLogin.getUserByEmailAndPassword(Email.getText().toString(), Password.getText().toString());
                if (user != null) {
                    // User found in the database, start the HomePage activity
                    startActivity(intent);
                } else {
                    // Display an error message if the user is not found
                    Toast.makeText(LoginPage.this, "Invalid credentials. Please re-enter your info or sign up.", Toast.LENGTH_SHORT).show();
                }



                // Todo if it in the shared prefernce, the next time users log in, they won't need to re-enter their email.
              /*  textViewUserName.setText(sharedPrefManager.readString("Email","noValue"));
                textViewPassword.setText(sharedPrefManager.readString("Password","noValue"));*/
            }
        });

    }

    public void onBackButtonClick(View view) {
        // Handle the back button click
        onBackPressed();
    }
}