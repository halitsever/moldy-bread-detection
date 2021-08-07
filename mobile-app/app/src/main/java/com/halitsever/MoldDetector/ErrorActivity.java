package com.halitsever.MoldDetector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ErrorActivity extends AppCompatActivity {

    Button recheckConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);

        recheckConnection = (Button) findViewById(R.id.recheckConnection);

        recheckConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnSplash = new Intent(getApplicationContext(), SplashActivity.class);
                startActivity(returnSplash);
                finish();
            }
        });
    }
}