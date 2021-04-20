package com.example.marsapp.mlkit;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class MLKitVisionImage {

    private static final String TAG = "MLKIT";
    private static final String MY_CAMERA_ID = "my_camera_id";

    private void imageFromBitmap(Bitmap bitmap) {
        int rotationDegree = 0;
        // [START image_from_bitmap]
        InputImage image = InputImage.fromBitmap(bitmap, rotationDegree);
        // [END image_from_bitmap]
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void imageFromMediaImage(Image mediaImage, int rotation) {
        // [START image_from_media_image]
        InputImage image = InputImage.fromMediaImage(mediaImage, rotation);
        // [END image_from_media_image]
    }

}
