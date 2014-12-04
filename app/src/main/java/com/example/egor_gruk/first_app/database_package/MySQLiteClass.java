package com.example.egor_gruk.first_app.database_package;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Егор on 27.11.2014.
 */
public class MySQLiteClass {
    private static final String DATABASE_NAME       =   "first_app_db";
    private static final int DATABASE_VERSION       =   1;
    private static final String TABLE_NAME          =   "first_app_db";

    private static final String KEY_USER            =   "user";
    private static final String KEY_PASSWORD        =   "password";
    private static final String KEY_HASH            =   "hash";
    private static final String KEY_SALT            =   "salt";

    private final Context context;
    private DBHelp dbhelp;
    private SQLiteDatabase thisDataBase;

    public MySQLiteClass (Context context)
    {
        this.context=context;
    }
    public MySQLiteClass open(boolean writable) throws SQLiteException {
        dbhelp = new DBHelp(context);
        if(writable)
            thisDataBase = dbhelp.getWritableDatabase();
        else
            thisDataBase = dbhelp.getReadableDatabase();
        return this;
    }
    public void close() {
        dbhelp.close();
    }
    public void addRecord(String userName, String password, String hash, String salt)
    {
        open(true);
        ContentValues values = new ContentValues();
        values.put(KEY_USER, userName);
        values.put(KEY_PASSWORD, password);
        values.put(KEY_HASH, hash);
        values.put(KEY_SALT, salt);
        thisDataBase.insert(TABLE_NAME, null, values);
        close();
    }
    public String fetchRecord(String userName)
    {
        open(false);
        String record = null;
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE user LIKE '" + userName + "'";
        Cursor cursor = thisDataBase.rawQuery(query, null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
            record = "name  = " + cursor.getString(0) + ", password = " + cursor.getString(1) +
                    " , hash  = " + cursor.getString(2) + ", hash + salt = " + cursor.getString(3);
        }
        close();
        return record;
    }
    
    private class DBHelp extends SQLiteOpenHelper {
        public DBHelp(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            // TODO Auto-generated constructor stub
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            String query = "CREATE TABLE " + TABLE_NAME + "(" +
                    KEY_USER        +  " TEXT NOT NULL, " +
                    KEY_PASSWORD    + " TEXT NOT NULL, " +
                    KEY_HASH        + " TEXT, " +//" TEXT NOT NULL, " +
                    KEY_SALT        + " TEXT);";//" TEXT NOT NULL);";
            db.execSQL(query);
            Log.d("MySQLiteClass", " onCreate()");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int old_version, int new_version) {
            // TODO Auto-generated method stub
            onCreate(db);
        }
    }
}
