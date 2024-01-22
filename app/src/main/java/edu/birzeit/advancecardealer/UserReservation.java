package edu.birzeit.advancecardealer;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class UserReservation extends AppCompatActivity {
    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reservation);
        sharedPrefManager = SharedPrefManager.getInstance(this);
        DataBaseHelper dataReserves = new DataBaseHelper(UserReservation.this, "CarsDatabase", null, 1);
        Cursor ReservedCursor = dataReserves.getUserReservation(sharedPrefManager.readString("currentUserEmail",""));

            LinearLayout nameLayout = findViewById(R.id.Car_Name);
            LinearLayout ReserveLayout = findViewById(R.id.ReserveDate);
            LinearLayout ReturnLayout = findViewById(R.id.ReturnDate);
            LinearLayout row = findViewById(R.id.row);
            ScrollView main = findViewById(R.id.main);
            nameLayout.removeAllViews();
            ReserveLayout.removeAllViews();
            ReturnLayout.removeAllViews();
            int i =0;

            TextView namelabel = createTextView("Car Name",i);
            TextView Reservedlabel = createTextView("Reservation Date",i);
            TextView datelabel = createTextView(" Returned Date",i);
            nameLayout.addView(namelabel);
            ReserveLayout.addView(Reservedlabel);
            ReturnLayout.addView(datelabel);
            i++;
            while (ReservedCursor.moveToNext()) {


                layoutDesign(ReservedCursor, nameLayout, ReserveLayout, ReturnLayout, i);
                i++;
            }


            if(ReservedCursor.getCount() == 0){
                main.removeAllViews();
                ImageView imageView = new ImageView(UserReservation.this);
                // Set the image resource or other properties
                imageView.setImageResource(R.drawable.no_data);
                // Set top margin for the ImageView
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                layoutParams.topMargin = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        100,
                        getResources().getDisplayMetrics()
                );
                layoutParams.rightMargin= (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        50,
                        getResources().getDisplayMetrics()
                );
                imageView.setLayoutParams(layoutParams);
                main.addView(imageView);
            }

        }





    public void layoutDesign(Cursor adminCursor,LinearLayout name,LinearLayout model,LinearLayout date,int i){
        TextView nameText = createTextView(adminCursor.getString(adminCursor.getColumnIndex("NAME")),i);
        TextView dateText = createTextView(adminCursor.getString(adminCursor.getColumnIndex("RETURNED_DATE")),i);
        TextView modelText = createTextView(adminCursor.getString(adminCursor.getColumnIndex("DATE")) + " \n" + adminCursor.getString(adminCursor.getColumnIndex("TIME")),i);

        name.addView(nameText);
        model.addView(modelText);
        date.addView(dateText);

    }
    private TextView createTextView(String text,int i) {

        int color = (i % 2 == 0) ? Color.parseColor("#FFFFFFFF"): Color.parseColor("#41D3D0D0");

        TextView textView = new TextView(UserReservation.this);
        textView.setBackgroundColor(color);

        // Set layout parameters with height set to 50dp
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics()), // Set height to 50dp
                1.0f
        );
        textView.setLayoutParams(layoutParams);

        // Set other properties
        //textView.setPadding(0, 10, 0, 10);
        textView.setText(text);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(16);
        textView.setTypeface(null, Typeface.BOLD);

        return textView;
    }
    public void onBackButtonClick(View view) {
        // Handle the back button click
        onBackPressed();
    }
}