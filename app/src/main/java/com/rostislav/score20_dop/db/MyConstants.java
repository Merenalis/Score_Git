package com.rostislav.score20_dop.db;

public class MyConstants {
    public static final String TABLE_NAME = "my_table";
    public static final String _ID = "_id";
    public static final String FIRST_TEAM = "first_team";
    public static final String SECOND_TEAM = "second_team";
    public static final String SCORE = "score";
    public static final String DATE = "date";
    public static final String TOUR = "tour";
    public static final String DB_NAME = "my_db.db";
    public static final int DB_VERSION = 5;

    public static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS "+
            TABLE_NAME + " (" + _ID + "INTEGER PRIMARY KEY," + FIRST_TEAM + " TEXT," +
            SECOND_TEAM + " TEXT," + SCORE + " TEXT," + DATE +  " TEXT," + TOUR + " TEXT)";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
