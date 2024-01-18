package edu.birzeit.advancecardealer.admin;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import edu.birzeit.advancecardealer.DataBaseHelper;
import edu.birzeit.advancecardealer.R;


public class AdminOffers extends AppCompatActivity {
    private ScrollView scrollView;
    private static final String Toast_Text1 = "Please Enter the Offer Details";
    DataBaseHelper dataBaseAddOffers = new DataBaseHelper(edu.birzeit.advancecardealer.admin.AdminOffers.this, "CarsDatabase", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_offers);
        scrollView = findViewById(R.id.scrollView);
        LinearLayout AddOfferLayout = findViewById(R.id.AddOfferLayout);
        EditText Factory_name = findViewById(R.id.Factory_Name);
        EditText Car_Name = findViewById(R.id.Car_Name);
        Button Search = findViewById(R.id.Search);
        SearchButtonListener(Search, Factory_name, Car_Name , AddOfferLayout );
    }
    private void SearchButtonListener(Button Search, EditText Factory_name, EditText Car_Name, LinearLayout AddOfferLayout ) {
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddOfferLayout.removeAllViews();
                if (Factory_name.getText().toString().isEmpty() && Car_Name.getText().toString().isEmpty()) {
                    Toast.makeText(edu.birzeit.advancecardealer.admin.AdminOffers.this, Toast_Text1, Toast.LENGTH_SHORT).show();
                } else {
                    Cursor offerCursor;
                    if (!Factory_name.getText().toString().isEmpty() && !Car_Name.getText().toString().isEmpty()) {
                        // Search by both Factory name and car name
                        offerCursor = dataBaseAddOffers.getOfferByFactoryNameAndCarName(Factory_name.getText().toString(), Car_Name.getText().toString());
                    } else if (!Factory_name.getText().toString().isEmpty()) {
                        // Search by factory name only
                        offerCursor = dataBaseAddOffers.getOfferByFactoryName(Factory_name.getText().toString());
                    } else {
                        // Search by car name only
                        offerCursor = dataBaseAddOffers.getOfferByCarName(Car_Name.getText().toString());
                    }
                    if (offerCursor.getCount() > 0) {
                        while (offerCursor.moveToNext()) {
                            AddOfferLayout.addView(layoutDesign(offerCursor, Search, Factory_name, Car_Name, AddOfferLayout));
                        }
                    } else {
                        Toast.makeText(edu.birzeit.advancecardealer.admin.AdminOffers.this, "No matching data found", Toast.LENGTH_SHORT).show();
                    }
                    offerCursor.close();
                }
            }
        });
    }

    public LinearLayout layoutDesign(Cursor offerCursor,Button Search, EditText Factory_Name, EditText Car_Name, LinearLayout  AddOfferLayout) {
        LinearLayout colLayout = new LinearLayout(edu.birzeit.advancecardealer.admin.AdminOffers.this);
        colLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout rowLayout = new LinearLayout(edu.birzeit.advancecardealer.admin.AdminOffers.this);
        TextView factoryNameTextView = new TextView(edu.birzeit.advancecardealer.admin.AdminOffers.this);
        TextView carNameTextView = new TextView(edu.birzeit.advancecardealer.admin.AdminOffers.this);
        TextView priceTextView = new TextView(edu.birzeit.advancecardealer.admin.AdminOffers.this);
        TextView offerTextView = new TextView(edu.birzeit.advancecardealer.admin.AdminOffers.this);
        EditText newOffer = new EditText(edu.birzeit.advancecardealer.admin.AdminOffers.this);
        Button SaveButton = new Button(edu.birzeit.advancecardealer.admin.AdminOffers.this);
        newOffer.setHint("Add Offer");
        newOffer.setTextSize(13);
        SaveButton.setTextSize(16);
        SaveButton.setText("Offer");
        SaveButton.setWidth(120);
        SaveButton.setHeight(60);
        SaveButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        SaveButton.setBackgroundColor(Color.parseColor("#97919e"));
        SaveButton.setTextColor(Color.WHITE);
        SaveButton.setTypeface(null, Typeface.BOLD);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        layoutParams.setMargins(70, 70, 150, 0);
        SaveButton.setLayoutParams(layoutParams);

        // Get data from the cursor
        if (!offerCursor.isNull(offerCursor.getColumnIndex("ID"))) {
            String id = offerCursor.getString(offerCursor.getColumnIndex("ID"));
            String factoryName = offerCursor.getString(offerCursor.getColumnIndex("FACTORY_NAME"));
            String carName = offerCursor.getString(offerCursor.getColumnIndex("NAME"));
            String price = offerCursor.getString(offerCursor.getColumnIndex("PRICE"));
            String offer = offerCursor.getString(offerCursor.getColumnIndex("OFFER"));

            // Set text for TextViews based on the retrieved data
            factoryNameTextView.setText("Factory Name: " + factoryName);
            carNameTextView.setText("Car Name: " + carName);
            priceTextView.setText("price: " + price);
            offerTextView.setText("Offer: " + offer);

            //todo price offer
            rowLayout.setTag(id);
                    SaveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String newoffer = newOffer.getText().toString().trim();

                            if (!newoffer.isEmpty()) {
                                // Check if the new offer format is valid (e.g., "25%")
                                if (newoffer.matches("^\\d+%$")) {
                                    try {
                                        double percentage = Double.parseDouble(newoffer.replace("%", ""));
                                        if (percentage >= 0 && percentage <= 100) {
                                            // Calculate the new offer based on the percentage
                                            double originalPrice = Double.parseDouble(price);
                                            double discount = (percentage / 100) * originalPrice;
                                            double newPrice = originalPrice - discount;

                                            // Update the database with the new offer
                                            dataBaseAddOffers.addOffer(Integer.parseInt(id), newoffer);
                                            // Update the TextView with the new offer
                                            offerTextView.setText("Offer: " + newoffer);
                                            priceTextView.setText("Price: " + newPrice);

                                            // Display a success message
                                            Toast.makeText(edu.birzeit.advancecardealer.admin.AdminOffers.this, "Offer Added Successfully", Toast.LENGTH_SHORT).show();
                                        } else {
                                            // Display an error message for an invalid percentage
                                            Toast.makeText(edu.birzeit.advancecardealer.admin.AdminOffers.this, "Invalid Percentage", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (NumberFormatException e) {
                                        // Display an error message for an invalid number format
                                        Toast.makeText(edu.birzeit.advancecardealer.admin.AdminOffers.this, "Invalid Number Format", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    // Display an error message for an invalid new offer format
                                    Toast.makeText(edu.birzeit.advancecardealer.admin.AdminOffers.this, "Invalid New Offer Format. Please use the format XX%", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                // Display an error message for an empty new offer
                                Toast.makeText(edu.birzeit.advancecardealer.admin.AdminOffers.this, "Failed to Add Offer", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

            // Add TextViews, edit and Button to the layout
            colLayout.addView(factoryNameTextView);
            colLayout.addView(carNameTextView);
            colLayout.addView(priceTextView);
            colLayout.addView(offerTextView);
            colLayout.addView(newOffer);
            rowLayout.addView(colLayout);
            rowLayout.addView(SaveButton);
        }
        return rowLayout;
    }

    public void onBackButtonClick(View view) {
        // Handle the back button click
        onBackPressed();
    }
}