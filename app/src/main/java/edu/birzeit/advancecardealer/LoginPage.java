package edu.birzeit.advancecardealer;

import androidx.appcompat.app.AppCompatActivity;
import edu.birzeit.advancecardealer.user.*;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.birzeit.advancecardealer.user.ContactUs;
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
        DataBaseHelper dataBaseLogin = new DataBaseHelper(LoginPage.this, "CarsDatabase", null, 1);
        sharedPrefManager = SharedPrefManager.getInstance(this);
        intent = new Intent(LoginPage.this, HomePage.class);

        rememberMeCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save email and password to SharedPreferences if "Remember Me" is checked
                if (rememberMeCheckBox.isChecked()) {
                    // Check if the email and password exist in the database
                    if (dataBaseLogin.verifyLogin(Email.getText().toString(), Password.getText().toString())) {
                        sharedPrefManager.writeString("email",Email.getText().toString());
                        sharedPrefManager.writeString("password",Password.getText().toString());
                        sharedPrefManager.putBoolean("rememberM",true);
                        System.out.print(rememberMeCheckBox.getText().toString());
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
                    Cursor Type= dataBaseLogin.getType(Email.getText().toString());
                    Toast.makeText(LoginPage.this, "Login successful", Toast.LENGTH_SHORT).show();
                  // Todo
                    if(Type.moveToNext()){
                        if(Type.getString(Type.getColumnIndex("TYPE")).equals("Admin")){
                            sharedPrefManager.writeString("type","Admin");


                            Intent intent = new Intent(LoginPage.this,ContactUs.class);
                            startActivity(intent);

                        }else{
                            sharedPrefManager.writeString("type","User");

                            startActivity(intent);
                        }
                    }

                } else {
                    Toast.makeText(LoginPage.this, "Login failed. Check your credentials", Toast.LENGTH_SHORT).show();
                }


            }

        });
        // Display stored email and password in TextViews for testing
        Email.setText(sharedPrefManager.readString("email", ""));
        Password.setText( sharedPrefManager.readString("password", ""));
    }

    public void onBackButtonClick(View view) {
        // Handle the back button click
        onBackPressed();
    }

}