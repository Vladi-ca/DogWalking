package com.example.android.dogwalking;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.dogwalking.data.OwnerContract.OwnerEntry;
import com.example.android.dogwalking.data.OwnerDbHelper;

import static com.example.android.dogwalking.data.OwnerContract.OwnerEntry.COLUMN_BREED;
import static com.example.android.dogwalking.data.OwnerContract.OwnerEntry.COLUMN_DOG;
import static com.example.android.dogwalking.data.OwnerContract.OwnerEntry.COLUMN_NUMBER;
import static com.example.android.dogwalking.data.OwnerContract.OwnerEntry.COLUMN_OWNER;

public class MainActivity extends AppCompatActivity {

    private OwnerDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instantiating of subclass of SQlLiteOpenHelper and passing the context to it
        mDbHelper = new OwnerDbHelper(this);
        insertOwner();
    }

    private Cursor createCursor() {
        // creaing or openning a database for reading from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                COLUMN_OWNER,
                OwnerEntry.COLUMN_NUMBER,
                OwnerEntry.COLUMN_BREED,
                OwnerEntry.COLUMN_DOG};

        // query on the dog owners table
        Cursor cursor = db.query(
                OwnerEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        return cursor;
    }

    private void readData() {

        StringBuilder displayData = new StringBuilder();
        Cursor cursor = createCursor();

        try {

            displayData.append("The Dog Walking table contains " + cursor.getCount() + " owners.\n");
            displayData.append(COLUMN_OWNER + " - " +
                    OwnerEntry.COLUMN_NUMBER + " - " +
                    OwnerEntry.COLUMN_BREED + " - " +
                    OwnerEntry.COLUMN_DOG + "\n");

            // figure out the index of each column
            int ownerColumnIndex = cursor.getColumnIndex(COLUMN_OWNER);
            int numberColumnIndex = cursor.getColumnIndex(COLUMN_NUMBER);
            int breedColumnIndex = cursor.getColumnIndex(COLUMN_BREED);
            int dogColumnIndex = cursor.getColumnIndex(COLUMN_DOG);

            while (cursor.moveToNext()) {

                String currentOwner = cursor.getString(ownerColumnIndex);
                int currentNumber = cursor.getInt(numberColumnIndex);
                int currentBreed = cursor.getInt(breedColumnIndex);
                String currentDog = cursor.getString(dogColumnIndex);

                displayData.append(("\n" + currentOwner + " - " +
                        currentNumber + " - " +
                        currentBreed + " - " +
                        currentDog));
            }
        } finally {
            cursor.close();
        }
    }

    // insert method using Content values
    private void insertOwner() {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Creating a ContentValues object
        ContentValues values = new ContentValues();

        values.put(COLUMN_OWNER, "James");
        values.put(OwnerEntry.COLUMN_NUMBER, 001234567);
        values.put(OwnerEntry.COLUMN_BREED, OwnerEntry.BREED_BIG);
        values.put(OwnerEntry.COLUMN_DOG, "Spiky");

        // Inserting a new row to database
        long newRowId = db.insert(OwnerEntry.TABLE_NAME, null, values);
        Log.v("MainActivity", "New row ID " + newRowId);
        readData();
    }
}
