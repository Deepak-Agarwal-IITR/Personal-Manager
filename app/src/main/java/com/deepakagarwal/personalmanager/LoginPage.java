package com.deepakagarwal.personalmanager;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.deepakagarwal.personalmanager.data.MyDBHandler;

public class LoginPage extends AppCompatActivity {

    MyDBHandler db = new MyDBHandler(LoginPage.this);
    public static String username1;
    String passcode1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
    }
    public void login(View view){
        EditText username  = findViewById(R.id.username);
        EditText passcode  = findViewById(R.id.passcode);
        username1 = username.getText().toString();
        passcode1 = passcode.getText().toString();

        if(db.checkPasscode(username1, passcode1)) {
            Intent intent = new Intent(this, ListData.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Username or Password is wrong.", Toast.LENGTH_SHORT).show();
        }
    }
    public void createNew(View view){
        finish();
    }
}
