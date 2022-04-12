package com.deepakagarwal.personalmanager;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.deepakagarwal.personalmanager.data.MyDBHandler;
import com.deepakagarwal.personalmanager.model.Passwords;

public class PasswordList extends AppCompatActivity {

    MyDBHandler db = new MyDBHandler(PasswordList.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_list);
    }

    @Override
    protected void onResume(){
        super.onResume();
        ListView listPassword = findViewById(R.id.listPassword);
        ArrayList<String> password = new ArrayList<>();
        password.add("+ Add New Password");
        List<Passwords> allPasswords = db.getAllPasswords(LoginPage.username1);

        for(Passwords p : allPasswords){
            password.add(p.website+"\n"+p.username+"\n"+p.password);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, password);
        listPassword.setAdapter(arrayAdapter);

        listPassword.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent intent = new Intent( getApplicationContext(), com.deepakagarwal.personalmanager.AddNewPassword.class);
                    startActivity(intent);
                }
            }
        });
    }
}
