package edu.birzeit.advancecardealer.admin;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import edu.birzeit.advancecardealer.DataBaseHelper;
import edu.birzeit.advancecardealer.R;

public class DeleteCustomersActivity extends AppCompatActivity {
    private static final String Toast_Text1 = "Please Enter the Customer Details";
    DataBaseHelper dataBaseDeleteCustomer = new DataBaseHelper(DeleteCustomersActivity.this, "CarsDatabase", null, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_customers);
        LinearLayout DeleteCustomerLayout = findViewById(R.id.DeleteCustomerLayout);
        EditText First_name = findViewById(R.id.First_Name);
        EditText Last_Name = findViewById(R.id.Last_Name);
        Button Search = findViewById(R.id.Search);
        SearchButtonListener(Search, First_name, Last_Name, DeleteCustomerLayout);
    }
    private void SearchButtonListener(Button Search, EditText First_Name, EditText Last_Name, LinearLayout DeleteCustomerLayout) {
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteCustomerLayout.removeAllViews();
                if (First_Name.getText().toString().isEmpty() && Last_Name.getText().toString().isEmpty()) {
                    Toast.makeText(DeleteCustomersActivity.this, Toast_Text1, Toast.LENGTH_SHORT).show();
                } else {
                    Cursor customerCursor;
                    if (!First_Name.getText().toString().isEmpty() && !Last_Name.getText().toString().isEmpty()) {
                        // Search by both first name and last name
                        customerCursor = dataBaseDeleteCustomer.getCustomerByFirstNameAndLastName(First_Name.getText().toString(), Last_Name.getText().toString());
                    } else if (!First_Name.getText().toString().isEmpty()) {
                        // Search by first name only
                        customerCursor = dataBaseDeleteCustomer.getCustomerByFirstName(First_Name.getText().toString());
                    } else {
                        // Search by last name only
                        customerCursor = dataBaseDeleteCustomer.getCustomerByLastName(Last_Name.getText().toString());
                    }
                    if (customerCursor.getCount() > 0) {
                        while (customerCursor.moveToNext()) {
                            DeleteCustomerLayout.addView(layoutDesign(customerCursor, Search, First_Name, Last_Name, DeleteCustomerLayout));
                        }
                    } else {
                        Toast.makeText(DeleteCustomersActivity.this, "No matching data found", Toast.LENGTH_SHORT).show();
                    }
                    customerCursor.close();
                }
            }
        });
    }

    public LinearLayout layoutDesign(Cursor customerCursor,Button Search, EditText First_Name, EditText Last_Name, LinearLayout DeleteCustomerLayout) {
        LinearLayout colLayout = new LinearLayout(DeleteCustomersActivity.this);
        colLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout rowLayout = new LinearLayout(DeleteCustomersActivity.this);
        TextView firstNameTextView = new TextView(DeleteCustomersActivity.this);
        TextView lastNameTextView = new TextView(DeleteCustomersActivity.this);
        TextView emailTextView = new TextView(DeleteCustomersActivity.this);
        TextView phoneTextView = new TextView(DeleteCustomersActivity.this);
        TextView locationTextView = new TextView(DeleteCustomersActivity.this);
        TextView TypeTextView = new TextView(DeleteCustomersActivity.this);

        Button DeleteButton = new Button(DeleteCustomersActivity.this);
        DeleteButton.setTextSize(16);
        DeleteButton.setText("Delete");
        DeleteButton.setWidth(150);
        DeleteButton.setHeight(80);
        DeleteButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        DeleteButton.setBackgroundColor(Color.parseColor("#6200EE"));
        DeleteButton.setTextColor(Color.WHITE);
        DeleteButton.setTypeface(null, Typeface.BOLD);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        // Customize the layout parameters as needed
        layoutParams.setMargins(70, 70, 150, 0);
        DeleteButton.setLayoutParams(layoutParams);

        // Get data from the cursor
        if (!customerCursor.isNull(customerCursor.getColumnIndex("FIRST_NAME"))) {
            String firstName = customerCursor.getString(customerCursor.getColumnIndex("FIRST_NAME"));
            String lastName = customerCursor.getString(customerCursor.getColumnIndex("LAST_NAME"));
            String email = customerCursor.getString(customerCursor.getColumnIndex("EMAIL"));
            String phone = customerCursor.getString(customerCursor.getColumnIndex("PHONE"));
            String country = customerCursor.getString(customerCursor.getColumnIndex("COUNTRY"));
            String city = customerCursor.getString(customerCursor.getColumnIndex("CITY"));
            String Type = customerCursor.getString(customerCursor.getColumnIndex("TYPE"));

            if (Type.equals("User")){
                // Set text for TextViews based on the retrieved data
                firstNameTextView.setText("First Name: " + firstName);
                lastNameTextView.setText("Last Name: " + lastName);
                emailTextView.setText("Email: " + email);
                phoneTextView.setText("Phone: " + phone);
                locationTextView.setText("Location: " + country + ", " + city);
                TypeTextView.setText("Type: " + Type);

            rowLayout.setTag(email);
            DeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    handleDeleteButtonClick(email,DeleteCustomerLayout,rowLayout);
                }
            });
            // Add TextViews and Button to the layout
            colLayout.addView(firstNameTextView);
            colLayout.addView(lastNameTextView);
            colLayout.addView(emailTextView);
            colLayout.addView(phoneTextView);
            colLayout.addView(locationTextView);
            rowLayout.addView(colLayout);
            rowLayout.addView(DeleteButton);
            }
        }
        return rowLayout;
    }

    private void handleDeleteButtonClick(String email, LinearLayout DeleteCustomerLayout, LinearLayout rowLayout) {
        boolean isDeleted = dataBaseDeleteCustomer.deleteCustomer(email);
        if (isDeleted) {
            Toast.makeText(DeleteCustomersActivity.this, "Customer Deleted Successfully", Toast.LENGTH_SHORT).show();
            // Remove the deleted customer's view from the layout
            DeleteCustomerLayout.removeView(rowLayout);
        } else {
            Toast.makeText(DeleteCustomersActivity.this, "Failed to delete customer", Toast.LENGTH_SHORT).show();
        }
    }

    public void onBackButtonClick(View view) {
        // Handle the back button click
        onBackPressed();
    }
}
