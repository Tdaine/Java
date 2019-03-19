package com.example.abaka.homework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DBAdapter {

    private static final String DB_NAME = "People.db";
    private static final String DB_TABLE = "peopleinfo";
    private static final int DB_VERSION = 5;

    public static final String KEY_ID = "_id";
    public static final String KEY_DATE = "date";
    public static final String KEY_MONEY = "money";
    public static final String KEY_COMMENT = "comment";
    public static final String KEY_NAME = "name";
    public static final String KEY_TYPE= "type";


    private SQLiteDatabase db;
    private final Context context;
    private DBOpenHelper dbOpenHelper;

    public DBAdapter(Context _context) {
        context = _context;
    }

    /** Close the database */
    public void close() {
        if (db != null){
            db.close();
            db = null;
        }
    }

    /** Open the database */
    public void open() throws SQLiteException {
        dbOpenHelper = new DBOpenHelper(context, DB_NAME, null, DB_VERSION);
        try {
            db = dbOpenHelper.getWritableDatabase();
        }
        catch (SQLiteException ex) {
            db = dbOpenHelper.getReadableDatabase();
        }
    }


    public long insert(Bill bill) {
        ContentValues newValues = new ContentValues();

        newValues.put(KEY_NAME, bill._name);
        newValues.put(KEY_DATE, bill._date);
        newValues.put(KEY_COMMENT,bill._comment);
        newValues.put(KEY_MONEY, bill._money);
        newValues.put(KEY_TYPE,bill._type);


        return db.insert(DB_TABLE, null, newValues);
    }



    public Bill[] queryAllData(String name) {
        Cursor results =  db.query(DB_TABLE, new String[] { KEY_ID, KEY_NAME, KEY_DATE, KEY_MONEY,KEY_COMMENT,KEY_TYPE},
                KEY_NAME+"='"+name+"'", null, null, null, null);
        return ConvertToPeople(results);
    }


    public Bill[] queryOneData(long id) {
        Cursor results =  db.query(DB_TABLE, new String[] { KEY_ID, KEY_NAME, KEY_DATE, KEY_MONEY,KEY_COMMENT,KEY_TYPE},
                KEY_ID + "=" + id, null, null, null, null);
        return ConvertToPeople(results);
    }

    private Bill[] ConvertToPeople(Cursor cursor){
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()){
            return null;
        }
        Bill[] Bill = new Bill[resultCounts];
        for (int i = 0 ; i<resultCounts; i++){
            Bill[i] = new Bill();
            Bill[i].id = cursor.getInt(0);
            Bill[i]._name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
            Bill[i]._date = cursor.getString(cursor.getColumnIndex(KEY_DATE));
            Bill[i]._money = cursor.getFloat(cursor.getColumnIndex(KEY_MONEY));
            Bill[i]._comment = cursor.getString(cursor.getColumnIndex(KEY_COMMENT));
            Bill[i]._type = cursor.getString(cursor.getColumnIndex(KEY_TYPE));
            cursor.moveToNext();
        }
        return Bill;
    }

    public long deleteAllData(String name) {
        return db.delete(DB_TABLE, KEY_NAME+"='"+name+"'", null);
    }

    public long deleteOneData(long id) {
        return db.delete(DB_TABLE,  KEY_ID + "=" + id, null);
    }

    public long updateOneData(long id , Bill bill){
        ContentValues updateValues = new ContentValues();
        updateValues.put(KEY_NAME, bill._name);
        updateValues.put(KEY_DATE, bill._date);
        updateValues.put(KEY_MONEY, bill._money);
        updateValues.put(KEY_COMMENT, bill._comment);
        updateValues.put(KEY_TYPE, bill._type);

        return db.update(DB_TABLE, updateValues,  KEY_ID + "=" + id, null);
    }

    /** 静态Helper类，用于建立、更新和打开数据库*/
    private static class DBOpenHelper extends SQLiteOpenHelper {

        public DBOpenHelper(Context context, String name, CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        private static final String DB_CREATE = "create table " +
                DB_TABLE + " (" + KEY_ID + " integer primary key autoincrement, " +
                KEY_NAME+ " text not null, " + "["+ KEY_DATE+ "]"+" String," + KEY_MONEY + " float, "+
                KEY_COMMENT + " String," + KEY_TYPE+" String);";

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
            _db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(_db);
        }
    }
}