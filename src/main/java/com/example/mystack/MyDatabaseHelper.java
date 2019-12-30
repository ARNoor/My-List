package com.example.mystack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyStack.db";
    private static final String TABLE_NAME = "MoviesTable";
    private static final int VERSION_NUMBER = 1;
    private static final String ID = "_id";
    private static final String RATING = "Rating";
    private static final String MOVIE_NAME = "Movie_Name";
    private static final String GENRE = "Movie_Genre";
    private static final String SUGGESTED_BY = "Suggested_By";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;

    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MOVIE_NAME+" VARCHAR(255), "+
            GENRE+"  VARCHAR(255), "+
            SUGGESTED_BY+"  VARCHAR(255), "+
            RATING+" INTEGER )";

    private Context context;
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            sqLiteDatabase.execSQL(CREATE_TABLE);
            Toast.makeText(context, "Table is created", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context, "Exception: "+e, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try{
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);
            Toast.makeText(context, "Table is upgraded", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context, "Exception: "+e, Toast.LENGTH_LONG).show();
        }
    }

    public long insertData(String name, String genre, String suggested, String rating){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MOVIE_NAME, name);
        contentValues.put(GENRE, genre);
        contentValues.put(SUGGESTED_BY, suggested);
        contentValues.put(RATING, rating);
        long row_id = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return row_id;
    }

    public Cursor showAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        return cursor;
    }

    public int deleteData(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int d =sqLiteDatabase.delete(TABLE_NAME, ID+" = ?", new String[]{ id });
        return d;
    }
}
