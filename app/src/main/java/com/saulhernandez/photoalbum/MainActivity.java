package com.saulhernandez.photoalbum;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Figure out a better way to create fragments for each *.png file
        //  in drawable folder...
        ArrayList<Fragment> animalFragments = new ArrayList<>();
        animalFragments.add(PictureFragment.newInstance(R.drawable.cobra));
        animalFragments.add(PictureFragment.newInstance(R.drawable.dog));
        animalFragments.add(PictureFragment.newInstance(R.drawable.dove));
        animalFragments.add(PictureFragment.newInstance(R.drawable.eagle));
        animalFragments.add(PictureFragment.newInstance(R.drawable.lion));
        animalFragments.add(PictureFragment.newInstance(R.drawable.tiger));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerPhoto, animalFragments.get(3));
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
