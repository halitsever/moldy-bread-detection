package com.halitsever.MoldDetector;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.halitsever.MoldDetector.user.UserController;


public class LoginActivity extends AppCompatActivity {

    EditText userEmail;
    EditText userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void btnFirebaseLogin_Click(View view) {
        userEmail = (EditText) findViewById(R.id.userEmail);
        userPassword = (EditText) findViewById(R.id.userPassword);
        UserController UserController = new UserController();
        UserController.loginUser(userEmail.getText().toString(), userPassword.getText().toString(), getApplicationContext());
    }

    public void btnFirebaseRegister_Click(View view) {
        userEmail = (EditText) findViewById(R.id.userEmail);
        userPassword = (EditText) findViewById(R.id.userPassword);
        UserController UserController = new UserController();
        UserController.addUser(userEmail.getText().toString(), userPassword.getText().toString(), getApplicationContext());

    }
}