package com.example.homepage.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.homepage.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {
    private Button btnSignIn, btnSignOut;
    private TextView tvUsername;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        View view;

        if (bundle != null) {
            String strUsername = bundle.getString("user_name");
            view = inflater.inflate(R.layout.fragment_info_loged, container, false);
            tvUsername = view.findViewById(R.id.tvUsername);
            tvUsername.setText(strUsername);

            btnSignOut = view.findViewById(R.id.btnSignOut);
            btnSignOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
            });

            return view;
        }
        else {
            view = inflater.inflate(R.layout.fragment_info, container, false);

            btnSignIn = view.findViewById(R.id.btnSignIn);
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
}
