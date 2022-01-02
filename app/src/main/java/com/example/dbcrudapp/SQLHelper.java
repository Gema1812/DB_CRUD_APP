package com.example.dbcrudapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import androidx.annotation.Nullable;

public class SQLHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLHelper";

    public SQLHelper(Context context) {
        super(context, "UserDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE Usertable(id INTEGER PRIMARY KEY AUTOINCREMENT, nama TEXT, email TEXT, alamat TEXT, noHp TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Usertable");
    }

    //method untuk menambahkan data ke dalam database
    public boolean tambahData(String nama, String email, String alamat, String noHp) {
        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("nama", nama);
        contentValues.put("email", email);
        contentValues.put("alamat", alamat);
        contentValues.put("noHp", noHp);
        long result = DB.insert("Usertable", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //method untuk melihat data yang telah dimasukkan


    //mengembalikan semua data dari database
    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM Usertable";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemID(String nama) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT id FROM Usertable WHERE nama = '" + nama+"'";
        Cursor data = db.rawQuery(query, null);
        return data;
        /**
         * Updates the name field
         * @param newName
         * @param id
         * @param oldName
         */
    }

    public void updateNama(String namaBaru, int id, String namaLama){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Usertable SET nama = '" + namaBaru + "' WHERE id = '" + id + "' AND nama = '" +namaLama + "'";


        Log.d(TAG, "updateNama: query: " + query);
        Log.d(TAG, "updateNama: Setting nama to " + namaBaru);
        db.execSQL(query);
    }

    /**
     * Delete from database
     * @param id
     * @param nama
     */
    public void deleteNama(Integer id, String nama){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM Userdtable WHERE id = '"+id+"' AND nama = '"+nama+"'";

        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + nama + " from database.");
        db.execSQL(query);
    }

}










