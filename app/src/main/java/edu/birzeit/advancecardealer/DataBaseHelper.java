package edu.birzeit.advancecardealer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;
import androidx.annotation.Nullable;


public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE USERS(EMAIL TEXT PRIMARY KEY, FIRST_NAME TEXT, LAST_NAME TEXT, PASSWORD TEXT, GENDER TEXT, COUNTRY TEXT, CITY TEXT, TYPE TEXT, PHONE TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE CARS(ID INTEGER PRIMARY KEY AUTOINCREMENT, FACTORY_NAME TEXT, TYPE TEXT, PRICE LONG, MODEL TEXT,NAME TEXT,OFFER LONG,YEAR TEXT,FUEL_TYPE TEXT, RATING LONG,ACCIDENT TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE RESERVE(ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, TIME TEXT, USER_EMAIL TEXT, CAR_ID INTEGER,FOREIGN KEY (USER_EMAIL) REFERENCES USERS(EMAIL),FOREIGN KEY (CAR_ID) REFERENCES CARS(ID))");
        sqLiteDatabase.execSQL("CREATE TABLE FAVORITE(ID INTEGER PRIMARY KEY AUTOINCREMENT,USER_EMAIL TEXT,CAR_ID INTEGER,FOREIGN KEY (USER_EMAIL) REFERENCES USERS(EMAIL),FOREIGN KEY (CAR_ID) REFERENCES CARS(ID))");
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

    public  void insertCar(Car car){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FACTORY_NAME",car.getFactoryName());
        contentValues.put("ID",car.getId());
        contentValues.put("TYPE",car.getType());
        contentValues.put("PRICE",car.getPrice());
        contentValues.put("MODEL",car.getModel());
        contentValues.put("ACCIDENT",car.getAccident());
        contentValues.put("RATING",car.getRating());
        contentValues.put("FUEL_TYPE",car.getFuelType());
        contentValues.put("YEAR",car.getYear());
        contentValues.put("OFFER",car.getOffer());
        contentValues.put("NAME",car.getModel());
        sqLiteDatabase.insert("CARS", null, contentValues);
    }

    public void insertFav(Favorite favorite){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",favorite.getId());
        contentValues.put("USER_EMAIL",favorite.getUseremail());
        contentValues.put("CAR_ID",favorite.getCarId());
        sqLiteDatabase.insert("FAVORITE", null, contentValues);
    }

    public void insertReservation(Reserve reserve){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",reserve.getId());
        contentValues.put("DATE",reserve.getDate());
        contentValues.put("TIME",reserve.getTime());
        contentValues.put("USER_EMAIL",reserve.getEmail());
        contentValues.put("CAR_ID",reserve.getCarId());
        sqLiteDatabase.insert("RESERVE", null, contentValues);
    }

    public Cursor getUsers(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM USERS", null);
    }

    public Cursor getType(String email){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM USERS WHERE '"+email+"' = EMAIL", null);
    }

    public Cursor getCar(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM CARS", null);
    }
    public Cursor getReservation(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT  USERS.*, CARS.*, RESERVE.* FROM  USERS JOIN RESERVE ON USERS.EMAIL = RESERVE.USER_EMAIL JOIN CARS ON CARS.ID = RESERVE.CAR_ID", null);
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

    public Cursor getCustomerByFirstNameAndLastName(String firstName, String lastName) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String selection = "FIRST_NAME = ? AND LAST_NAME = ?";
        String[] selectionArgs = {firstName, lastName};
        return sqLiteDatabase.query("USERS", null, selection, selectionArgs, null, null, null);
    }

    public Cursor getCustomerByFirstName(String firstName) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String selection = "FIRST_NAME = ?";
        String[] selectionArgs = {firstName};
        return sqLiteDatabase.query("USERS", null, selection, selectionArgs, null, null, null);
    }

    public Cursor getCustomerByLastName(String lastName) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String selection = "LAST_NAME = ?";
        String[] selectionArgs = {lastName};
        return sqLiteDatabase.query("USERS", null, selection, selectionArgs, null, null, null);
    }

    public boolean deleteCustomer(String email) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClause = "EMAIL = ?";
        String[] whereArgs = {email};
        int rowsDeleted = sqLiteDatabase.delete("USERS", whereClause, whereArgs);
        return rowsDeleted > 0;
    }
}
