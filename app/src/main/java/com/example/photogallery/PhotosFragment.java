package com.example.photogallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PhotosFragment extends Fragment {

int startIndex = 0;
int endIndex = 6;

    private final String image_titles[] = {
            "Img1",
            "Img2",
            "Img3",
            "Img4",
            "Img11",
            "Img21",
            "Img31",
            "Img41",
            "Img12",
            "Img22",
            "Img32",
            "Img42",
    };

    private final Integer image_ids[] = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img11,
            R.drawable.img21,
            R.drawable.img31,
            R.drawable.img41,
            R.drawable.img12,
            R.drawable.img22,
            R.drawable.img32,
            R.drawable.img42,
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_photos, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.photo_gallery);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        Bundle args = getArguments();
        if (args != null && !args.isEmpty()) {
            int argStartIndex = args.getInt("startIndex", 0);
            int argEndIndex = args.getInt("endIndex", 1);
            ArrayList<CreateList> createLists = prepareData(argStartIndex, argEndIndex);
            ImageAdapter adapter = new ImageAdapter(getActivity().getApplicationContext(), createLists);
            recyclerView.setAdapter(adapter);
        }
        return rootView;
    }

    private ArrayList<CreateList> prepareData(int startIndex, int endIndex){
        ArrayList<CreateList> theimage = new ArrayList<>();
        for(int i = startIndex; i< endIndex+1; i++){
            CreateList createList = new CreateList();
            createList.setImage_title(image_titles[i]);
            createList.setImage_ID(image_ids[i]);
            theimage.add(createList);
        }
        return theimage;
    }
//    private ArrayList<CreateList> prepareSearch(){
//        ArrayList<CreateList> theSearchImage = new ArrayList<>();
//
//        return theSearchImage;
//    }
}
