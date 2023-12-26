package edu.birzeit.advancecardealer.user;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Layout;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.birzeit.advancecardealer.DataBaseHelper;
import edu.birzeit.advancecardealer.MainActivity;
import edu.birzeit.advancecardealer.R;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        LinearLayout layout = findViewById(R.id.layout);
        layout.removeAllViews();
        DataBaseHelper dataBaseHome = new DataBaseHelper(HomePage.this, "CarsDatabase", null, 1);
        Cursor cursor = dataBaseHome.getReservation();
        while (cursor.moveToNext()){

                TextView textView =new TextView(HomePage.this);
                textView.setText(
                        "Id= "+cursor.getInt(cursor.getColumnIndex("ID"))
                                +"\ndate= "+cursor.getString(cursor.getColumnIndex("DATE"))
                                +"\ntime= "+cursor.getString(cursor.getColumnIndex("TIME"))
                                +"\nName= "+cursor.getString(cursor.getColumnIndex("FIRST_NAME")) + " "+ cursor.getString(cursor.getColumnIndex("LAST_NAME"))
                                +"\ndate= "+cursor.getString(cursor.getColumnIndex("DATE")) + " " + cursor.getString(cursor.getColumnIndex("TIME"))
                                +"\n\n"
                );
                layout.addView(textView);
        }



    }
}