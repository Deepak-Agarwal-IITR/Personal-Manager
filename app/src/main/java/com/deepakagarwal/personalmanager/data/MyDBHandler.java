package com.deepakagarwal.personalmanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.deepakagarwal.personalmanager.model.Contacts;
import com.deepakagarwal.personalmanager.model.Passwords;
import com.deepakagarwal.personalmanager.model.User;
import com.deepakagarwal.personalmanager.parameters.ContactInfo;
import com.deepakagarwal.personalmanager.parameters.PasswordInfo;
import com.deepakagarwal.personalmanager.parameters.UserInfo;

public class MyDBHandler extends SQLiteOpenHelper {
    public MyDBHandler(Context context){
        super(context, UserInfo.DB_NAME, null, UserInfo.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE "+UserInfo.TABLE_NAME+"("+UserInfo.KEY_NAME+" TEXT, "+UserInfo.KEY_AGE+" TEXT, "+UserInfo.KEY_USERNAME+" TEXT,"+UserInfo.KEY_PASSCODE+" TEXT"+")";
        Log.d("dbdeepak", "Query being run is: "+create);
        db.execSQL(create);

        String createContactTable = "CREATE TABLE "+ ContactInfo.TABLE_NAME+"("+ContactInfo.OUR_USER+" TEXT, "+ContactInfo.KEY_NAME+" TEXT, "+ContactInfo.KEY_PHONE_NO+" TEXT, "+ContactInfo.KEY_ADDRESS+" TEXT, "+ContactInfo.KEY_ID+" TEXT"+")";
        Log.d("dbdeepak", "Query being run is: "+createContactTable);
        db.execSQL(createContactTable);


        String createPasswordTable = "CREATE TABLE "+ PasswordInfo.TABLE_NAME+"("+PasswordInfo.OUR_USER+" TEXT, "+PasswordInfo.KEY_WEBSITE+" TEXT, "+PasswordInfo.KEY_USERNAME+" TEXT,"+PasswordInfo.KEY_PASSWORD+" TEXT"+")";
        Log.d("dbdeepak", "Query being run is: "+createPasswordTable);
        db.execSQL(createPasswordTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addUser(User user){

        if (!checkUser(user.getUsername())) {
            return false;
        }

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserInfo.KEY_NAME,user.getName());
        values.put(UserInfo.KEY_AGE,user.getAge());
        values.put(UserInfo.KEY_USERNAME,user.getUsername());
        values.put(UserInfo.KEY_PASSCODE,user.getPasscode());

       db.insert(UserInfo.TABLE_NAME,null,values);
       Log.d("dbdeepak","Successfully inserted.");
       db.close();
       return true;
    }

    /*public void getUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + UserInfo.TABLE_NAME;
        Cursor cr = db.rawQuery(query, null);

        if(cr.moveToFirst()) {
            do {
                String name = cr.getString(0);
                String age = cr.getString(1);
                String username = cr.getString(2);
                String pass = cr.getString(3);
                Log.d("dbDeepak", "" + name + "===" + pass);
            } while (cr.moveToNext());
        }
        db.close();
    }*/

    public String getNameOfUser(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + UserInfo.TABLE_NAME;
        Cursor cr = db.rawQuery(query, null);
        String name = "";
        if (cr.moveToFirst()) {
            do {

                if (cr.getString(2).equalsIgnoreCase(username)) {
                    return cr.getString(0);
                }
            } while (cr.moveToNext());
        }
        db.close();
        return name;
    }
    public boolean checkUser(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + UserInfo.TABLE_NAME;
        Cursor cr = db.rawQuery(query, null);

        if(cr.moveToFirst()) {
            do {
                String tempUsername = cr.getString(2);
                if (tempUsername.equalsIgnoreCase(username)) {
                    return false;
                }
            } while (cr.moveToNext());
        }
        db.close();
        return true;
    }

    public boolean checkPasscode(String username, String passcode) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + UserInfo.TABLE_NAME;
        Cursor cr = db.rawQuery(query, null);

        if (cr.moveToFirst()) {
            do {
                String tempUsername = cr.getString(2);
                String tempPasscode = cr.getString(3);
                if (tempUsername.equals(username) && tempPasscode.equals(passcode)) {
                    return true;
                }
            } while (cr.moveToNext());
        }
        db.close();
        return false;
    }

    public void addPassword(Passwords p){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PasswordInfo.OUR_USER,p.ourUser);
        values.put(PasswordInfo.KEY_WEBSITE,p.website);
        values.put(PasswordInfo.KEY_USERNAME,p.username);
        values.put(PasswordInfo.KEY_PASSWORD,p.password);

        db.insert(PasswordInfo.TABLE_NAME,null,values);
        Log.d("dbdeepak","Successfully inserted.");
        db.close();
    }

    public List<Passwords> getAllPasswords(String username){
        List<Passwords> passwordList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String select = "SELECT * FROM " +PasswordInfo.TABLE_NAME;
        Cursor cr = db.rawQuery(select,null);
        if(cr.moveToFirst()){
            do{
                Passwords p = new Passwords(cr.getString(0),cr.getString(1),cr.getString(2),cr.getString(3));
                if(username.equals(p.ourUser))
                    passwordList.add(p);
            }while(cr.moveToNext());
        }
        return passwordList;
    }

    public void addContact(Contacts c){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ContactInfo.OUR_USER,c.ourUser);
        values.put(ContactInfo.KEY_NAME,c.name);
        values.put(ContactInfo.KEY_PHONE_NO,c.phoneNumber);
        values.put(ContactInfo.KEY_ADDRESS,c.address);
        values.put(ContactInfo.KEY_ID,c.id);

        db.insert(ContactInfo.TABLE_NAME,null,values);
        Log.d("dbdeepak","Successfully inserted.");
        db.close();
    }

    public List<Contacts> getAllContacts(String username){
        List<Contacts> contactList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String select = "SELECT * FROM " +ContactInfo.TABLE_NAME;
        Cursor cr = db.rawQuery(select,null);
        if(cr.moveToFirst()){
            do{
                Contacts c = new Contacts(cr.getString(0),cr.getString(1),cr.getString(2),cr.getString(3));
                if(username.equals(c.ourUser))
                    contactList.add(c);
            }while(cr.moveToNext());
        }
        return contactList;
    }

    public int getCountOfContacts(){
        String query = "SELECT * FROM "+ ContactInfo.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query,null);
        return c.getCount();
    }



    // NOT WORKING PROPERLY
    public void deleteContact(Contacts contact){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("dbdeepak",String.valueOf(contact.id));
        db.delete(ContactInfo.TABLE_NAME,ContactInfo.KEY_ID+"=?",new String[]{String.valueOf(contact.id)});
        db.close();
    }

    public void updateContact(Contacts c){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ContactInfo.KEY_NAME,c.name);
        values.put(ContactInfo.KEY_PHONE_NO,c.phoneNumber);
        values.put(ContactInfo.KEY_ADDRESS,c.address);
        db.update(ContactInfo.TABLE_NAME,values,ContactInfo.KEY_ID+"=?",new String[]{String.valueOf(c.id)});
        
    }
}
