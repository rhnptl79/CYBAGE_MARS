package com.example.marsapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "caurseApp";
    private static final String TABLE_COURSE = "course";
    private static final String TABLE_CONTENT = "course_content";

    private static final String KEY_ID = "id";
    private static final String KEY_COURSE_NAME = "name";
    private static final String KEY_COURSE_IS_PAID = "isPaid";
    private static final String KEY_COURSE_FEE = "fee";


    private static final String KEY_COURSE_ID = "course_id";
    private static final String KEY_VIDEO_NAME = "video_name";
    private static final String KEY_VIDEO_URL = "video_url";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_COURSE_TABLE = "CREATE TABLE " + TABLE_COURSE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_COURSE_NAME + " TEXT," + KEY_COURSE_IS_PAID +" BOOLEN," +KEY_COURSE_FEE +" TEXT" +")";

        String CREATE_COURSE_CONTENT_TABLE = "CREATE TABLE " + TABLE_CONTENT + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_VIDEO_NAME + " TEXT," + KEY_VIDEO_URL +" TEXT," +KEY_COURSE_ID +" TEXT" +")";

        db.execSQL(CREATE_COURSE_TABLE);
        db.execSQL(CREATE_COURSE_CONTENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);

        // Create tables again

        onCreate(db);
    }

}
