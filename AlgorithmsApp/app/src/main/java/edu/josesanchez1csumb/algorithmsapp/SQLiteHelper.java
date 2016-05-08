package edu.josesanchez1csumb.algorithmsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by jsanchez-garcia on 5/7/16.
 */
public class SQLiteHelper extends SQLiteOpenHelper{

    private static SQLiteHelper dInstance;

    // Database Name - AlgorithmDB
    private  static final String DATABASE_NAME = "AlgorithmDB";

    // Table Name - Algorithms
    private static final String TABLE_ALGORITHMS = "algorithm";

    // Columns Names of books Table
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_RUNTIME = "runtime";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Log TAG for debugging purpose
    private static final String TAG = "SQLiteAppLog";

    // Constructor
    private SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized SQLiteHelper getInstance(Context context){
        if(dInstance == null){
            dInstance = new SQLiteHelper(context);
        }
        return dInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create a table called "books"
        String CREATE_BOOK_TABLE = "CREATE TABLE " + TABLE_ALGORITHMS + "( " +
                "id INTEGER PRIMARY KEY, " +
                "name TEXT, "+
                "description TEXT, "+
                "runtime TEXT )";

        // execute an SQL statement to create the table
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS algorithm");

        // create fresh books table
        this.onCreate(db);
    }

    public void addAlgorithm(Algorithm algorithm){
        Log.d(TAG, " Add Algorithm" + algorithm.toString());

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_ID, algorithm.getID());
        values.put(KEY_NAME, algorithm.getName()); // get name
        values.put(KEY_DESCRIPTION, algorithm.getDescription()); // get description
        values.put(KEY_RUNTIME, algorithm.getRuntime());   // get runtime

        // 3. insert
        db.insert(TABLE_ALGORITHMS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close - release the reference of writable DB
        db.close();
    }

    public ArrayList<Algorithm> getAll(){
        ArrayList<Algorithm> algorithms_array = new ArrayList<>();

        // 1. Build the Query
        String query = "SELECT id, name from " + TABLE_ALGORITHMS;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        // 3. Go over each row, build Algorithm Object as we iterate through SQL statement
        Algorithm algorithm = null;

        if(cursor.moveToFirst()){
            do{
                algorithm = new Algorithm();
                algorithm.setID(cursor.getInt(0));
                algorithm.setName(cursor.getString(1)); //this will get the name from the row query

                //add the algorithm object into algorithms_array
                algorithms_array.add(algorithm);
            }while(cursor.moveToNext()); //determines whether there is still information available.
        }

        Log.d(TAG, "All Books: " + algorithms_array.toString());
        db.close();
        return algorithms_array;
    }

    public Algorithm getBasedOnId(int id){
        // 1. Build the Query
        String query = "SELECT * from " + TABLE_ALGORITHMS + " WHERE id= " + Integer.toString(id);

        // 2. get a writable Database connection
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query,null);

        Algorithm chosen_algorithm = null;

        if(cursor.moveToFirst()){
            chosen_algorithm = new Algorithm();
            chosen_algorithm.setID(cursor.getInt(0));
            chosen_algorithm.setName(cursor.getString(1));
            chosen_algorithm.setDescription(cursor.getString(2));
            chosen_algorithm.setRuntime(cursor.getString(3));
        }

        db.close();

        Log.d(TAG, "Object found: " + chosen_algorithm.toString());
        return chosen_algorithm;


    }

}
