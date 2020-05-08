package com.marand.espressouitest.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import com.marand.espressouitest.R;

public class IntentCameraActivity extends AppCompatActivity {
    private static final String TAG = IntentCameraActivity.class.getSimpleName();
    private static final int REQUEST_IMAGE_CAPTURE = 100;
    public static final String KEY_IMAGE_DATA = "data";
    private ImageView image;
    private Button button_launch_camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_camera);

        initView();
        button_launch_camera.setOnClickListener(view -> dispatchCameraIntent());
    }

// -------------------------------------------------------------------------------------------------

    private void initView() {
        button_launch_camera = findViewById(R.id.button_launch_camera);
        image = findViewById(R.id.image);
    }

    private void dispatchCameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

// -------------------------------------------------------------------------------------------------

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras == null || !extras.containsKey(KEY_IMAGE_DATA)) {
                return;
            }
            Bitmap imageBitmap = (Bitmap) extras.get(KEY_IMAGE_DATA);
            image.setImageBitmap(imageBitmap);
        }
    }

}
