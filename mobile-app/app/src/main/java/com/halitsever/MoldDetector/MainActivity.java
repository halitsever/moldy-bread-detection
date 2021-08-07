package com.halitsever.MoldDetector;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.halitsever.MoldDetector.api.Api;

import com.halitsever.MoldDetector.user.UserController;
import com.halitsever.MoldDetector.util.ImageConverter;

import org.json.JSONObject;

import java.io.InputStream;


import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

public class MainActivity extends AppCompatActivity {

    public static final int SELECT_PHOTO = 1000;
    public static final int TAKE_PHOTO = 2000;
    public static final String APIKEY = "<API_KEY>";

    public void btnSelectFromGallery_Click(View view) {
    selectFromGallery();
    }


    public void btnTakePhotoFromCamera_Click(View view) {
    takePhotoFromCamera();
    }
    public void btnLogin_Click(View view) {
        UserController UserController = new UserController();
        Boolean isSigned = UserController.sessionCheck();
        if (isSigned == true) {
            UserController userController = new UserController();
            userController.Logout();
            Intent RefreshedMainActivity = new Intent(getApplicationContext(),MainActivity.class);
            Toast.makeText(this, R.string.logoutMsg, Toast.LENGTH_SHORT).show();
            startActivity(RefreshedMainActivity);
            finish();
        }
        else {
            Intent LoginActivity = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(LoginActivity);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView ApiCountNumber;
        ApiCountNumber = (TextView) findViewById(R.id.ApiCountNumber);
        getApiStatistics(ApiCountNumber);
        initializeUser();
    }

    private void selectFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, SELECT_PHOTO);
    }

    private void takePhotoFromCamera() {
        String perms[] = {
                Manifest.permission.CAMERA
        };
        int permCode = 5000;
        if (EasyPermissions.hasPermissions(getApplicationContext(), perms)) {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, TAKE_PHOTO);
        } else {
            EasyPermissions.requestPermissions(new PermissionRequest.Builder(MainActivity.this, permCode, perms).build());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        ImageConverter ConvertImage = new ImageConverter();
                        String encodedImage = ConvertImage.BitMapToString(selectedImage);
                        UserController UserController = new UserController();
                        Boolean isSigned = UserController.sessionCheck();
                        JSONObject dataJson = new JSONObject();
                        dataJson.put("base64Image", encodedImage);
                        dataJson.put("apikey", APIKEY);
                        if(isSigned == true) dataJson.put("isSigned", true);
                        else dataJson.put("isSigned",false);
                        sendImageToApi(dataJson);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }

                break;

            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        ImageConverter ImageConverter = new ImageConverter();
                        String encodedImage = ImageConverter.BitMapToString(selectedImage);
                        UserController UserController = new UserController();
                        Boolean isSigned = UserController.sessionCheck();
                        JSONObject dataJson = new JSONObject();
                        dataJson.put("base64Image", encodedImage);
                        dataJson.put("apikey", APIKEY);
                        if(isSigned == true) dataJson.put("isSigned", true);
                        else dataJson.put("isSigned",false);
                        sendImageToApi(dataJson);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                break;
        }



    }
    private void sendImageToApi(JSONObject dataJson) {
        Api ApiService = new Api();
        ApiService.sendImage(dataJson.toString(), getApplicationContext());
    }



    private void initializeUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserController UserController = new UserController();
        Boolean isSigned = UserController.sessionCheck();
        if (isSigned == true) {
            String userMail = user.getEmail();
            TextView userDisplayContent;
            Button btnLogin;
            btnLogin = (Button) findViewById(R.id.btnLogin);
            btnLogin.setText(getApplicationContext().getString(R.string.userLogout));
            userDisplayContent = (TextView) findViewById(R.id.userDisplayContent);
            userDisplayContent.setText(getApplicationContext().getString(R.string.welcomeMsg) + " " + userMail );
        }
    }

    private void getApiStatistics(TextView ApiCountNumberTextView) {
        Api ApiService = new Api();
        ApiService.getStatistics(ApiCountNumberTextView, getApplicationContext());
    }
}