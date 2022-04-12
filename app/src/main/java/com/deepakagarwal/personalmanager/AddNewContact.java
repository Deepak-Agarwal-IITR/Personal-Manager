package com.deepakagarwal.personalmanager;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.deepakagarwal.personalmanager.data.MyDBHandler;
import com.deepakagarwal.personalmanager.model.Contacts;

public class AddNewContact extends AppCompatActivity {

    MyDBHandler db = new MyDBHandler(AddNewContact.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);
    }

    public void save (View view){

        EditText name  = findViewById(R.id.name);
        EditText phoneNumber  = findViewById(R.id.phoneNumber);
        EditText address  = findViewById(R.id.address);
        String name1 = name.getText().toString();
        String phoneNumber1 = phoneNumber.getText().toString();
        String address1 = address.getText().toString();

        Contacts c = new Contacts(LoginPage.username1,name1,phoneNumber1,address1);
        Log.d("dbdeepak",String.valueOf(db.getCountOfContacts()));

        // set id of contact it is wrong
        c.setId(db.getCountOfContacts());


        db.addContact(c);
        Toast.makeText(this, "Saving Your Data...", Toast.LENGTH_SHORT).show();
        finish();
    }

}
