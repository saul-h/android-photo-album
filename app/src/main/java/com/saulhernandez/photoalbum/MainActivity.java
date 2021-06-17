package com.saulhernandez.photoalbum;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        // display image by image position
        transaction.replace(R.id.fragmentContainerPhoto, animalFragments.get(imagePosition));
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onButtonPressed(String msg) {

        //if user press next, then move to the next fragment in the list
        if(msg.equals("next")){
            if(imagePosition == animalFragments.size() - 1){
                imagePosition = -1;
            }
            imagePosition = imagePosition + 1;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // display image by image position
            transaction.replace(R.id.fragmentContainerPhoto, animalFragments.get(imagePosition));
            transaction.addToBackStack(null);
            transaction.commit();

        }else if(msg.equals("prev")){ //likewise if user press prev, the more the previous fragment in the list

            // previous picture
            if(imagePosition == 0) {
                imagePosition = animalFragments.size();
            }
            imagePosition = imagePosition - 1;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // display image by image position
            transaction.replace(R.id.fragmentContainerPhoto, animalFragments.get(imagePosition));
            transaction.addToBackStack(null);
            transaction.commit();


        } else if(msg.equals("slide")){
            //create slideshow
            runSlideShow();
        }
        textView.setText(String.valueOf(imagePosition + 1 +"/" + animalFragments.size()));
    }

    void runSlideShow(){
        startThread();
    }

    public void startThread() {
        SlideThread runnable = new SlideThread(6);
        new Thread(runnable).start();
    }

    // Create a seperate thread to run the slideshow
    class SlideThread implements Runnable{
        int seconds;

        SlideThread(int seconds){
            this.seconds = seconds;
        }

        @Override
        public void run() {
            textView.setText("Running Slideshow");
            for(int i = 0; i < seconds; i++) {
                try {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    //display image by image position
                    transaction.replace(R.id.fragmentContainerPhoto, animalFragments.get(i));
                    transaction.addToBackStack(null);
                    transaction.commit();
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
