package edu.birzeit.advancecardealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.birzeit.advancecardealer.user.HomePage;

public class LoginPage extends AppCompatActivity {
    EditText Email;
    EditText Password;
    Button logIn;
    CheckBox rememberMeCheckBox;
    SharedPrefManager sharedPrefManager;
    Intent intent;
    private TextView textViewStoredEmail;
    private TextView textViewStoredPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        Email = (EditText) findViewById(R.id.Email);
        Password = (EditText) findViewById(R.id.Password);
        logIn = (Button) findViewById(R.id.logIn);
        rememberMeCheckBox  = findViewById(R.id.RememberMe);
        textViewStoredEmail = findViewById(R.id.textViewStoredEmail);
        textViewStoredPassword = findViewById(R.id.textViewStoredPassword);
        DataBaseHelper dataBaseLogin = new DataBaseHelper(LoginPage.this, "CarsDatabase", null, 1);
        sharedPrefManager = SharedPrefManager.getInstance(this);
        intent = new Intent(LoginPage.this, HomePage.class);

        // Load saved email and password from SharedPreferences if "Remember Me" is checked
        if (rememberMeCheckBox.isChecked()) {
            Email.setText(sharedPrefManager.readString("email","noValue"));
            Password.setText(sharedPrefManager.readString("password","noValue"));
        }

       // sharedPrefManager.clearAllEntries();

        // Todo the remember me must first check if email and password are in the database then store it in sharedpref

        rememberMeCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save email and password to SharedPreferences if "Remember Me" is checked
                if (rememberMeCheckBox.isChecked()) {
                    // Check if the email and password exist in the database
                    if (dataBaseLogin.verifyLogin(Email.getText().toString(), Password.getText().toString())) {
                        sharedPrefManager.writeString("email",Email.getText().toString());
                        sharedPrefManager.writeString("password",Password.getText().toString());
                        Toast.makeText(LoginPage.this, "Email and password exist in database", Toast.LENGTH_SHORT).show();
                    } else {
                        // If not found in the database, uncheck "Remember Me" and show a message
                        rememberMeCheckBox.setChecked(false);
                        Toast.makeText(LoginPage.this, "Email and password not found in the database.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if email and password are not empty
                if (Email.getText().toString().isEmpty() || Password.getText().toString().isEmpty()) {
                    Toast.makeText(LoginPage.this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rememberMeCheckBox.isChecked() && Email.getText().toString() !=null && Email.getText().toString() !=null) {
                    sharedPrefManager.writeString("email",Email.getText().toString());
                    sharedPrefManager.writeString("password",Password.getText().toString());
                    // Toast.makeText(LoginPage.this, "Values written to shared Preferences", Toast.LENGTH_SHORT).show();
                }
                if (dataBaseLogin.verifyLogin(Email.getText().toString(), Password.getText().toString())) {
                    Toast.makeText(LoginPage.this, "Login successful", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginPage.this, "Login failed. Check your credentials", Toast.LENGTH_SHORT).show();
                }

                // Display stored email and password in TextViews
                textViewStoredEmail.setText(sharedPrefManager.readString("email", ""));
                textViewStoredPassword.setText( sharedPrefManager.readString("password", ""));
            }

        });
    }

    // Todo if it in the shared prefernce, the next time users log in, they won't need to re-enter their email.

    public void onBackButtonClick(View view) {
        // Handle the back button click
        onBackPressed();
    }

}