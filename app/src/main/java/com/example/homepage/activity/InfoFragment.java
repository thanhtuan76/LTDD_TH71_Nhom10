package com.example.homepage.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.homepage.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {
    private Button btnSignIn, btnSignUp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        btnSignIn = view.findViewById(R.id.btnSignIn);
        btnSignUp = view.findViewById(R.id.btnSignUp);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SigninActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
