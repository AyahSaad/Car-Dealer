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
        sqLiteDatabase.execSQL("CREATE TABLE USERS(EMAIL TEXT PRIMARY KEY, FIRST_NAME TEXT, LAST_NAME TEXT, PASSWORD TEXT, GENDER TEXT, COUNTRY TEXT, CITY TEXT, TYPE TEXT, PHONE TEXT,IMAGE TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE CARS(ID INTEGER PRIMARY KEY AUTOINCREMENT, FACTORY_NAME TEXT, TYPE TEXT, PRICE REAL, MODEL TEXT,NAME TEXT,OFFER REAL,YEAR TEXT,FUEL_TYPE TEXT, RATING REAL,ACCIDENT TEXT,IMAGE TEXT,SPARE TEXT,COLOR TEXT,DOORS INTEGER,COMPANY TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE RESERVE(ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, TIME TEXT, USER_EMAIL TEXT, CAR_ID INTEGER,FOREIGN KEY (USER_EMAIL) REFERENCES USERS(EMAIL),FOREIGN KEY (CAR_ID) REFERENCES CARS(ID))");
        sqLiteDatabase.execSQL("CREATE TABLE FAVORITE(ID INTEGER PRIMARY KEY AUTOINCREMENT,USER_EMAIL TEXT,CAR_ID INTEGER,FOREIGN KEY (USER_EMAIL) REFERENCES USERS(EMAIL),FOREIGN KEY (CAR_ID) REFERENCES CARS(ID))");
        sqLiteDatabase.execSQL("CREATE TABLE ADMIN(ID INTEGER PRIMARY KEY AUTOINCREMENT,ADMIN_EMAIL TEXT,COMPANY TEXT,FOREIGN KEY (ADMIN_EMAIL) REFERENCES USERS(EMAIL))");
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
        contentValues.put("IMAGE",user.getImage());
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
        contentValues.put("NAME",car.getName());
        contentValues.put("SPARE",car.getHasAspare());
        contentValues.put("COLOR",car.getColor());
        contentValues.put("DOORS",car.getDoorsCount());
        contentValues.put("IMAGE",car.getImage());
        contentValues.put("COMPANY",car.getCompany());
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

    public  void insertImage(byte [] image){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("IMAGE",image);
        sqLiteDatabase.insert("PROFILEIMAGE",null,contentValues);
    }


    public boolean verifyLogin(String email, String password) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String[] columns = {"EMAIL", "PASSWORD"};
        String selection = "EMAIL = ? AND PASSWORD = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = sqLiteDatabase.query("USERS", columns, selection, selectionArgs, null, null, null);
        boolean isValidLogin = cursor.moveToFirst();
        cursor.close();
        return isValidLogin;
    }
    public Cursor getUser(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM USERS", null);
    }
    public Cursor getUsersDelete(String firstName){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM USERS WHERE '"+firstName+"' = FIRST_NAME", null);
    }
    public Cursor getProfile(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM PROFILEIMAGE", null);

    }
    public Cursor getEmail(String Email){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM USERS WHERE '"+Email+"' = EMAIL", null);
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
    public Cursor getUserReservation(String email){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT  USERS.*, CARS.*, RESERVE.* FROM  USERS JOIN RESERVE ON USERS.EMAIL = RESERVE.USER_EMAIL JOIN CARS ON CARS.ID = RESERVE.CAR_ID WHERE RESERVE.USER_EMAIL = '"+email+"'", null);
    }

    public Cursor getUserbyEmail(String email){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT  USERS.*, CARS.*, RESERVE.* FROM  USERS JOIN RESERVE ON USERS.EMAIL = RESERVE.USER_EMAIL JOIN CARS ON CARS.ID = RESERVE.CAR_ID WHERE RESERVE.USER_EMAIL = '"+email+"'", null);
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

    public void updatePhone(String email, String phone) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("PHONE", phone);
        sqLiteDatabase.update("USERS", contentValues, "EMAIL = ?", new String[]{email});
    }


    public void updateEmail(String email,String new_email) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", new_email);
        sqLiteDatabase.update("USERS", contentValues, "EMAIL = ?", new String[]{email});
    }

    public void updateCountry(String email, String country) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("COUNTRY", country);
        sqLiteDatabase.update("USERS", contentValues, "EMAIL = ?", new String[]{email});
    }

    public void updateCity(String email, String city) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("CITY", city);
        sqLiteDatabase.update("USERS", contentValues, "EMAIL = ?", new String[]{email});
    }

    public void updateFirstName(String email, String first_name) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FIRST_NAME", first_name);
        sqLiteDatabase.update("USERS", contentValues, "EMAIL = ?", new String[]{email});
    }

    public void updateLastName(String email, String last_name) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("LAST_NAME", last_name);
        sqLiteDatabase.update("USERS", contentValues, "EMAIL = ?", new String[]{email});
    }

    public void updateGender(String email, String gender) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("GENDER", gender);
        sqLiteDatabase.update("USERS", contentValues, "EMAIL = ?", new String[]{email});
    }

    public void updatePassword(String email, String password) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("PASSWORD", password);
        sqLiteDatabase.update("USERS", contentValues, "EMAIL = ?", new String[]{email});
    }

    //car offers
    public Cursor getOfferByFactoryNameAndCarName(String factoryName, String carName) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String selection = "FACTORY_NAME = ? AND NAME = ?";
        String[] selectionArgs = {factoryName, carName};
        return sqLiteDatabase.query("CARS", null, selection, selectionArgs, null, null, null);
    }

    public Cursor getOfferByFactoryName(String factoryName) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String selection = "FACTORY_NAME = ?";
        String[] selectionArgs = {factoryName};
        return sqLiteDatabase.query("CARS", null, selection, selectionArgs, null, null, null);
    }

    public Cursor getOfferByCarName(String carName) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String selection = "NAME= ?";
        String[] selectionArgs = {carName};
        return sqLiteDatabase.query("CARS", null, selection, selectionArgs, null, null, null);
    }

    public void addOffer(int id, double offer) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("OFFER", offer);
        sqLiteDatabase.update("CARS", contentValues, "ID = ?", new String[]{String.valueOf(id)});
    }

}
