package com.example.hunger.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hunger.R;
import com.example.hunger.adapter.DBAdapter;
import com.example.hunger.bean.PeopleInfo;
import com.example.hunger.fragment.PersonInfoFragment;

import static com.example.hunger.R.id.btn_mod;
import static com.example.hunger.R.id.username;

public class ModActivity extends AppCompatActivity {

    private EditText userpass_info,phonenum_info,useraddress_info;
    private Button btn_ok;
     String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_person_mod);
        //获取从MainHomeActivity中传递过来的数据
        Bundle bundle=this.getIntent().getExtras();
        username=bundle.getString("username");

    }

    public void onModClick(View v){


        phonenum_info = (EditText)findViewById(R.id.phonenum_info);
        useraddress_info = (EditText)findViewById(R.id.useraddress_info);


        String phone=phonenum_info.getText().toString();
        String address=useraddress_info.getText().toString();
        PeopleInfo people=new PeopleInfo(phone,address);


        //通过适配器修改数据
        DBAdapter adpter=new DBAdapter(this);
        adpter.openDB();
        adpter.updateByName(people,username);


    //传递数据
        Bundle bundle1=new Bundle();
        bundle1.putString("username",username);
        Intent intent=new Intent(this,MainActivity.class);
        intent.putExtras(bundle1);
        startActivity(intent);
        finish();

    }

}
