package com.example.android.dogwalking.data;

import android.provider.BaseColumns;

/**
 * Created by Vladi on 25.7.17.
 */

public final class OwnerContract {

    // preventing from accidental instantiating the contract class
    // by giving an empty contructor
    private OwnerContract() {
    }

    // Inner class defining constant values of the table
    public static final class OwnerEntry implements BaseColumns {

        public final static String TABLE_NAME = "dog walking";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_OWNER = "owner";
        public final static String COLUMN_NUMBER = "number";
        public final static String COLUMN_BREED = "breed";
        public final static String COLUMN_DOG = "dog";

        // values of breed of the dog
        public static final int BREED_UNKNOWN = 0;
        public static final int BREED_SMALL = 1;
        public static final int BREED_MEDIUM = 2;
        public static final int BREED_BIG = 3;
    }
}
