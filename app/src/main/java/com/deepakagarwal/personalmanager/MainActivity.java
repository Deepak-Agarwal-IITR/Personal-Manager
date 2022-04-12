package com.deepakagarwal.personalmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.deepakagarwal.personalmanager.data.MyDBHandler;
import com.deepakagarwal.personalmanager.model.User;

public class MainActivity extends AppCompatActivity {

    MyDBHandler db = new MyDBHandler(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void save (View view){

        EditText name  = findViewById(R.id.name);
        EditText age  = findViewById(R.id.age);
        EditText username  = findViewById(R.id.username);
        EditText passcode  = findViewById(R.id.passcode);
        String name1 = name.getText().toString();
        String age1 = age.getText().toString();
        String username1 = username.getText().toString();
        String passcode1 = passcode.getText().toString();

        User u = new User(name1,age1,username1,passcode1);
        if(name1.equals("") == false) {
            if(age1.equals("") == false) {
                if (username1.equals("") == false){
                    if(passcode1.equals("") == false) {
                        if (db.addUser(u)) {

                            Toast.makeText(this, "Successfully Registered...\nClick Login to login", Toast.LENGTH_SHORT).show();



                        } else {
                            Toast.makeText(this, "User already Registered...\nSelect different username", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(this, "Passcode can't be empty.", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(this, "Username can't be empty.", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(this, "Age can't be empty.", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Name can't be empty.", Toast.LENGTH_SHORT).show();
        }

    }
    public void login(View view){
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);

    }
}