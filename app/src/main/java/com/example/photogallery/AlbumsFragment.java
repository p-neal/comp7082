package com.example.photogallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlbumsFragment extends Fragment implements View.OnClickListener {

    Button buttonLeft;
    Button buttonRight;
    ImageView image;
    private View rootView;

    private int currentPhotoIndex = 1;
    Map<Integer, String> map = new HashMap<Integer, String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        rootView = inflater.inflate(R.layout.fragment_albums, container, false);

        image = (ImageView) rootView.findViewById(R.id.ivMain);
        image.setImageResource(R.drawable.img1);

        buttonLeft = (Button) rootView.findViewById(R.id.buttonLeft);

        buttonRight = (Button) rootView.findViewById(R.id.buttonRight);

        buttonLeft.setOnClickListener(this);

        buttonRight.setOnClickListener(this);

        return rootView;
    }


    @Override
    public void onClick(View v) {

        Map<Integer, String> imageEntry = populateGallery();

        switch (v.getId()) {
            case R.id.buttonLeft:
                --currentPhotoIndex;
                break;
            case R.id.buttonRight:
                ++currentPhotoIndex;
                break;
            default:
                break;
        }

        imageDrawable(currentPhotoIndex);
    }

    private void imageDrawable(int currentPhotoIndex) {
        Map<Integer, String> imageEntry = populateGallery();

        List<String> values = new ArrayList(imageEntry.values());

        int i=1;
        if(currentPhotoIndex >= values.size()-1)
        {
            currentPhotoIndex =1;
            i = 1;
        }

        for(String value: values)
        {
            if(currentPhotoIndex < 0) {
                currentPhotoIndex = 1;
                i = 1;
            }
            if(i == currentPhotoIndex)
            {
                image.setImageResource(getEntry(imageEntry, value));
            }
            i++;
        }
        i = 1;
    }

    private Integer getEntry(Map<Integer, String> imageEntry, String value)
    {
        Integer key = 0;

        for (Map.Entry<Integer, String> entry : imageEntry.entrySet()) {
            if (entry.getValue().equals(value)) {
                key =entry.getKey();
            }
        }

        return key;
    }

    private Map<Integer, String> populateGallery()
    {

        map.put(R.drawable.img1, "Img1");
        map.put(R.drawable.img2, "Img2");
        map.put(R.drawable.img3, "Img3");
        map.put(R.drawable.img4, "Img4");
        map.put(R.drawable.img11, "Img11");
        map.put(R.drawable.img21, "Img21");
        map.put(R.drawable.img31, "Img31");
        map.put(R.drawable.img41, "Img41");
        map.put(R.drawable.img12, "Img12");
        map.put(R.drawable.img22, "Img22");
        map.put(R.drawable.img32, "Img32");
        map.put(R.drawable.img42, "Img42");

        return map;
    }
}