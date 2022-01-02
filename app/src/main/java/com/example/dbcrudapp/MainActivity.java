package com.example.dbcrudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nama, email, alamat, noHp;
    Button tambah, edit, hapus, lihat;
    SQLHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nama = findViewById(R.id.ed_nama);
        email = findViewById(R.id.ed_email);
        alamat = findViewById(R.id.ed_alamat);
        noHp = findViewById(R.id.ed_noHp);
        tambah = findViewById(R.id.btn_tambah);
        edit = findViewById(R.id.btn_edit);
        hapus = findViewById(R.id.btn_hapus);
        lihat = findViewById(R.id.btn_lihat);
        DB = new SQLHelper(this);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaTXT = nama.getText().toString();
                String emailTXT = email.getText().toString();
                String alamatTXT = alamat.getText().toString();
                String noHpTXT = noHp.getText().toString();

                Boolean cektambahdata = DB.tambahData(namaTXT,emailTXT,alamatTXT,noHpTXT);
                if (cektambahdata==true){
                    toastMessage("Data berhasil ditambahkan");
                }else {
                    toastMessage("Data gagal ditambahkan");
                }
            }
        });

        lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }




    // notifikasi menggunakan toast message
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}