package com.ahmadmuzaki.jobtestk24;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AdminActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    Button logoutAdm;
    String[] daftar_member;
    ListView listView;
    Menu menu;
    protected Cursor cursor;
    public static AdminActivity adminActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        databaseHelper = new DatabaseHelper(this);

        logoutAdm = (Button)findViewById(R.id.btn_logout_admin);

        boolean checkSession = databaseHelper.checkSession("admin");
        if (checkSession == false){
            Intent loginIntent = new Intent(AdminActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }

        //Logout
        logoutAdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean updtSession = databaseHelper.upgradeSession("kosong",1);
                if (updtSession == true) {
                    Toast.makeText(getApplicationContext(), "Berhasil Keluar", Toast.LENGTH_SHORT).show();
                    Intent loginIntent = new Intent(AdminActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                    finish();
                }
            }
        });

        //Floating Button

        FloatingActionButton floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, InputDataMemberActivity.class);
                startActivity(intent);
                finish();
            }
        });

        adminActivity = this;
        databaseHelper = new DatabaseHelper(this);
        RefreshList();
    }

    public void RefreshList(){
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM user",null);
        daftar_member = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar_member[cc] = cursor.getString(1);
        }
        listView = (ListView)findViewById(R.id.Listview_member);
//        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar_member));
        listView.setAdapter(new ArrayAdapter(this, R.layout.desain_list, daftar_member));
        listView.setSelected(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar_member[arg2]; //.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = {"Lihat Data Member", "Update Data Member", "Hapus Data Member"};
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch(item){
                            case 0 :
                                Intent i = new Intent(getApplicationContext(), LihatDataActivity.class);
                                i.putExtra("username", selection);
                                startActivity(i);
                                break;
                            case 1 :
                                Intent in = new Intent(getApplicationContext(), InputDataMemberActivity.class);
                                in.putExtra("username", selection);
                                startActivity(in);
                                break;
                            case 2 :
                                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                                db.execSQL("delete from user where username = '"+selection+"'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }});
        ((ArrayAdapter)listView.getAdapter()).notifyDataSetInvalidated();
    }
}