package com.example.android.dogwalking.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.dogwalking.data.OwnerContract.OwnerEntry;

/**
 * Created by Vladi on 25.7.17.
 */

public class OwnerDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = OwnerDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "dog_walking.db";

    private static final int DATABASE_VERSION = 1;

    // Constructing a new instance of OwnerDbHelper
    public OwnerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // when database is created 1st time this is called
    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating the String that contains the SQL statement for creating dogs owner info for a dog walker
        String SQL_CREATE_OWNER_TABLE = "CREATE TABLE " + OwnerEntry.TABLE_NAME + " ("
                + OwnerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + OwnerEntry.COLUMN_OWNER + " TEXT NOT NULL, "
                + OwnerEntry.COLUMN_NUMBER + " INTEGER NOT NULL, "
                + OwnerEntry.COLUMN_BREED + " INTEGER NOT NULL DEFAULT 0, "
                + OwnerEntry.COLUMN_DOG + " TEXT NOT NULL);";

        // Executing the SQL statement above
        db.execSQL(SQL_CREATE_OWNER_TABLE);
    }

    // when database needs to be updated this is called
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // The database is still at version 1, so there's nothing to do be done here
    }
}
