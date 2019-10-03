package com.example.photogallery;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

<<<<<<< HEAD
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
=======
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

>>>>>>> ace934f502ecc300f5d6dc0bf49b9a109a71458a
    private DrawerLayout drawer;
    private int currentPhotoIndex = 0;
    private String currentPhotoPath = null;
    private ArrayList<String> photoGallery;

    static final int REQUEST_IMAGE_CAPTURE = 0;

    private String mCurrentPhotoPath;

    private MediaStore mediaStore;

    private FileProvider fileProvider;

    protected void takePicture(View v) {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

            Intent takepictureintent = new Intent(mediaStore.ACTION_IMAGE_CAPTURE);
            // ensure that there's a camera activity to handle the intent
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                // create the file where the photo should go
                File photofile = null;
                try {
                    photofile = createImageFile();
                } catch (IOException ex) {
                    // error occurred while creating the file
                }

                // continue only if the file was successfully created
                if (photofile != null) {
                    Uri photouri = fileProvider.getUriForFile(this, "com.example.mnm.photogallery.fileprovider", photofile);
                    takepictureintent.putExtra(mediaStore.EXTRA_OUTPUT, photouri);
                    startActivityForResult(takepictureintent, REQUEST_IMAGE_CAPTURE);
                }
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize navigation drawer
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById((R.id.nav_view));
        navView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Prevent reload after rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new PhotosFragment()).commit();
            navView.setCheckedItem(R.id.nav_photos);
        }

        //Initialize navigation buttons
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Navigate to view according to selected item
        switch (menuItem.getItemId()) {
            case R.id.nav_photos:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PhotosFragment()).commit();
                break;
            case R.id.nav_photo_albums:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AlbumsFragment()).commit();
                break;
        }
        // Close navigation drawer
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private ArrayList<String> populateGallery(Date minDate, Date maxDate) {
        File file = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath(), "/app/src/main/res/drawable");
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
