package com.deepakagarwal.personalmanager;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.deepakagarwal.personalmanager.data.MyDBHandler;
import com.deepakagarwal.personalmanager.model.Contacts;

public class EditContact extends AppCompatActivity {

    Contacts c;
    MyDBHandler dbHandler = new MyDBHandler(EditContact.this);
    EditText[] t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        Intent intent = getIntent();
        c = (Contacts)intent.getParcelableExtra("Contact");
        t = new EditText[3];
        t[0] = (EditText)findViewById(R.id.nameOfChosenContact);
        t[1] = (EditText)findViewById(R.id.phoneOfChosenContact);
        t[2] = (EditText)findViewById(R.id.addressOfChosenContact);
        t[0].setText(""+c.name);
        t[1].setText(""+c.phoneNumber);
        t[2].setText(""+c.address);
        for(int x = 0;x<3;x++){
            t[x].setEnabled(false);
        }
    }

    public void editContact(View v){
        for(int x = 0;x<3;x++){
            t[x].setEnabled(true);
        }
        Button save = findViewById(R.id.saveButton);
        save.setVisibility(View.VISIBLE);
    }

    public void save(View view){
        c.name = t[0].getText().toString();
        c.phoneNumber = t[1].getText().toString();
        c.address = t[2].getText().toString();

        dbHandler.updateContact(c);
        finish();
    }
    public void deleteContact(View v){
        Log.d("editDeepak",String.valueOf(c.id));
        dbHandler.deleteContact(c);
        finish();
    }
}