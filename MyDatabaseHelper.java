package com.example.lastdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "ExpenseDb.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "data_expense";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "trip_title";
    private static final String COLUMN_TRANSPORT = "trip_transport";
    private static final String COLUMN_TRIP_DATE = "trip_date";
    private static final String COLUMN_TRIP_RETURN_DATE = "trip_return";
    private static final String COLUMN_DESCRIPTION = "trip_description";

    private static final String COLUMN_PARTICIPANT = "trip_participant";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT, " +
                COLUMN_TRANSPORT + " TEXT, " +
                COLUMN_TRIP_DATE + " TEXT, " +
                COLUMN_TRIP_RETURN_DATE + " TEXT, " +
                 COLUMN_DESCRIPTION + " TEXT, " +
                        COLUMN_PARTICIPANT + " INTEGER);";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addTrip(String title,String transport, String date,  String  returndate, String description, int participant){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_TRANSPORT, transport);
        cv.put(COLUMN_TRIP_DATE, date);
        cv.put(COLUMN_TRIP_RETURN_DATE, returndate);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_PARTICIPANT, participant);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String title,String transport, String date, String returndate, String description, String participant){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_TRANSPORT, transport);
        cv.put(COLUMN_TRIP_DATE, date);
        cv.put(COLUMN_TRIP_RETURN_DATE, returndate);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_PARTICIPANT, participant);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}

