package edu.birzeit.advancecardealer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE USERS(EMAIL TEXT PRIMARY KEY, FIRST_NAME TEXT, LAST_NAME TEXT, PASSWORD TEXT, GENDER TEXT, COUNTRY TEXT, CITY TEXT, TYPE TEXT, PHONE TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE CARS(ID INTEGER PRIMARY KEY AUTOINCREMENT, FACTORY_NAME TEXT, TYPE TEXT, PRICE LONG, MODEL TEXT,NAME TEXT,OFFER LONG,YEAR TEXT,FUEL_TYPE TEXT, RATING LONG,ACCIDENT TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE RESERVE(ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, TIME TEXT, USER_ID INTEGER, CAR_ID INTEGER)");
        sqLiteDatabase.execSQL("CREATE TABLE FAVORITE(ID INTEGER PRIMARY KEY AUTOINCREMENT, USER_ID INTEGER,CAR_ID INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertUser(User user) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FIRST_NAME", user.getFirstName());
        contentValues.put("LAST_NAME",user.getLastName());
        contentValues.put("EMAIL",user.getEmail());
        contentValues.put("PASSWORD",user.getPassword());
        contentValues.put("GENDER",user.getGender());
        contentValues.put("COUNTRY",user.getCountry());
        contentValues.put("CITY",user.getCity());
        contentValues.put("TYPE",user.getType());
        contentValues.put("PHONE",user.getPhone());
        sqLiteDatabase.insert("USERS", null, contentValues);

    }

    public Cursor getUsers(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM USERS", null);
    }

    public Cursor getType(String email){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM USERS WHERE '"+email+"' = EMAIL", null);
    }
    public boolean verifyLogin(String email, String password) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String[] columns = {"EMAIL", "PASSWORD"};
        String selection = "EMAIL = ? AND PASSWORD = ?";
        String[] selectionArgs = {email, password};

       /* The result of this query is stored in a Cursor object.*/
        Cursor cursor = sqLiteDatabase.query("USERS", columns, selection, selectionArgs, null, null, null);

        /* This method moves the cursor to the first row of the result set.
        If the result set is not empty (i.e., there is at least one row matching the criteria),
        this method returns true, indicating a valid login */
        boolean isValidLogin = cursor.moveToFirst(); // Move to the first row of the result

        cursor.close();
        return isValidLogin;
        }
}
