package com.saulhernandez.photoalbum;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ControlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ControlFragment extends Fragment {
    //View view;
    Button btnNext, btnPrev;
    onButtonPressListener buttonListener;
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

        btnNext = (Button) view.findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonListener.onButtonPressed("next");
            }
        });

        btnNext = (Button) view.findViewById(R.id.btnPrev);

        return view;
    }

    @Override
    public void onAttach(Context contex){
        super.onAttach(contex);

        buttonListener =  (onButtonPressListener)getActivity();

    }
}