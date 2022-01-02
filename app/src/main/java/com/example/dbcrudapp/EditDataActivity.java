package com.example.dbcrudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditDataActivity extends AppCompatActivity {

    private static final String TAG = "EditDataActivity";

    private Button btn_edit, btn_hapus;
    private EditText editable_nama,editable_email;

    SQLHelper DB;

    private String selectedName, selectedEmail;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        btn_edit = (Button) findViewById(R.id.btn_edit);
        btn_hapus = (Button) findViewById(R.id.btn_hapus);
        editable_nama = (EditText) findViewById(R.id.editable_nama);
        editable_email = (EditText) findViewById(R.id.editable_email);
        DB = new SQLHelper(this);

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id", -1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectedName = receivedIntent.getStringExtra("nama");
        selectedEmail = receivedIntent.getStringExtra("email");


        //set the text to show the current selected name
        editable_nama.setText(selectedName);
        editable_email.setText(selectedEmail);
//        editable_item.setText(selectedEmail);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editable_nama.getText().toString();
                if (!item.equals("")) {
                    DB.updateNama(item, selectedID, selectedName);
                } else {
                    toastMessage("You must enter a name");
                }
            }
        });

        btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB.deleteNama(selectedID, selectedName);
                editable_nama.setText("");
                toastMessage("removed from database");
            }
        });

    }

    /**
     * customizable toast
     *
     * @param message
     */
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}