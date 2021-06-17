package com.saulhernandez.photoalbum;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements onButtonPressListener {

    int imagePosition = 0;
    TextView textView;
    ArrayList<Fragment> animalFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        // TODO: Figure out a better way to create fragments for each *.png file
        //  in drawable folder...
        animalFragments = new ArrayList<>();
        animalFragments.add(PictureFragment.newInstance(R.drawable.cobra));
        animalFragments.add(PictureFragment.newInstance(R.drawable.dog));
        animalFragments.add(PictureFragment.newInstance(R.drawable.dove));
        animalFragments.add(PictureFragment.newInstance(R.drawable.eagle));
        animalFragments.add(PictureFragment.newInstance(R.drawable.lion));
        animalFragments.add(PictureFragment.newInstance(R.drawable.tiger));

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerPhoto, animalFragments.get(imagePosition));//display image by image position
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onButtonPressed(String msg) {


        if(msg.equals("next")){
            if(imagePosition == 5){
                imagePosition = -1;
            }
            imagePosition = imagePosition + 1;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainerPhoto, animalFragments.get(imagePosition));//display image by image position
            transaction.addToBackStack(null);
            transaction.commit();

            //imagePosition++;


        }else if(msg.equals("prev")){
            //previous picture
            if(imagePosition == 0) {
                imagePosition = 6;
            }
            imagePosition = imagePosition - 1;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainerPhoto, animalFragments.get(imagePosition));//display image by image position
            transaction.addToBackStack(null);
            transaction.commit();


        }


        textView.setText(String.valueOf(imagePosition));
    }
}
