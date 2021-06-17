package com.saulhernandez.photoalbum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PictureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PictureFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_IMAGE_ID = "imageID";

    private int mImageID;

    public PictureFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param imageID id of image resource.
     * @return A new instance of fragment PictureFragment.
     */
    public static PictureFragment newInstance(int imageID) {
        PictureFragment fragment = new PictureFragment();
        Bundle args = new Bundle();
        // passing imageID to bundle
        args.putInt(ARG_IMAGE_ID, imageID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mImageID = getArguments().getInt(ARG_IMAGE_ID);
        } else {
            // error image in case no argument was given for picture
            mImageID = R.drawable.ic_error_outline;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_picture, container, false);
        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageResource(mImageID);
        return view;
    }
}