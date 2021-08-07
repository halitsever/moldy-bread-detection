package com.halitsever.MoldDetector;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.halitsever.MoldDetector.util.ImageConverter;

public class ResultActivity extends AppCompatActivity {

    ImageView resultImg;
    Button btnReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ImageView resultImgZoom = findViewById(R.id.resultImg);
        resultImgZoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultImgZoom.requestLayout();
                resultImgZoom.getLayoutParams().height = 1000;
                resultImgZoom.getLayoutParams().width = 1000;

                resultImgZoom.clearAnimation();

            }
        });
        try {

                resultImg = (ImageView) findViewById(R.id.resultImg);
                Bundle returnedBase64Image = getIntent().getExtras();
                if(returnedBase64Image != null) {
                String ImageString = returnedBase64Image.getString("returnedBase64Str");

                ImageConverter ImageConverter = new ImageConverter();
                ImageConverter.BitMapToImageView(resultImg, ImageString);

                final Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake);
                resultImg.startAnimation(animShake);



                btnReturn = (Button) findViewById(R.id.btnReturn);

                btnReturn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent backToMain = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(backToMain);
                        finish();
                    }
                });
            }
        }
        catch(Exception error) {
            android.util.Log.d("Hata", error.toString());
        }
    }
}