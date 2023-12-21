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


        // Todo the remember me must first check if email and password are in the database then store it in sharedpref
        RememberMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 //checkbox labeled "Remember Me," which will save the email in shared preferences.
                if (RememberMe.isChecked() && Email.getText().toString() !=null && Email.getText().toString() !=null) {
                    sharedPrefManager.writeString("Email",Email.getText().toString());
                    sharedPrefManager.writeString("Password",Password.getText().toString());
                   // Toast.makeText(LoginPage.this, "Values written to shared Preferences", Toast.LENGTH_SHORT).show();
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
                if (dataBaseLogin.verifyLogin(Email.getText().toString(), Password.getText().toString())) {
                    Toast.makeText(LoginPage.this, "Login successful", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginPage.this, "Login failed. Check your credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    // Todo if it in the shared prefernce, the next time users log in, they won't need to re-enter their email.
    /*textViewUserName.setText(sharedPrefManager.readString("Email","noValue"));
     textViewPassword.setText(sharedPrefManager.readString("Password","noValue"));*/

    public void onBackButtonClick(View view) {
        // Handle the back button click
        onBackPressed();
    }
}