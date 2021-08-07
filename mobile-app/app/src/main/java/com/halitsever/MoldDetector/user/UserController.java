package com.halitsever.MoldDetector.user;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.halitsever.MoldDetector.LoginActivity;
import com.halitsever.MoldDetector.MainActivity;

public class UserController extends LoginActivity {
    public void addUser(String email, String password, Context ApplicationContext) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(ApplicationContext, "Error! The username or password is incorrect.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Intent RefreshedMainActivity = new Intent(ApplicationContext,MainActivity.class);
                            RefreshedMainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            ApplicationContext.startActivity(RefreshedMainActivity);
                            Toast.makeText(ApplicationContext, "Register successful", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    public void loginUser(String email, String password, Context ApplicationContext) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(ApplicationContext, "Error! The username or password is incorrect.",
                                    Toast.LENGTH_SHORT).show();
                        }
                     else {
                            Intent RefreshedMainActivity = new Intent(ApplicationContext,MainActivity.class);
                            RefreshedMainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            ApplicationContext.startActivity(RefreshedMainActivity);
                    }
                    }
                });

    }


    public boolean sessionCheck() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null) {
            return false;
        }
        else {
            return true;
        }
    }

    public void Logout() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
    }
}
