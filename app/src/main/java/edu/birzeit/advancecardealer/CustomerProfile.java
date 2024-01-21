package edu.birzeit.advancecardealer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.imageview.ShapeableImageView;


public class CustomerProfile extends AppCompatActivity {
    private ScrollView scrollView;
    private ShapeableImageView profileImage;
    private ImageButton editButton;
    private ImageButton editFirstNameButton;
    private ImageButton editLastNameButton;
    private ImageButton editPasswordButton;
    private ImageButton editGenderButton;
    private TextView firstNameTextView;
    private TextView lastNameTextView;
    private TextView genderTextView;
    private TextView passwordTextView;
    private Button editLocationButton;
    private Button editEmailButton;
    private Button editPhoneButton;
    private TextView viewCountry;
    private TextView viewCity;
    private TextView viewEmail;
    private TextView viewPhone;
    private SharedPrefManager sharedPrefManager;

    private EditText editPhone;
    private Button savePhoneButton;
    private EditText editEmail;
    private Button saveEmailButton;
    //private EditText editCountry;
    private Button saveCountryButton;
    //private EditText editCity;
    private Button saveCityButton;
    private EditText editFirstName;
    private Button saveFirstNameButton;
    private EditText editLastName;
    private Button saveLastNameButton;
    //private EditText editGender;
    private Button saveGenderButton;
    private EditText editPassword;
    private Button savePasswordButton;

    private Spinner edit_gender_Spinner;
    private Spinner edit_country_Spinner;
    private Spinner edit_city_Spinner;


    private static final int PICK_IMAGE_REQUEST = 1;

    DataBaseHelper dataBaseCustomerProfile = new DataBaseHelper(CustomerProfile.this, "CarsDatabase", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);
        editButton = findViewById(R.id.editButton);
        scrollView = findViewById(R.id.scrollView);
        sharedPrefManager = SharedPrefManager.getInstance(this);
        profileImage = findViewById(R.id.profileImage);
        editFirstNameButton = findViewById(R.id.editFirstNameButton);
        editLastNameButton = findViewById(R.id.editLastNameButton);
        editPasswordButton = findViewById(R.id.editPasswordButton);
        editGenderButton = findViewById(R.id.editGenderButton);
        editLocationButton = findViewById(R.id.EditLocationButton);
        editEmailButton = findViewById(R.id.EditEmailButton);
        editPhoneButton = findViewById(R.id.EditPhoneButton);
        // Initialize TextViews
        firstNameTextView = findViewById(R.id.firstname);
        lastNameTextView = findViewById(R.id.lastname);
        genderTextView = findViewById(R.id.gender);
        passwordTextView = findViewById(R.id.password);
        // Initialize EditText fields and Save Changes button
        viewCountry = findViewById(R.id.countryView);
        viewCity = findViewById(R.id.cityView);
        viewEmail = findViewById(R.id.emailView);
        viewPhone = findViewById(R.id.phoneView);
        // Set initial visibility for edit buttons
        editFirstNameButton.setVisibility(View.GONE);
        editLastNameButton.setVisibility(View.GONE);
        editGenderButton.setVisibility(View.GONE);
        editPasswordButton.setVisibility(View.GONE);
        // Initialize EditText and Save Phone button
        editFirstName = findViewById(R.id.editFirstName);
        saveFirstNameButton = findViewById(R.id.saveFirstNameButton);
        editLastName = findViewById(R.id.editLastName);
        saveLastNameButton = findViewById(R.id.saveLastNameButton);
//        editGender = findViewById(R.id.editGender);

        edit_gender_Spinner = findViewById(R.id.gender_Spinner);
        saveGenderButton = findViewById(R.id.saveGenderButton);
        editPassword = findViewById(R.id.editPassword);
        savePasswordButton = findViewById(R.id.savePasswordButton);

        editPhone = findViewById(R.id.editPhone);
        savePhoneButton = findViewById(R.id.savePhoneButton);
        editEmail = findViewById(R.id.editEmail);
        saveEmailButton = findViewById(R.id.saveEmailButton);
        // editCountry = findViewById(R.id.editCountry);
        edit_country_Spinner = findViewById(R.id.country_Spinner);
        saveCountryButton = findViewById(R.id.saveCountryButton);
        // editCity = findViewById(R.id.editCity);
        edit_city_Spinner = findViewById(R.id.city_Spinner);
        saveCityButton = findViewById(R.id.saveCityButton);

        Intent intent = getIntent();
        String currentUser = sharedPrefManager.readString("currentUserEmail","");
        Cursor cursorUser = dataBaseCustomerProfile.getEmail(currentUser);

        while (cursorUser.moveToNext()){
            viewPhone.setText(cursorUser.getString(cursorUser.getColumnIndex("PHONE")));
            viewEmail.setText(cursorUser.getString(cursorUser.getColumnIndex("EMAIL")));
            viewCity.setText(cursorUser.getString(cursorUser.getColumnIndex("CITY")));
            viewCountry.setText(cursorUser.getString(cursorUser.getColumnIndex("COUNTRY")));
            firstNameTextView.setText(cursorUser.getString(cursorUser.getColumnIndex("FIRST_NAME")));
            lastNameTextView.setText(cursorUser.getString(cursorUser.getColumnIndex("LAST_NAME")));
            genderTextView.setText(cursorUser.getString(cursorUser.getColumnIndex("GENDER")));
            passwordTextView.setText(cursorUser.getString(cursorUser.getColumnIndex("PASSWORD")));
        }

        editFirstNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditFirstName();
            }
        });

        saveFirstNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFirstName(currentUser);
            }
        });


        editLastNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditLastName();
            }
        });

        saveLastNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLastName(currentUser);
            }
        });


        editGenderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditGender();
            }
        });

        saveGenderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveGender(currentUser);
            }
        });

        editPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditPassword();
            }
        });

        savePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePassword(currentUser);
            }
        });


        editPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditPhone();
            }
        });

        savePhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePhone(currentUser);
            }
        });

        editEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditEmail();
            }
        });

        saveEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEmail(currentUser);
            }
        });

        editLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditCountry();
            }
        });

        saveCountryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCountry(currentUser);
            }
        });

        saveCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCity(currentUser);
            }
        });

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setProfileImageEditButtonVisibility(editButton.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });

        // Edit Button for picture
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the "edit" button click
                // For simplicity, let's assume you're opening the gallery
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });

        firstNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEditFirstNameButtonVisibility(editFirstNameButton.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });

        lastNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEditLastNameButtonVisibility(editLastNameButton.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });

        genderTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEditGenderButtonVisibility(editGenderButton.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });

        passwordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEditPasswordButtonVisibility(editPasswordButton.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });
    }

    private void setProfileImageEditButtonVisibility(int visibility) {
        editButton.setVisibility(visibility);
        editFirstNameButton.setVisibility(View.GONE);
        editLastNameButton.setVisibility(View.GONE);
        editGenderButton.setVisibility(View.GONE);
        editPasswordButton.setVisibility(View.GONE);
    }

    private void setEditFirstNameButtonVisibility(int visibility) {
        editButton.setVisibility(View.GONE);
        editFirstNameButton.setVisibility(visibility);
        editLastNameButton.setVisibility(View.GONE);
        editGenderButton.setVisibility(View.GONE);
        editPasswordButton.setVisibility(View.GONE);
    }

    private void setEditLastNameButtonVisibility(int visibility) {
        editButton.setVisibility(View.GONE);
        editFirstNameButton.setVisibility(View.GONE);
        editLastNameButton.setVisibility(visibility);
        editGenderButton.setVisibility(View.GONE);
        editPasswordButton.setVisibility(View.GONE);
    }

    private void setEditGenderButtonVisibility(int visibility) {
        editButton.setVisibility(View.GONE);
        editFirstNameButton.setVisibility(View.GONE);
        editLastNameButton.setVisibility(View.GONE);
        editGenderButton.setVisibility(visibility);
        editPasswordButton.setVisibility(View.GONE);
    }

    private void setEditPasswordButtonVisibility(int visibility) {
        editButton.setVisibility(View.GONE);
        editFirstNameButton.setVisibility(View.GONE);
        editLastNameButton.setVisibility(View.GONE);
        editGenderButton.setVisibility(View.GONE);
        editPasswordButton.setVisibility(visibility);
    }

    public void showEditPhone() {
        // Toggle visibility of the edit phone elements
        int visibility = (editPhone.getVisibility() == View.VISIBLE) ? View.GONE : View.VISIBLE;
        editPhone.setVisibility(visibility);
        savePhoneButton.setVisibility(visibility);
    }

    public void savePhone(String email) {
        String newPhone = editPhone.getText().toString().trim();
        if (!newPhone.isEmpty() && newPhone.length() == 10) {
            dataBaseCustomerProfile.updatePhone(email,newPhone);
            viewPhone.setText(newPhone);
            editPhone.setVisibility(View.GONE);
            savePhoneButton.setVisibility(View.GONE);
        } else {
            Toast.makeText(this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
        }
    }

    public void showEditEmail() {
        int visibility = (editEmail.getVisibility() == View.VISIBLE) ? View.GONE : View.VISIBLE;
        editEmail.setVisibility(visibility);
        saveEmailButton.setVisibility(visibility);
        if (visibility == View.GONE) {
            editEmail.setText("");
        } else {
            editEmail.setText(viewEmail.getText().toString());
        }
    }

    public void saveEmail(String email) {
        String newEmail = editEmail.getText().toString().trim();

        if (!newEmail.isEmpty()) {
            dataBaseCustomerProfile.updateEmail(email,newEmail);
            viewEmail.setText(newEmail);
            editEmail.setVisibility(View.GONE);
            saveEmailButton.setVisibility(View.GONE);
        } else {

            Toast.makeText(this, "Please enter a valid Email address", Toast.LENGTH_SHORT).show();
        }
    }

    public void showEditCountry() {
        int visibility = (edit_country_Spinner.getVisibility() == View.VISIBLE) ? View.GONE : View.VISIBLE;
        edit_country_Spinner.setVisibility(visibility);
        saveCountryButton.setVisibility(visibility);
        int visibility1 = (edit_city_Spinner.getVisibility() == View.VISIBLE) ? View.GONE : View.VISIBLE;
        edit_city_Spinner.setVisibility(visibility1);
        saveCityButton.setVisibility(visibility1);
        String [] countryOptions = {"Select Country" , "Palestine" , "Egypt", "Syria", "Jordan"};
        ArrayAdapter<String> obCountryArray = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, countryOptions);
        edit_country_Spinner.setAdapter(obCountryArray);
    }

    public void saveCountry(String email) {
        String [] palestine = {"Select City" , "Ramallah" , "Nablus","Hebron"};
        String [] jordan = {"Select City" , "Amman" , "Irbid","Aqaba"};
        String [] egypt = {"Select City" , "Alexandria" , "Cairo","Luxor"};
        String []  syria = {"Select City" , "Damascus" , "Aleppo","Homs"};

        String newCountry = edit_country_Spinner.getSelectedItem().toString().trim();
        if (!newCountry.isEmpty()) {
            dataBaseCustomerProfile.updateCountry(email,newCountry);
            viewCountry.setText(newCountry);
            edit_country_Spinner.setVisibility(View.GONE);
            saveCountryButton.setVisibility(View.GONE);

            if (newCountry.equals("Palestine")) {
                ArrayAdapter<String> obCityArray = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, palestine);
                obCityArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                edit_city_Spinner.setAdapter(obCityArray);
                editPhone.setText("00970");
            }else if (newCountry.equals("Egypt")){
                ArrayAdapter<String> obCityArray = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,egypt);
                obCityArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                edit_city_Spinner.setAdapter(obCityArray);
                editPhone.setText("0020");
            }else if (newCountry.equals("Syria")){
                ArrayAdapter<String> obCityArray = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,syria);
                obCityArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                edit_city_Spinner.setAdapter(obCityArray);
                editPhone.setText("00963");

            }else if (newCountry.equals("Jordan")) {
                ArrayAdapter<String> obCityArray = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, jordan);
                obCityArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                edit_city_Spinner.setAdapter(obCityArray);
                editPhone.setText("00962");
            }
        } else {
            Toast.makeText(this, "Please enter a valid Country", Toast.LENGTH_SHORT).show();
        }
    }


    public void saveCity(String email) {
        String newCity = edit_city_Spinner.getSelectedItem().toString().trim();
        if (!newCity.isEmpty()) {
            dataBaseCustomerProfile.updateCity(email,newCity);
            viewCity.setText(newCity);
            edit_city_Spinner.setVisibility(View.GONE);
            saveCityButton.setVisibility(View.GONE);
        } else {
            Toast.makeText(this, "Please enter a valid city", Toast.LENGTH_SHORT).show();
        }
    }


    public void showEditFirstName() {
        int visibility = (editFirstName.getVisibility() == View.VISIBLE) ? View.GONE : View.VISIBLE;
        editFirstName.setVisibility(visibility);
        saveFirstNameButton.setVisibility(visibility);

        if (visibility == View.GONE) {
            editFirstName.setText("");
        } else {
            editFirstName.setText(firstNameTextView.getText().toString());
        }
    }

    public void saveFirstName(String email) {
        String newFirstName = editFirstName.getText().toString().trim();
        if (!newFirstName.isEmpty()) {
            dataBaseCustomerProfile.updateFirstName(email,newFirstName);
            firstNameTextView.setText(newFirstName);
            editFirstName.setVisibility(View.GONE);
            saveFirstNameButton.setVisibility(View.GONE);
        } else {
            Toast.makeText(this, "Please enter a valid First Name", Toast.LENGTH_SHORT).show();
        }
    }

    public void showEditLastName() {
        int visibility = (editLastName.getVisibility() == View.VISIBLE) ? View.GONE : View.VISIBLE;
        editLastName.setVisibility(visibility);
        saveLastNameButton.setVisibility(visibility);

        if (visibility == View.GONE) {
            editLastName.setText("");
        } else {
            editLastName.setText(lastNameTextView.getText().toString());
        }
    }

    public void saveLastName(String email) {
        String newLastName = editLastName.getText().toString().trim();
        if (!newLastName.isEmpty()) {
            dataBaseCustomerProfile.updateLastName(email,newLastName);
            lastNameTextView.setText(newLastName);
            editLastName.setVisibility(View.GONE);
            saveLastNameButton.setVisibility(View.GONE);
        } else {
            Toast.makeText(this, "Please enter a valid last name", Toast.LENGTH_SHORT).show();
        }
    }

    public void showEditGender() {
        int visibility = (edit_gender_Spinner.getVisibility() == View.VISIBLE) ? View.GONE : View.VISIBLE;
        edit_gender_Spinner.setVisibility(visibility);
        saveGenderButton.setVisibility(visibility);
        String [] genderOptions = {"Select Gender" , "Male" , "Female"};
        ArrayAdapter<String> objGenderArray = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, genderOptions);
        edit_gender_Spinner.setAdapter(objGenderArray);
    }

    public void saveGender(String email) {
        String newGender = edit_gender_Spinner.getSelectedItem().toString().trim();
        if (!newGender.isEmpty()) {
            dataBaseCustomerProfile.updateGender(email,newGender);
            genderTextView.setText(newGender);
            edit_gender_Spinner.setVisibility(View.GONE);
            saveGenderButton.setVisibility(View.GONE);
        } else {
            Toast.makeText(this, "Please enter a valid Gender", Toast.LENGTH_SHORT).show();
        }
    }

    public void showEditPassword() {
        int visibility = (editPassword.getVisibility() == View.VISIBLE) ? View.GONE : View.VISIBLE;
        editPassword.setVisibility(visibility);
        savePasswordButton.setVisibility(visibility);

        if (visibility == View.GONE) {
            editPassword.setText("");
        } else {
            editPassword.setText(passwordTextView.getText().toString());
        }
    }

    public void savePassword(String email) {
        String newPassword = editPassword.getText().toString().trim();
        if (!newPassword.isEmpty()) {
            dataBaseCustomerProfile.updatePassword(email,newPassword);
            passwordTextView.setText(newPassword);
            editPassword.setVisibility(View.GONE);
            savePasswordButton.setVisibility(View.GONE);
        } else {
            Toast.makeText(this, "Please enter a valid password", Toast.LENGTH_SHORT).show();
        }
    }

    public void onBackButtonClick(View view) {
        onBackPressed();
    }

}