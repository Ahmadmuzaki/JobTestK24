package com.ahmadmuzaki.jobtestk24;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    Button login, register;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);

        username = (EditText)findViewById(R.id.editText_username);
        password = (EditText)findViewById(R.id.editText_password);
        login = (Button)findViewById(R.id.btn_login);
        register =  (Button)findViewById(R.id.btn_register);

        //resgister pindah ke halaman register
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
                finish();
            }
        });

        //login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Mengambil nilai string dari EditText username
                String strUsername = username.getText().toString();
                String strPassword = password.getText().toString();
                boolean masuk = databaseHelper.checkLogin(strUsername, strPassword);
                if (masuk == true){
//                    boolean updateSession = databaseHelper.upgradeSession("ada",1);
                    if (strUsername.equals("admin")){
                        databaseHelper.upgradeSession("admin",1);
                        Toast.makeText(getApplicationContext(), "Berhasil Masuk", Toast.LENGTH_SHORT).show();
                        Intent mainIntent = new Intent(LoginActivity.this, AdminActivity.class);
                        startActivity(mainIntent);
                        finish();
                    }else {
                        databaseHelper.upgradeSession("ada",1);
                        Toast.makeText(getApplicationContext(), "Berhasil Masuk", Toast.LENGTH_SHORT).show();
                        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(mainIntent);
                        finish();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Masuk Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}