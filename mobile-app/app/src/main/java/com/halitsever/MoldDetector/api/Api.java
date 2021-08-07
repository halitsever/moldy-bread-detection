package com.halitsever.MoldDetector.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.halitsever.MoldDetector.ErrorActivity;
import com.halitsever.MoldDetector.MainActivity;
import com.halitsever.MoldDetector.R;
import com.halitsever.MoldDetector.ResultActivity;
import com.halitsever.MoldDetector.api.service.ImageData;
import com.halitsever.MoldDetector.api.service.MoldDetectorApiService;
import com.halitsever.MoldDetector.api.service.Statistics;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api extends MainActivity {
    public void sendImage(String JsonData, Context ApplicationContext) {
        Retrofit HttpClient = new Retrofit.Builder()
                .baseUrl("https://localhost:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MoldDetectorApiService ImageDataToServer = HttpClient.create(MoldDetectorApiService.class);

        ImageDataToServer.createTask(JsonData).enqueue(new Callback<ImageData>() {
            @Override
            public void onResponse(Call<ImageData> call, Response<ImageData> response) {
                Intent ResultActivity = new Intent(ApplicationContext, ResultActivity.class);
                ResultActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ResultActivity.putExtra("returnedBase64Str", response.body().getBase64Image());
                ApplicationContext.startActivity(ResultActivity);
                ((Activity) ApplicationContext).finish();
            }

            @Override
            public void onFailure(Call<ImageData> call, Throwable error) {
                Toast.makeText(ApplicationContext, "API Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                Intent ErrorActivity = new Intent(ApplicationContext, ErrorActivity.class);
                ErrorActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ApplicationContext.startActivity(ErrorActivity);
            }
        });
    }

    public void getStatistics(TextView textView, Context ApplicationContext) {
        Retrofit HttpClient = new Retrofit.Builder()
                .baseUrl("https://localhost:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MoldDetectorApiService getApiStatistics = HttpClient.create(MoldDetectorApiService.class);
        getApiStatistics.getStatistics().enqueue(new Callback<Statistics> () {
            @Override
            public void onResponse(Call<Statistics> call, Response<Statistics> response) {
                textView.setText(response.body().getStatistics() + " " + ApplicationContext.getString(R.string.countMsg));
            }

            @Override
            public void onFailure(Call<Statistics> call, Throwable t) {
                Toast.makeText(ApplicationContext, "Api Error: " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
