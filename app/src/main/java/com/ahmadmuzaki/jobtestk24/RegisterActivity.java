package com.ahmadmuzaki.jobtestk24;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    Button login, register;
    EditText username, password, passwordConf, nama, tanggal_lahir, alamat, jenis_kelamin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseHelper = new DatabaseHelper(this);

        username = findViewById(R.id.editText_username_regis);
        password = findViewById(R.id.editText_password_regis);
        passwordConf = findViewById(R.id.editText_konfirm_password);
        login = findViewById(R.id.btn_login_regis);
        register =  findViewById(R.id.btn_register_regis);
        nama = findViewById(R.id.editText_nama_regis);
        tanggal_lahir = findViewById(R.id.editText_tanggal_lahir_regis);
        alamat = findViewById(R.id.editText_alamat_regis);
        jenis_kelamin = findViewById(R.id.editText_jenis_kelamin_regis);

        //Login -> Jika klik login maka akan pindah ke halaman login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });

        //register
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUsername = username.getText().toString();
                String strPassword = password.getText().toString();
                String strPasswordConf = passwordConf.getText().toString();
                String strNama = nama.getText().toString();
                String strTanggalLahir = tanggal_lahir.getText().toString();
                String strAlamat = alamat.getText().toString();
                String strJenisKelamin = jenis_kelamin.getText().toString();

                //menyamakan password dengan passwordconf
                if (strPassword.equals(strPasswordConf)){
                    boolean daftar = databaseHelper.insertUser(strUsername, strPassword, strNama, strTanggalLahir, strAlamat, strJenisKelamin);
                    if (daftar){
                        Toast.makeText(getApplicationContext(), "Daftar Berhasil", Toast.LENGTH_SHORT).show();
                        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(loginIntent);
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(), "Daftar Gagal", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Password Tidak Sesuai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}