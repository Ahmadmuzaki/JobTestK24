package com.ahmadmuzaki.jobtestk24;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputDataMemberActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    Button simpan;
    EditText username, password, passwordConf, nama, tanggal_lahir, alamat, jenis_kelamin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_member);
        databaseHelper = new DatabaseHelper(this);

        username = findViewById(R.id.editText_username_tambah);
        password = findViewById(R.id.editText_password_tambah);
        passwordConf = findViewById(R.id.editText_konfirm_password_tambah);
//        kembali = findViewById(R.id.btn_kembali_tambah);
        simpan =  findViewById(R.id.btn_simpan_tambah);
        nama = findViewById(R.id.editText_nama_regis_tambah);
        tanggal_lahir = findViewById(R.id.editText_tanggal_lahir_regis_tambah);
        alamat = findViewById(R.id.editText_alamat_regis_tambah);
        jenis_kelamin = findViewById(R.id.editText_jenis_kelamin_regis_tambah);

        //Login -> Jika klik login maka akan pindah ke halaman login
//        kembali.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent loginIntent = new Intent(InputDataMemberActivity.this, AdminActivity.class);
//                startActivity(loginIntent);
//                finish();
//            }
//        });

        //register
        simpan.setOnClickListener(new View.OnClickListener() {
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
                        Toast.makeText(getApplicationContext(), "Data Berhasil Ditambah", Toast.LENGTH_SHORT).show();
                        Intent loginIntent = new Intent(InputDataMemberActivity.this, AdminActivity.class);
                        startActivity(loginIntent);
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(), "Data Gagal Ditambah", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Password Tidak Sesuai", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }
}