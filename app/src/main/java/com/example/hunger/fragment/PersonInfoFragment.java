package com.example.hunger.fragment;


import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hunger.R;
import com.example.hunger.activity.MainActivity;
import com.example.hunger.bean.PeopleInfo;


public class PersonInfoFragment extends Fragment implements TextView.OnEditorActionListener {
    public interface PersonInfoFragmentListener {
        void onFinishMod();
        void onFinishModPass();
        PeopleInfo[] showInfo();
    }

    private TextView userpass_info, phonenum_info, useraddress_info;
    private TextView username_info, username;
    private Button btn_mod,btn_mod_pass;

    public PersonInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_personinfo, container, false);

        username_info = (TextView)view.findViewById(R.id.username_info);

        btn_mod = (Button)view.findViewById(R.id. btn_mod);
        btn_mod_pass = (Button)view.findViewById(R.id. btn_mod_pass);

        PersonInfoFragment.PersonInfoFragmentListener activity = (PersonInfoFragment.PersonInfoFragmentListener)getActivity();
        PeopleInfo[] peoples=activity.showInfo();

        String result="";
        result=peoples[0].showInfo();
        username_info.setText(result);



        btn_mod .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PersonInfoFragment.PersonInfoFragmentListener activity0 = (PersonInfoFragment.PersonInfoFragmentListener)getActivity();
                activity0.onFinishMod();
            }
        });

        btn_mod_pass .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                PersonInfoFragment.PersonInfoFragmentListener activity0 = (PersonInfoFragment.PersonInfoFragmentListener)getActivity();
                activity0.onFinishModPass();
            }
        });

        return view;
    }


    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        return false;
    }

    }

