
package com.example.madproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBName = "Login.db";

    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(userName TEXT primary key, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase MYDB= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MYDB.insert("users",null, contentValues);
        if(result==-1) return false;
        else
            return true;

    }

    public Boolean checkusername(String username){
        SQLiteDatabase MYDB= this.getWritableDatabase();
        Cursor cursor=  MYDB.rawQuery("Select * from user where username =?",new String[] {username} );
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MYDB = this.getWritableDatabase();
        Cursor cursor= MYDB.rawQuery("Select * from user where username =? and password=?", new String[]{username,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

}
