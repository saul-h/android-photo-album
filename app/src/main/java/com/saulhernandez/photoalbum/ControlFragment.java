package com.saulhernandez.photoalbum;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ControlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ControlFragment extends Fragment {
    //View view;
    Button btnNext, btnPrev;
    onButtonPressListener buttonListener;
    CheckBox slideShow;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters

/*
    public Button btnNext, btnPrev;
*/

    public ControlFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ControlFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ControlFragment newInstance() {
        ControlFragment fragment = new ControlFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
/*        btnNext = getView().findViewById(R.id.btnNext);
        btnPrev = getView().findViewById(R.id.btnPrev);*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //view = inflater.inflate(R.layout.fragment_control, container, false);
        ViewGroup  view = (ViewGroup) inflater.inflate(R.layout.fragment_control, container, false);

        //create buttons/slideshow and set on click listener
        //on click, invoke the onButtonPressed() from the mainActivity
        btnNext = (Button) view.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonListener.onButtonPressed("next");
            }
        });

        btnPrev = (Button) view.findViewById(R.id.btnPrev);
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonListener.onButtonPressed("prev");
            }
        });

        slideShow =(CheckBox) view.findViewById(R.id.chkBoxSlideShow);
        slideShow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    btnPrev.setVisibility(View.INVISIBLE);
                    btnNext.setVisibility(View.INVISIBLE);
                    buttonListener.onButtonPressed("slide");

                }else{
                    btnPrev.setVisibility(View.VISIBLE);
                    btnNext.setVisibility(View.VISIBLE);
                    buttonListener.onButtonPressed("stop_slide");
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context contex){
        super.onAttach(contex);

        buttonListener =  (onButtonPressListener)getActivity();

    }
}