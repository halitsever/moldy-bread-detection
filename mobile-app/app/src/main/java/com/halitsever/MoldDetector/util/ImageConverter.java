package com.halitsever.MoldDetector.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.widget.ImageView;

import com.halitsever.MoldDetector.ResultActivity;

import java.io.ByteArrayOutputStream;

public class ImageConverter extends ResultActivity {
    public String BitMapToString(Bitmap bitmap) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);

    }

    public void BitMapToImageView(ImageView imageView, String base64String){
        base64String =  base64String.replace("b'","").replace("'","").replace("\n", "").replace("\\", "");
        if (!base64String.isEmpty()) {
            byte[] bytes = Base64.decode(base64String, Base64.DEFAULT);
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
        }
        else {
            android.util.Log.e("Error", "Base64 str can't be converted to bitmap");
        }
    }
}
