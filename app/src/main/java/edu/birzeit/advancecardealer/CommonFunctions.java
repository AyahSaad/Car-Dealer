package edu.birzeit.advancecardealer;



import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonFunctions {




    public static String formattedDate(Date date) {
        // Create a SimpleDateFormat instance with the desired pattern
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);


    }
    public static String formattedTime(Date date) {
        // Create a SimpleDateFormat instance with the desired pattern
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(date);


    }
    // Method to convert a string in the format "yyyy/MM/dd" to a Date object
    public static Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(dateString);
    }



    public static boolean isValidDateFormat(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false); // Disable lenient parsing

        try {
            // Try to parse the date without leniency
            Date parsedDate = dateFormat.parse(dateString);

            // Check if the parsed date is not null (indicating successful parsing)
            return parsedDate != null;
        } catch (ParseException e) {
            // Parsing failed, indicating an invalid date format
            return false;
        }
    }


}

