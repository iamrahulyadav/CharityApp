package com.charityapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by archirayan on 1/12/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "CharityApp";

    // Contacts table name
    private static final String TABLE_REGISTRATION = "Registration";
    private static final String TABLE_ADD_ORGANIZATION = "add_organization";

    // Contacts Table Columns names
    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String EMAIL = "email";
    private static final String PHONE = "phone";
    private static final String PASSWORD = "password";

    private static final String ORG_NAME = "org_name";
    private static final String ORG_ADDRESS = "org_address";
    private static final String ORG_CATEGORY = "org_category";
    private static final String ORG_DESCRIPTION = "org_description";

    // Create Table
    String CREATE_REGISTRATION_TABLE = "CREATE TABLE " + TABLE_REGISTRATION + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + USERNAME + " TEXT," + EMAIL + " TEXT,"
            + PHONE + " TEXT," + PASSWORD + " TEXT" + ")";

    String CREATE_ADD_ORGANIZATION_TABLE = "CREATE TABLE " + TABLE_ADD_ORGANIZATION + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ORG_NAME + " TEXT," + ORG_ADDRESS + " TEXT,"
            + ORG_CATEGORY + " TEXT," + ORG_DESCRIPTION + " TEXT" + ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_REGISTRATION_TABLE);
        db.execSQL(CREATE_ADD_ORGANIZATION_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTRATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADD_ORGANIZATION);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void addRegistrationData(RegistrationDataPojo registrationDataPojo) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USERNAME, registrationDataPojo.getName());// Contact Name
        values.put(PHONE, registrationDataPojo.getContact());//mobile number
        values.put(EMAIL, registrationDataPojo.getEmail());//email
        values.put(PASSWORD, registrationDataPojo.getPassword()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_REGISTRATION, null, values);
        db.close(); // Closing database connection
    }

    public void addOrganizationData(OrganizationDetailPojo organizationDetailPojo) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ORG_NAME, organizationDetailPojo.getName());
        values.put(ORG_ADDRESS, organizationDetailPojo.getLocation());
        values.put(ORG_CATEGORY, organizationDetailPojo.getCategory());
        values.put(ORG_DESCRIPTION, organizationDetailPojo.getDiscription());

        // Inserting Row
        db.insert(TABLE_ADD_ORGANIZATION, null, values);
        db.close();
    }

    // get profile data
    public RegistrationDataPojo getRegistrationData() throws SQLException {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_REGISTRATION, null, null, null, null, null, null);

        RegistrationDataPojo registrationDataPojo = new RegistrationDataPojo();

        if (cursor.getCount() > 0) {

            if (cursor.moveToFirst()) {
                registrationDataPojo.setName(cursor.getString(cursor.getColumnIndex(USERNAME)));
                registrationDataPojo.setContact(cursor.getString(cursor.getColumnIndex(PHONE)));
                registrationDataPojo.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
                registrationDataPojo.setPassword(cursor.getString(cursor.getColumnIndex(PASSWORD)));

            }
        }
        cursor.close();
        db.close();
        return registrationDataPojo;

    }

    //update profile
    public boolean updateProfile(String name, String email, String phone) throws SQLException {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME,name);
        contentValues.put(EMAIL,email);
        contentValues.put(PHONE,phone);
        db.update(TABLE_REGISTRATION, contentValues, USERNAME + " = ?",new String[] { name });
        return true;

    }

    // All organization list
    public ArrayList<OrganizationDetailPojo> getAllOrganization() throws SQLException {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ADD_ORGANIZATION, null, null, null, null, null, null);

        ArrayList<OrganizationDetailPojo> organizationDetailPojos = new ArrayList<>();

        OrganizationDetailPojo organizationDetailPojo;

        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                organizationDetailPojo = new OrganizationDetailPojo();
                organizationDetailPojo.setName(cursor.getString(1));
                organizationDetailPojo.setDiscription(cursor.getString(4));
                organizationDetailPojo.setCategory(cursor.getString(3));
                organizationDetailPojo.setLocation(cursor.getString(2));
                organizationDetailPojos.add(organizationDetailPojo);
            }
        }
        cursor.close();
        db.close();
        return organizationDetailPojos;

    }

    //Organization Detail
    // get profile data
    public OrganizationDetailPojo getOrganizationData() throws SQLException {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ADD_ORGANIZATION, null, null, null, null, null, null);

        OrganizationDetailPojo organizationDetailPojo = new OrganizationDetailPojo();

        if (cursor.getCount() > 0) {

            if (cursor.moveToFirst()) {
                organizationDetailPojo.setName(cursor.getString(cursor.getColumnIndex(ORG_NAME)));
                organizationDetailPojo.setLocation(cursor.getString(cursor.getColumnIndex(ORG_ADDRESS)));
                organizationDetailPojo.setCategory(cursor.getString(cursor.getColumnIndex(ORG_CATEGORY)));
                organizationDetailPojo.setDiscription(cursor.getString(cursor.getColumnIndex(ORG_DESCRIPTION)));

            }
        }
        cursor.close();
        db.close();
        return organizationDetailPojo;

    }

    //For Login
    public boolean checkUser(String email, String password) throws SQLException {

        SQLiteDatabase db = this.getReadableDatabase();

        // query user table with conditions

        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */

//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_REGISTRATION + " WHERE " + EMAIL + "=?", new String[]{email, password});                      //The sort order

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_REGISTRATION + " WHERE " + USERNAME + " = '" + email + "'" + " AND " + PASSWORD + " = '" + password + "'", null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();

                return true;
            }
            cursor.close();
        }
        return false;

    }

}
