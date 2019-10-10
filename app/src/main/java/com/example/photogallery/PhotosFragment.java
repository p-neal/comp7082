package com.example.photogallery;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDescriptor;
import com.drew.metadata.exif.ExifSubIFDDirectory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class PhotosFragment extends Fragment {

int startIndex = 0;
int endIndex = 6;

    private final String image_titles[] = {
            "Img1",
            "Img2",
            "Img3",
            "Img4",
            "Img5",
            "Img6",
            "Img7",
            "Img8",
            "Img9",
            "Img10",
            "Img11",
            "Img12",
    };

    private final Integer image_ids[] = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6,
            R.drawable.img7,
            R.drawable.img8,
            R.drawable.img9,
            R.drawable.img10,
            R.drawable.img11,
            R.drawable.img12,
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
            int argStartIndex = args.getInt("STARTDATE", 0);
            int argEndIndex = args.getInt("ENDDATE", 1);  // 5
            ArrayList<CreateList> createLists = prepareData(argStartIndex, argEndIndex);
            ImageAdapter adapter = new ImageAdapter(getActivity().getApplicationContext(), createLists);
            recyclerView.setAdapter(adapter);
        }
        return rootView;
    }

    private ArrayList<CreateList> prepareData(int startIndex, int endIndex){
        ArrayList<CreateList> images = new ArrayList<>();
//        int[] drawableId = new int[12];
        for(int i = startIndex; i < endIndex+1; i++){

            String drawableName = "img" + (i + 1) + ".jpg";
//            drawableId[i] = getResources().getIdentifier(drawableName, "drawable", "com.example.photogallery");

            File img = new File(Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + R.drawable.img1 + ".jpg").toString());
            try {
                Metadata metadata = ImageMetadataReader.readMetadata(img);
                CreateList createList = new CreateList();

                ExifSubIFDDirectory directory
                        = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);

                Date date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);

                createList.setImage_title(date.toString());
                createList.setImage_ID(image_ids[i]);
                images.add(createList);
            } catch (ImageProcessingException e) {
                // do nothing
            } catch (IOException e) {
                // do nothing
                String aaa = "no";
            }



        }
        return images;
    }
}
