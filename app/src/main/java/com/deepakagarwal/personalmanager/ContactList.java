package com.deepakagarwal.personalmanager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.deepakagarwal.personalmanager.data.MyDBHandler;
import com.deepakagarwal.personalmanager.model.Contacts;

public class ContactList extends AppCompatActivity {

    MyDBHandler db = new MyDBHandler(ContactList.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ListView listContact = findViewById(R.id.listContact);
        ArrayList<String> contact = new ArrayList<>();
        contact.add("+ Add New Contact");
        final List<Contacts> allContacts = db.getAllContacts(LoginPage.username1);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, contact);
        listContact.setAdapter(arrayAdapter);

        for (Contacts c : allContacts) {
                contact.add(c.name + "\n" + c.phoneNumber + "\n" + c.address);
        }

        listContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                if (position == 0) {
                    intent = new Intent(getApplicationContext(), AddNewContact.class);
                }
                else {
                    intent = new Intent(getApplicationContext(), com.deepakagarwal.personalmanager.EditContact.class);
                    intent.putExtra("Contact", (Parcelable) allContacts.get(position-1));
                }
                startActivity(intent);
            }
        });
    }

}