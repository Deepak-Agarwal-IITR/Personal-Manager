package com.deepakagarwal.personalmanager;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import com.deepakagarwal.personalmanager.data.MyDBHandler;

public class ListData extends AppCompatActivity {
    MyDBHandler db = new MyDBHandler(ListData.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        TextView nameOfUser = (TextView)findViewById(R.id.nameOfUser);
        nameOfUser.setText("Hello "+db.getNameOfUser(com.deepakagarwal.personalmanager.LoginPage.username1));

        ListView listData = findViewById(R.id.listData);
        ArrayList<String> typeOfData = new ArrayList<>();
        typeOfData.add("Contacts");
        typeOfData.add("Passwords");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, typeOfData);
        listData.setAdapter(arrayAdapter);

        listData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent intent = new Intent( getApplicationContext(), com.deepakagarwal.personalmanager.ContactList.class);
                    startActivity(intent);
                }
                else if(position == 1){
                    Intent intent = new Intent( getApplicationContext(), com.deepakagarwal.personalmanager.PasswordList.class);
                    startActivity(intent);
                }

            }
        });

    }
}
