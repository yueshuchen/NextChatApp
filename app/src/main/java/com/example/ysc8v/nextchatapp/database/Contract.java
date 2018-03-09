package com.example.ysc8v.nextchatapp.database;

/**
 * Created by ysc8v on 3/8/2018.
 */

public class Contract {

    private static final String TABLE_NAME = "User";
    private static final String COLUMN_NAME_TITLE_1 = "Name";
    private static final String COLUMN_NAME_TITLE_2 = "Email";
    private static final String COLUMN_NAME_TITLE_3 = "Image";

    public static final String SQL_CREATE_USER_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_NAME_TITLE_1 + " VARCHAR(255) PRIMARY KEY, "
                    + COLUMN_NAME_TITLE_2 + " VARCHAR(255), " + COLUMN_NAME_TITLE_3 + " BLOB);";

    public static final String SQL_DELETE_WUSER_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


}
