package edu.birzeit.advancecardealer;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import java.util.List;

public class Notific extends AppCompatActivity {
    DataBaseHelper dataBaseNotification = new DataBaseHelper(Notific.this, "CarsDatabase", null, 1);
    private LinearLayout notificationLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notific);
        notificationLinearLayout = findViewById(R.id.NotificationLayout);
        displayNotifications();
    }

    private void displayNotifications() {
        // Get notifications from the database
        Cursor notifier = dataBaseNotification.getNotification();
        while (notifier.moveToNext()){
            CardView cardView = new CardView(this);
            cardView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            cardView.setCardElevation(8);
            cardView.setRadius(8);
            cardView.setUseCompatPadding(true);
            cardView.setContentPadding(16, 16, 16, 16);
            TextView textView = new TextView(this);
            textView.setText( notifier.getString(notifier.getColumnIndex("NOTIFICATION_TEXT")));
            textView.setTextSize(16);
//            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.BLACK);
            textView.setTypeface(null, Typeface.BOLD);
            cardView.addView(textView);
            notificationLinearLayout.addView(cardView);
        }

    }

    public void onBackButtonClick(View view) {
        onBackPressed();
    }
}