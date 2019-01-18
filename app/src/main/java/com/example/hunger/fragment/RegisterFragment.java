package com.example.hunger.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;

import com.example.hunger.R;
import com.example.hunger.bean.PeopleInfo;

public class RegisterFragment extends DialogFragment implements TextView.OnEditorActionListener {

    public interface registerDialogListener{
        void onFinishRegisterDialog(String register_info, PeopleInfo people);
    }
    private TextView username_reg, userpass_reg, user_phone,user_address;
    private TextView show_info;
    private Button register_reg,cancel_reg;

    public RegisterFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_register, container);

        username_reg = (TextView)view.findViewById(R.id.username_reg);
        user_phone= (TextView)view.findViewById(R.id.user_phone);
        user_address=(TextView)view.findViewById(R.id.user_address);
        userpass_reg = (TextView)view.findViewById(R.id.password_reg);

        getDialog().setTitle("Register");
        username_reg.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        userpass_reg.setOnEditorActionListener(this);

        register_reg = (Button)view.findViewById(R.id.register_reg);



        register_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerDialogListener activity = (registerDialogListener)getActivity();


                        PeopleInfo people=new PeopleInfo(username_reg.getText().toString(),user_address.getText().toString(),user_phone.getText().toString(),userpass_reg.getText().toString());
                        activity.onFinishRegisterDialog("注册成功！\n"+"用户名是："+username_reg.getText().toString(),people);


                dismissAllowingStateLoss();
            }
        });
        cancel_reg = (Button)view.findViewById(R.id.cancel_reg);
        cancel_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissAllowingStateLoss();
            }
        });
        return view;
    }

    private boolean check_username(TextView username_reg){
        String name = username_reg.getText().toString();
        int length = name.length();
        return length!=0;
    }
    private boolean check_paw(TextView password_reg,TextView rpassword_reg){
        String psw,rpsw;
        int length_psw,length_rpsw;
        psw = password_reg.getText().toString();
        rpsw = rpassword_reg.getText().toString();
        length_psw = psw.length();
        length_rpsw = rpsw.length();
        return (psw.equals(rpsw))&&((length_psw!=0)||(length_rpsw!=0));
    }
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId){
            //Return input text to activity
            LoginFragment.loginDialogListener activity = (LoginFragment.loginDialogListener)getActivity();
            PeopleInfo people=new PeopleInfo(username_reg.getText().toString(),user_address.getText().toString(),user_phone.getText().toString(),userpass_reg.getText().toString());
            activity.onFinishEidtDialog("注册成功！\n"+"用户名是："+username_reg.getText().toString()
                    ,userpass_reg.getText().toString());
            this.dismiss();
            return true;
        }
        return false;
    }
}
