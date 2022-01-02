package com.example.dbcrudapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    SQLHelper DB;
    private ListView mListView;
    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mListView = (ListView) findViewById(R.id.listview);
        DB = new SQLHelper(this);

        isiListView();
    }

    private void isiListView(){
        Cursor data = DB.getData();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()){
            listData.add(data.getString(1));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);

        //set an onItemClickListener to the ListView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String nama = adapterView.getItemAtPosition(i).toString();
                String email = adapterView.getItemAtPosition(i).toString();
                String alamat = adapterView.getItemAtPosition(i).toString();
                String noHp = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + nama);

                Cursor data = DB.getItemID(nama); //get the id associated with that name
                int id = -1;
                while(data.moveToNext()){
                    id = data.getInt(0);
                }
                if(id > -1){
                    Log.d(TAG, "onItemClick: The ID is: " + id);
                    Intent editIntent = new Intent(SecondActivity.this, EditDataActivity.class);
                    editIntent.putExtra("id",id);
                    editIntent.putExtra("nama",nama);
                    editIntent.putExtra("email",email);
                    startActivity(editIntent);
                }
                else{
                    toastMessage("tidak ada id dengan nama tersebut");
                }
            }
        });
    }

    // notifikasi menggunakan toast message
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}