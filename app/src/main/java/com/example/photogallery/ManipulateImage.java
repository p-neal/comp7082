package com.example.photogallery;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class ManipulateImage extends AppCompatActivity implements View.OnClickListener{
    private ArrayList<String> photoGallery;
    private String currentPhotoPath = null;
    private int currentPhotoIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Initialize navigation buttons
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonLeft = findViewById(R.id.buttonLeft);
        Button buttonRight = findViewById(R.id.buttonRight);
        buttonLeft.setOnClickListener(this);
        buttonRight.setOnClickListener(this);

        Date minDate = new Date(Long.MIN_VALUE);
        Date maxDate = new Date(Long.MAX_VALUE);
        photoGallery = populateGallery(minDate, maxDate);
        Log.d("onCreate, size", Integer.toString(photoGallery.size()));
        if (photoGallery.size() > 0)
            currentPhotoPath = photoGallery.get(currentPhotoIndex);
        displayPhoto(currentPhotoPath);
    }

    private ArrayList<String> populateGallery(Date minDate, Date maxDate) {
        File file = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath(), "/app/src/main/res/drawable-xxhdpi");
        photoGallery = new ArrayList<String>();
        File[] fList = file.listFiles();
        if (fList != null) {
            for (File f : file.listFiles()) {
                photoGallery.add(f.getPath());
            }
        }
        return photoGallery;
    }

    private void displayPhoto(String path) {
        ImageView iv = (ImageView) findViewById(R.id.ivMain);
        iv.setImageBitmap(BitmapFactory.decodeFile(path));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonLeft:
                --currentPhotoIndex;
                break;
            case R.id.buttonRight:
                ++currentPhotoIndex;
                break;
            default:
                break;
        }
        if (currentPhotoIndex < 0)
            currentPhotoIndex = 0;
        if (currentPhotoIndex >= photoGallery.size())
            currentPhotoIndex = photoGallery.size() - 1;

        currentPhotoPath = photoGallery.get(currentPhotoIndex);
        Log.d("phpotoleft, size", Integer.toString(photoGallery.size()));
        Log.d("photoleft, index", Integer.toString(currentPhotoIndex));
        displayPhoto(currentPhotoPath);
    }
}
