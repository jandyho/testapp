package com.jandy.testapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CAMERA = 111;
    private static final int REQUEST_GALLERY = 112;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button camera = (Button) findViewById(R.id.takeCamera);
        Button gallery = (Button) findViewById(R.id.goGallery);
        camera.setOnClickListener(this);
        gallery.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.takeCamera :
            {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp1.jpg");
                Uri mImageCaptureUri = Uri.fromFile(f);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                startActivityForResult(intent, REQUEST_CAMERA);
                break;
            }
            case R.id.goGallery :
            {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_GALLERY);
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == REQUEST_CAMERA) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                Bundle extras = data.getExtras();
                Bitmap photo = extras.getParcelable("data");
            }
        }
    }
}
