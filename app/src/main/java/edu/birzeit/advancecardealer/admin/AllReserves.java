package edu.birzeit.advancecardealer.admin;

import static android.view.View.TEXT_ALIGNMENT_CENTER;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.database.CursorWindowCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.birzeit.advancecardealer.DataBaseHelper;
import edu.birzeit.advancecardealer.MainActivity;
import edu.birzeit.advancecardealer.R;

public class AllReserves extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_reserves);
        Intent emailIntent = getIntent();
        String currentUser = emailIntent.getStringExtra("email");
        DataBaseHelper dataBaseadminReserves = new DataBaseHelper(AllReserves.this, "CarsDatabase", null, 1);
        Cursor adminCursor;
        if (currentUser.equals("Admin")){
            adminCursor = dataBaseadminReserves.getReservation();
        }else{
            adminCursor = dataBaseadminReserves.getUserReservation(currentUser);
        }

        LinearLayout nameLayout = findViewById(R.id.name);
        LinearLayout modelLayout = findViewById(R.id.carModel);
        LinearLayout dateLayout = findViewById(R.id.date);
        LinearLayout row = findViewById(R.id.row);
        ScrollView main = findViewById(R.id.main);
        nameLayout.removeAllViews();
        modelLayout.removeAllViews();
        dateLayout.removeAllViews();
        int i =0;

        TextView namelabel = createTextView("Customer Name",i);
        TextView modellabel = createTextView("Car Model",i);
        TextView datelabel = createTextView("Date",i);
        nameLayout.addView(namelabel);
        modelLayout.addView(modellabel);
        dateLayout.addView(datelabel);
        i++;

            while (adminCursor.moveToNext()) {


                layoutDesign(adminCursor, nameLayout, modelLayout, dateLayout, i);
                i++;
            }


            if(i == 1){
                main.removeAllViews();
                ImageView imageView = new ImageView(AllReserves.this);
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
        int color = (i % 2 == 0) ? Color.parseColor("#FFFFFFFF"): Color.parseColor("#41D3D0D0");
        TextView nameText = createTextView(adminCursor.getString(adminCursor.getColumnIndex("FIRST_NAME")) + " " + adminCursor.getString(adminCursor.getColumnIndex("LAST_NAME")),i);
        TextView modelText = createTextView(adminCursor.getString(adminCursor.getColumnIndex("MODEL")),i);
        TextView dateText = createTextView(adminCursor.getString(adminCursor.getColumnIndex("DATE")) + " \n" + adminCursor.getString(adminCursor.getColumnIndex("TIME")),i);

        name.addView(nameText);
        model.addView(modelText);
        date.addView(dateText);

    }

    private TextView createTextView(String text,int i) {

        int color = (i % 2 == 0) ? Color.parseColor("#FFFFFFFF"): Color.parseColor("#41D3D0D0");

        TextView textView = new TextView(AllReserves.this);
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