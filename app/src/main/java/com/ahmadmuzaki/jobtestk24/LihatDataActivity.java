package com.ahmadmuzaki.jobtestk24;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LihatDataActivity extends AppCompatActivity {
    protected Cursor cursor;
    DatabaseHelper databaseHelper;
    Button kembali;
    TextView kode_member, nama, tanggal_lahir, alamat, jenis_kelamin, username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);

        databaseHelper = new DatabaseHelper(this);
        kode_member = findViewById(R.id.lihat_kode_member);
        nama = findViewById(R.id.lihat_nama);
        tanggal_lahir =  findViewById(R.id.lihat_tanggal_lahir);
        alamat = findViewById(R.id.lihat_alamat);
        jenis_kelamin =  findViewById(R.id.lihat_jenis_kelamin);
        username = findViewById(R.id.lihat_username);
        password = findViewById(R.id.lihat_password);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM user WHERE username = '" +
                getIntent().getStringExtra("username") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            kode_member.setText(cursor.getString(0).toString());
            nama.setText(cursor.getString(3).toString());
            username.setText(cursor.getString(1).toString());
            password.setText(cursor.getString(2).toString());
            tanggal_lahir.setText(cursor.getString(4).toString());
            alamat.setText(cursor.getString(5).toString());
            jenis_kelamin.setText(cursor.getString(6).toString());
        }
        kembali = findViewById(R.id.btn_kembali_lihat);
        kembali.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}