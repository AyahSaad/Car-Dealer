package edu.birzeit.advancecardealer.user;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.imageview.ShapeableImageView;
import edu.birzeit.advancecardealer.DataBaseHelper;
import edu.birzeit.advancecardealer.R;


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
    private EditText editPhone;
    private Button savePhoneButton;
    private EditText editEmail;
    private Button saveEmailButton;
    private EditText editCountry;
    private Button saveCountryButton;
    private EditText editCity;
    private Button saveCityButton;

    private static final int PICK_IMAGE_REQUEST = 1;

    DataBaseHelper dataBaseCustomerProfile = new DataBaseHelper(CustomerProfile.this, "CarsDatabase", null, 1);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);
        editButton = findViewById(R.id.editButton);
        scrollView = findViewById(R.id.scrollView);
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

        ////////////////new
        // Initialize EditText and Save Phone button
        editPhone = findViewById(R.id.editPhone);
        savePhoneButton = findViewById(R.id.savePhoneButton);
        editEmail = findViewById(R.id.editEmail);
        saveEmailButton = findViewById(R.id.saveEmailButton);
        editCountry = findViewById(R.id.editCountry);
        saveCountryButton = findViewById(R.id.saveCountryButton);
        editCity = findViewById(R.id.editCity);
        saveCityButton = findViewById(R.id.saveCityButton);

        Intent intent = getIntent();
         String currentUser = intent.getStringExtra("email");
         Cursor cursorUser = dataBaseCustomerProfile.getEmail(currentUser);

         while (cursorUser.moveToNext()){

             viewPhone.setText(cursorUser.getString(cursorUser.getColumnIndex("PHONE")));

         }






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
                saveEmail();
            }
        });

        editLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditCountry();
                showEditCity();
            }
        });

        saveCountryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCountry();
            }
        });

        saveCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCity();
            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////////////
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

        // Clear the previous input when the elements are hidden
        if (visibility == View.GONE) {
                editPhone.setText("");

        } else {
            // Set the current phone number as the default value for editing

                editPhone.setText(viewPhone.getText().toString());


        }
    }

    public void savePhone(String email) {
        String newPhone = editPhone.getText().toString().trim();

        if (!newPhone.isEmpty() && newPhone.length() == 10) {
            //TODO: ADD NEW CHECK IF newPhone < 10 and add Toast Message
            // Update the phone number in the database
            dataBaseCustomerProfile.updatePhone(email,newPhone);

            // Update the TextView displaying the phone number
            viewPhone.setText(newPhone);

            // Hide the edit elements after saving
            editPhone.setVisibility(View.GONE);
            savePhoneButton.setVisibility(View.GONE);
        } else {
            // Handle empty phone number case
            // You can show a Toast or display an error message
            Toast.makeText(this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
        }
    }




    public void showEditEmail() {
        // Toggle visibility of the edit email elements
        int visibility = (editEmail.getVisibility() == View.VISIBLE) ? View.GONE : View.VISIBLE;
        editEmail.setVisibility(visibility);
        saveEmailButton.setVisibility(visibility);

        // Clear the previous input when the elements are hidden
        if (visibility == View.GONE) {
            editEmail.setText("");
        } else {
            // Set the current email as the default value for editing
            editEmail.setText(viewEmail.getText().toString());
        }
    }

    public void saveEmail() {
        String newEmail = editEmail.getText().toString().trim();

        if (!newEmail.isEmpty()) {
            // Update the email in the database
            updateEmail(newEmail);

            // Update the TextView displaying the email
            viewEmail.setText(newEmail);

            // Hide the edit elements after saving
            editEmail.setVisibility(View.GONE);
            saveEmailButton.setVisibility(View.GONE);
        } else {

            Toast.makeText(this, "Please enter a valid Email address", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateEmail(String newEmail) {

    }

    public void showEditCountry() {
        int visibility = (editCountry.getVisibility() == View.VISIBLE) ? View.GONE : View.VISIBLE;
        editCountry.setVisibility(visibility);
        saveCountryButton.setVisibility(visibility);

        if (visibility == View.GONE) {
            editCountry.setText("");
        } else {
            editCountry.setText(viewCountry.getText().toString());
        }
    }

    public void saveCountry() {
        String newCountry = editCountry.getText().toString().trim();
        if (!newCountry.isEmpty()) {
            updateCountry(newCountry);
            viewPhone.setText(newCountry);
            editCountry.setVisibility(View.GONE);
            saveCountryButton.setVisibility(View.GONE);
        } else {
            Toast.makeText(this, "Please enter a valid Country", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateCountry(String newCountry) {

    }


    public void showEditCity() {
        // Toggle visibility of the edit phone elements
        int visibility = (editCity.getVisibility() == View.VISIBLE) ? View.GONE : View.VISIBLE;
        editCity.setVisibility(visibility);
        saveCityButton.setVisibility(visibility);

        // Clear the previous input when the elements are hidden
        if (visibility == View.GONE) {
            editCity.setText("");
        } else {
            // Set the current phone number as the default value for editing
            editCity.setText(viewCity.getText().toString());
        }
    }

    public void saveCity() {
        String newCity = editCity.getText().toString().trim();
        if (!newCity.isEmpty()) {
           // update(newCity);
            viewCity.setText(newCity);
            editCity.setVisibility(View.GONE);
            saveCityButton.setVisibility(View.GONE);
        } else {
            Toast.makeText(this, "Please enter a valid city", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateCity(String newCity) {

    }

    public void onBackButtonClick(View view) {
        // Handle the back button click
        onBackPressed();
    }

}