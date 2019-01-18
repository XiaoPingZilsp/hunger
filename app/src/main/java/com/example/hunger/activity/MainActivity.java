package com.example.hunger.activity;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hunger.R;
import com.example.hunger.fragment.LoginFragment;
import com.example.hunger.fragment.LoginFragment.loginDialogListener;
import com.example.hunger.fragment.RegisterFragment;
import com.example.hunger.fragment.RegisterFragment.registerDialogListener;
import com.example.hunger.adapter.DBAdapter;
import com.example.hunger.bean.PeopleInfo;

public class MainActivity extends AppCompatActivity implements loginDialogListener,registerDialogListener{
    private TextView show_info;
    private TextView show_info1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private void showLoginDialog(){
        android.app.FragmentManager fm = getFragmentManager();
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.show(fm,"dlg_login_dialog");
    }
    private void showRegisterDialog(){
        android.app.FragmentManager rm = getFragmentManager();
        RegisterFragment registerFragment = new RegisterFragment();
        registerFragment.show(rm,"dlg_register_dialog");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                break;
            case R.id.login:
                showLoginDialog();
                break;
            case R.id.register:
                showRegisterDialog();
                break;
            default:break;
        }
//        return super.onOptionsItemSelected(item);
        return true;
    }
    @Override
    public void onFinishEidtDialog(String inputText,String password) {

        DBAdapter adpter=new DBAdapter(this);
        adpter.openDB();
        if(adpter.queryByNamePass(inputText,password)!=null){
            Bundle bundle=new Bundle();
            bundle.putString("username",inputText);
            Intent intent=new Intent(this,MainHomeActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }else{

            Toast.makeText(this,"登录失败，请重新登录！",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFinishRegisterDialog(String register_info,PeopleInfo people) {
        //show_info.setText(register_info);
        DBAdapter adpter=new DBAdapter(this);
        adpter.openDB();
        adpter.Insert(people);

        //跳转回首页
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();

    }

}
