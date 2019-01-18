package com.example.hungry.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hungry.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PersonInfoFragment extends Fragment {
    public interface PersonInfoFragmentListener {
        void onFinishMod(String inputText, com.example.hungry.bean.PeopleInfo people);

        void showInfo(String username);
    }

    private EditText userpass_info, phonenum_info, useraddress_info;
    private TextView username_info, username;
    private Button btn_mod;

    public PersonInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact, container, false);
       // final View view = inflater.inflate(R.layout.fragment_personinfo, container);
        /*username=(TextView)view.findViewById(R.id.username);
        username.getText().toString();*//*
        username_info = (TextView)view.findViewById(R.id.username_info);
        userpass_info = (EditText)view.findViewById(R.id.userpass_info);
        phonenum_info = (EditText)view.findViewById(R.id.phonenum_info);
        useraddress_info = (EditText)view.findViewById(R.id.useraddress_info);
        btn_mod = (Button)view.findViewById(R.id. btn_mod);
        if(username_info.getText().toString()!=null){
        PersonInfoFragment.PersonInfoFragmentListener activity = (PersonInfoFragment.PersonInfoFragmentListener)getActivity();
        activity.showInfo(null);}
        btn_mod .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                PersonInfoFragment.PersonInfoFragmentListener activity0 = (PersonInfoFragment.PersonInfoFragmentListener)getActivity();
                PeopleInfo people=new PeopleInfo(username_info.getText().toString(),useraddress_info.getText().toString(),phonenum_info.getText().toString(),userpass_info.getText().toString());
                activity0.onFinishMod("修改成功！\n"+"用户名是："+username_info.getText().toString(),people);

            }
        });

        return view;
    }*/

    }
}