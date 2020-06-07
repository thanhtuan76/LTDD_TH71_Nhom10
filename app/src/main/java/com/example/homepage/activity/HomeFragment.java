package com.example.homepage.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.homepage.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private ViewFlipper view_flipper;
    private RecyclerView recyclerView;

    //View flipper
    int[] imgs = {
            R.drawable.s1,
            R.drawable.s2,
            R.drawable.s3,
            R.drawable.s4,
            R.drawable.s5,
            R.drawable.s6
    };

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        view_flipper = view.findViewById(R.id.v_flipper);
        recyclerView = view.findViewById(R.id.recycleViewMain);

        //View flipper
        for (int i = 0; i < imgs.length; i++) {
            flip_img(imgs[i]);
        }
        return view;
    }

    //View flipper function
    public void flip_img(int i) {
        ImageView view = new ImageView(getActivity());
        view.setBackgroundResource(i);
        view_flipper.addView(view);
        view_flipper.setFlipInterval(4000);
        view_flipper.setAutoStart(true);
        view_flipper.setInAnimation(getActivity(), android.R.anim.slide_in_left);
        view_flipper.setOutAnimation(getActivity(), android.R.anim.slide_out_right);
    }

}
