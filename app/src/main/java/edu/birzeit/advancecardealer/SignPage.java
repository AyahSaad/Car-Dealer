package edu.birzeit.advancecardealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.birzeit.advancecardealer.user.HomePage;

public class SignPage extends AppCompatActivity {
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static final String PASSWORD_PATTERN =
            "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!])([A-Za-z\\d@#$%^&+=!]){5,}$";

    private static final Pattern passPattern = Pattern.compile(PASSWORD_PATTERN);

    public static boolean isValidPassword(String password) {
        Matcher matcher = passPattern.matcher(password);
        return matcher.matches();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_page);

        DataBaseHelper dataBaseSign = new DataBaseHelper(SignPage.this, "CarsDatabase", null, 1);


        String [] genderOptions = {"Select Gender" , "Male" , "Female"};
        final Spinner genderSpinner = (Spinner)findViewById(R.id.genderSpinner);
        ArrayAdapter<String> objGenderArray = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, genderOptions);
        genderSpinner.setAdapter(objGenderArray);

        String [] countryOptions = {"Select Country" , "Palestine" , "Egypt", "Syria", "Jordan"};
        final Spinner countrySpinner = (Spinner)findViewById(R.id.countrySpinner);
        ArrayAdapter<String> obCountryArray = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, countryOptions);
        countrySpinner.setAdapter(obCountryArray);
        final Spinner citySpinner = (Spinner)findViewById(R.id.citySpinner);
        TextView  zip = findViewById(R.id.zip);

        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updateCitySpinner(citySpinner,countrySpinner.getSelectedItem().toString(),zip);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Toast country = Toast.makeText(SignPage.this,"Select the Country please",Toast.LENGTH_SHORT);
                country.show();
            }
        });



        EditText firstName = findViewById(R.id.firstName);
        EditText lastName = findViewById(R.id.lastName);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.signPassword);
        EditText confirm = findViewById(R.id.confirmPassword);
        EditText phone = findViewById(R.id.signPhone);
        Button sign = findViewById(R.id.signInButton);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firstName.getText().length() < 3){
                    Toast firstname = Toast.makeText(SignPage.this,"The First name should be not less than 3 characters",Toast.LENGTH_SHORT);
                    firstname.show();
                } else if(lastName.getText().length() < 3){
                    Toast lastname = Toast.makeText(SignPage.this,"The last name should be not less than 3 characters",Toast.LENGTH_SHORT);
                    lastname.show();
                } else if (isValidEmail(email.getText().toString())  == false|| email.getText().toString().isEmpty()){
                    Toast email = Toast.makeText(SignPage.this,"The Entered email is on wrong format",Toast.LENGTH_SHORT);
                    email.show();
                }else if(isValidPassword(password.getText().toString()) == false|| password.getText().toString().isEmpty()){
                    Toast password = Toast.makeText(SignPage.this,"The password (must not be less than 5 characters and must include at least 1 \n" +
                            "character, 1 number, and one special character)",Toast.LENGTH_SHORT);
                    password.show();
                }else if(isValidPassword(confirm.getText().toString()) == false|| confirm.getText().toString().isEmpty() || confirm.getText().toString().matches(password.getText().toString()) == false){
                    Toast password = Toast.makeText(SignPage.this,"The confirm is not the same password",Toast.LENGTH_SHORT);
                    password.show();
                }else if (genderSpinner.getSelectedItem().toString().equals("Select Gender")){
                    Toast gender = Toast.makeText(SignPage.this,"Select the Gender please",Toast.LENGTH_SHORT);
                    gender.show();
                }else if(countrySpinner.getSelectedItem().toString().equals("Select Country")){
                    Toast country = Toast.makeText(SignPage.this,"Select the Country please",Toast.LENGTH_SHORT);
                    country.show();

                }else if(citySpinner.getSelectedItem().toString().equals("Select City")){
                    Toast country = Toast.makeText(SignPage.this,"Select the city please",Toast.LENGTH_SHORT);
                    country.show();

                }else if(phone.getText().length() !=10 || phone.getText().toString().isEmpty()){
                    Toast phone = Toast.makeText(SignPage.this,"The phone number should be from 10 digits",Toast.LENGTH_SHORT);
                    phone.show();

                }
                else{

                    dataBaseSign.insertUser(new User(firstName.getText().toString(),lastName.getText().toString(),email.getText().toString(),password.getText().toString(),genderSpinner.getSelectedItem().toString(),countrySpinner.getSelectedItem().toString(),citySpinner.getSelectedItem().toString(),"User",zip.getText().toString()+phone.getText().toString()));
                    Intent signIntent = new Intent(SignPage.this, HomePage.class);
                    startActivity(signIntent);
                }
            }
        });
    }

    private void updateCitySpinner(Spinner citySpinner, String selectedCountry, TextView zip) {

        String [] palestine = {"Select City" , "Ramallah" , "Nablus","Hebron"};
        String [] jordan = {"Select City" , "Amman" , "Irbid","Aqaba"};
        String [] egypt = {"Select City" , "Alexandria" , "Cairo","Luxor"};
        String []  syria = {"Select City" , "Damascus" , "Aleppo","Homs"};

        if (selectedCountry.equals("Palestine")) {
            ArrayAdapter<String> obCityArray = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, palestine);
            obCityArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            citySpinner.setAdapter(obCityArray);
            zip.setText("00970");
        }else if (selectedCountry.equals("Egypt")){
            ArrayAdapter<String> obCityArray = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,egypt);
            obCityArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            citySpinner.setAdapter(obCityArray);
            zip.setText("0020");
        }else if (selectedCountry.equals("Syria")){
            ArrayAdapter<String> obCityArray = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,syria);
            obCityArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            citySpinner.setAdapter(obCityArray);
            zip.setText("00963");

        }else if (selectedCountry.equals("Jordan")){
            ArrayAdapter<String> obCityArray = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,jordan);
            obCityArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            citySpinner.setAdapter(obCityArray);
            zip.setText("00962");
        }
    }
    public void onBackButtonClick(View view) {
        // Handle the back button click
        onBackPressed();
    }


}