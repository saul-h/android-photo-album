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
    Button stopSlide;

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


        } else if(msg.equals("slide")){
            //create slideshow
            runSlideShow();
        }
        textView.setText(String.valueOf(imagePosition + 1 +"/6"));
    }

    void runSlideShow(){
        stopSlide = findViewById(R.id.stopSlide);
        stopSlide.setVisibility(View.VISIBLE);
        stopSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        startThread();

    }
    public void startThread() {
        SlideThread runnable = new SlideThread(6);
        new Thread(runnable).start();
    }

    class SlideThread implements Runnable{
        int seconds;

        SlideThread(int seconds){
            this.seconds = seconds;
        }

        @Override
        public void run() {
            textView.setText("Running Slideshow");
            for(int i = 0; i < seconds; i++){
                try {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContainerPhoto, animalFragments.get(i));//display image by image position
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
