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

public class ModPassActivity extends AppCompatActivity {
    //private TextView;
    private EditText userpass_info;
    private Button btn_ok;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_pass);
        //获取从MainHomeActivity中传递过来的数据
        Bundle bundle=this.getIntent().getExtras();
        username=bundle.getString("username");

    }

    public void onModPassClick(View v){

        userpass_info = (EditText)findViewById(R.id.userpass_info);


        String userpass=userpass_info.getText().toString();

        PeopleInfo people=new PeopleInfo(userpass);

        //通过适配器修改数据
        DBAdapter adpter=new DBAdapter(this);
        adpter.openDB();
        adpter.updatePassword(people,username);


        //传递数据
        Bundle bundle1=new Bundle();
        bundle1.putString("username",username);
        Intent intent=new Intent(this,MainActivity.class);
        intent.putExtras(bundle1);
        startActivity(intent);
        finish();

    }

}
