package com.example.jr.applipersistancebdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JR on 15/03/2017.
 */

public class ChapitreBaseSQLite extends SQLiteOpenHelper {


    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "chapitredb";

    private static final String TABLE_CHAPITRE = "chapitre";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESC = "description";

    public ChapitreBaseSQLite(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CHAPITRE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_DESC + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHAPITRE);
        onCreate(db);
    }

    public void addChapitre(Chapitre chapitre) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, chapitre.getName());
        values.put(KEY_DESC, chapitre.getDescription());

        // Inserting Row
        db.insert(TABLE_CHAPITRE, null, values);
        db.close(); // Closing database connection
    }


    public Chapitre getChapitre(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CHAPITRE, new String[]{KEY_ID,
                        KEY_NAME, KEY_DESC}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Chapitre chapitre = new Chapitre(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        return chapitre;
    }

    public List<Chapitre> getAllChapitre() {
        List<Chapitre> chapitreList = new ArrayList<Chapitre>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CHAPITRE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Chapitre chapitre = new Chapitre();
                chapitre.setId(Integer.parseInt(cursor.getString(0)));
                chapitre.setName(cursor.getString(1));
                chapitre.setDescription(cursor.getString(2));
                chapitreList.add(chapitre);
            } while (cursor.moveToNext());
        }

        return chapitreList;
    }

    public int getChapitresCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CHAPITRE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    public int updateChapitre(Chapitre chapitre) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, chapitre.getName());
        values.put(KEY_DESC, chapitre.getDescription());

        // updating row
        return db.update(TABLE_CHAPITRE, values, KEY_ID + " = ?",
                new String[]{String.valueOf(chapitre.getId())});
    }

    public void deleteChapitre(Chapitre chapitre) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CHAPITRE, KEY_ID + " = ?",
                new String[] { String.valueOf(chapitre.getId()) });
        db.close();
    }
}
