package com.ahmadmuzaki.jobtestk24;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        //Membut Nama Database yaitu Apotekk24 dengan factory null dan version 1
        super(context, "apotekk24", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE session(id integer PRIMARY KEY, login text)");
        sqLiteDatabase.execSQL("CREATE TABLE user(id integer PRIMARY KEY AUTOINCREMENT, username text UNIQUE, password text,nama_member text, tanggal_lahir text, alamat text, jenis_kelamin text)");
//        sqLiteDatabase.execSQL("CREATE TABLE user(id integer PRIMARY KEY AUTOINCREMENT, username text UNIQUE, password text)");
        sqLiteDatabase.execSQL("INSERT INTO session(id, login) VALUES (1, 'kosong')");
        sqLiteDatabase.execSQL("INSERT INTO user(id, username, password, nama_member, tanggal_lahir, alamat, jenis_kelamin) VALUES (1, 'admin', 'admin', 'Admin Ganteng', '21 Juni 1993', 'Karangengek', 'Laki-laki')");
        sqLiteDatabase.execSQL("INSERT INTO user(id,username, password, nama_member, tanggal_lahir, alamat, jenis_kelamin) VALUES (2,'aangmorningstar', '123456', 'Ahmad Muzaki SA', '21 Juni 1993', 'KarangCengek', 'Laki-laki')");
        sqLiteDatabase.execSQL("INSERT INTO user(username, password, nama_member, tanggal_lahir, alamat, jenis_kelamin) VALUES ('omfransaryanto', '123456', 'Om Frans Aryanto', '14 Agustus 1952', 'Demangan Jogja', 'Laki-laki')");
        sqLiteDatabase.execSQL("INSERT INTO user(username, password, nama_member, tanggal_lahir, alamat, jenis_kelamin) VALUES ('dinamerlnd', '123456', 'Dina Merlinda', '4 Mei 1994', 'Wanareja', 'Perempuan')");
        sqLiteDatabase.execSQL("INSERT INTO user(username, password, nama_member, tanggal_lahir, alamat, jenis_kelamin) VALUES ('pujianto', '123456', 'Dina Merlinda', '14 Agustus 1994', 'Pamarican', 'Laki-laki')");
        sqLiteDatabase.execSQL("INSERT INTO user(username, password, nama_member, tanggal_lahir, alamat, jenis_kelamin) VALUES ('dikibayu', '123456', 'Diki Bayu Permana', '14 Agustus 1994', 'Pamarican', 'Laki-laki')");


    }
//onUpgrade digunakan untuk menghapus initial database apabila aplikasi sudah pernah di Install
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS session");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS user");
        onCreate(sqLiteDatabase);
    }

    //method check session
    public boolean checkSession(String sessionValues){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM session WHERE login = ?", new String[]{sessionValues});
        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }

    //upgrade session
    public boolean upgradeSession(String sessionValues, int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("login", sessionValues);
        long update = sqLiteDatabase.update("session", contentValues, "id="+id, null);
        if (update == -1){
            return false;
        }else {
            return true;
        }
    }
    //insert user
    public boolean insertUser(String username, String password, String nama_member, String tanggal_lahir, String alamat, String jenis_kelamin){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("nama_member", nama_member);
        contentValues.put("tanggal_lahir", tanggal_lahir);
        contentValues.put("alamat", alamat);
        contentValues.put("jenis_kelamin", jenis_kelamin);
        long insert = sqLiteDatabase.insert("user", null, contentValues);
        if (insert == -1){
            return false;
        }else{
            return true;
        }
    }
    //check login
    public boolean checkLogin(String username, String password){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE username = ? AND password=?", new String[]{username, password});
        if (cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    //Read Data
}
