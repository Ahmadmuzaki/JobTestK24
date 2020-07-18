package com.ahmadmuzaki.jobtestk24;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);

        logout = findViewById(R.id.btn_logout);

        //Mengecek Apakah user sedang login atau belum login
        boolean checkSession = databaseHelper.checkSession("ada");
        boolean checkSessionAdm = databaseHelper.checkSession("admin");
        if (checkSessionAdm){
            Intent loginIntent = new Intent(MainActivity.this, AdminActivity.class);
            startActivity(loginIntent);
            finish();
        }else if (checkSession == false){
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }

        //Logout
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean updtSession = databaseHelper.upgradeSession("kosong",1);
                if (updtSession) {
                    Toast.makeText(getApplicationContext(), "Berhasil Keluar", Toast.LENGTH_SHORT).show();
                    Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                    finish();
                }
            }
        });
    }
}