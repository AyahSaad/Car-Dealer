package edu.birzeit.advancecardealer;

import androidx.appcompat.app.AppCompatActivity;

import edu.birzeit.advancecardealer.admin.AdminMainPage;
import edu.birzeit.advancecardealer.admin.AllReserves;
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

import java.util.Locale;

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
                        sharedPrefManager.writeString("email",Email.getText().toString().toLowerCase());
                        sharedPrefManager.writeString("password",Password.getText().toString());
                        sharedPrefManager.putBoolean("rememberM",true);
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
                if (rememberMeCheckBox.isChecked() && Email.getText().toString() !=null && Password.getText().toString() !=null) {
                    sharedPrefManager.writeString("email",Email.getText().toString().toLowerCase());
                    sharedPrefManager.writeString("password",Password.getText().toString());
                    // Toast.makeText(LoginPage.this, "Values written to shared Preferences", Toast.LENGTH_SHORT).show();
                }
                if (dataBaseLogin.verifyLogin(Email.getText().toString(), Password.getText().toString())) {
                    Cursor Type= dataBaseLogin.getType(Email.getText().toString().toLowerCase());
                    Toast.makeText(LoginPage.this, "Login successful", Toast.LENGTH_SHORT).show();
                  // Todo
                    if(Type.moveToNext()){
                        if(Type.getString(Type.getColumnIndex("TYPE")).equals("Admin")){
                            sharedPrefManager.writeString("type","Admin");
                            Intent intent = new Intent(LoginPage.this, AdminMainPage.class);
                            startActivity(intent);

                        }else{
                            sharedPrefManager.writeString("email",Type.getString(Type.getColumnIndex("EMAIL")));
                            sharedPrefManager.writeString("type","User");
                            Intent intent = new Intent(LoginPage.this, CustomerProfile.class);
                            intent.putExtra("email",Type.getString(Type.getColumnIndex("EMAIL")));
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