package com.deepakagarwal.personalmanager;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.deepakagarwal.personalmanager.data.MyDBHandler;
import com.deepakagarwal.personalmanager.model.Passwords;

public class AddNewPassword extends AppCompatActivity {

    MyDBHandler db = new MyDBHandler(AddNewPassword.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_password);
    }

    public void save (View view){

        EditText website  = findViewById(R.id.website);
        EditText username  = findViewById(R.id.username);
        EditText password  = findViewById(R.id.password);
        String website1 = website.getText().toString();
        String username1 = username.getText().toString();
        String password1 = password.getText().toString();

        Passwords p = new Passwords(LoginPage.username1,website1,username1,password1);
        db.addPassword(p);
        Toast.makeText(this, "Saving Your Data...", Toast.LENGTH_SHORT).show();
        finish();
    }


}
