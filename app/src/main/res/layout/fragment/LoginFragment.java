package com.example.hungry.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hungry.R;
import com.example.hungry.bean.PeopleInfo;

public class LoginFragment extends DialogFragment implements TextView.OnEditorActionListener {

    public interface loginDialogListener{
        void onFinishEidtDialog(String inputText, PeopleInfo people);
    }
    private EditText usernameET,passwordET;
    private Button login,cancel;
    public LoginFragment(){
        //Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        //Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_login,container);
        usernameET = (EditText)view.findViewById(R.id.username);
        passwordET = (EditText)view.findViewById(R.id.password);
        getDialog().setTitle("Login");
        usernameET.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        passwordET.setOnEditorActionListener(this);
        login = (Button)view.findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginDialogListener activity = (loginDialogListener)getActivity();
                PeopleInfo people=new PeopleInfo(usernameET.getText().toString(),null,null,passwordET.getText().toString());
                activity.onFinishEidtDialog(usernameET.getText().toString(),people);
                dismissAllowingStateLoss();
            }
        });
        cancel = (Button)view.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissAllowingStateLoss();
            }
        });
        return view;
    }
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
       /* if (EditorInfo.IME_ACTION_DONE == actionId){
            //Return input text to activity
            loginDialogListener activity = (loginDialogListener)getActivity();
            PeopleInfo people=new PeopleInfo(usernameET.getText().toString(),null,null,passwordET.getText().toString());
            activity.onFinishEidtDialog(usernameET.getText().toString(),people);
            this.dismiss();
            return true;
        }*/
        return false;
    }
}
