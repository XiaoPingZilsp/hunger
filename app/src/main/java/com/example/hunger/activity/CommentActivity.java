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
import com.example.hunger.adapter.CommentAdapter;
import com.example.hunger.adapter.OrderAdapter;
import com.example.hunger.bean.CommentInfo;
import com.example.hunger.bean.Order;

public class CommentActivity extends AppCompatActivity {
    String id;
    TextView order_id,foodname,sum,address;
    EditText  content;
    Button  btn_ok,btn_cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment);

        Bundle bundle=this.getIntent().getExtras();
        id=bundle.getString("order_id");
        String foodname1=bundle.getString("order_foodname");
        String sum1=bundle.getString("order_sum");
        String address1=bundle.getString("order_address");
        final String username=bundle.getString("username");


        order_id = (TextView)findViewById(R.id.order_id);
        foodname = (TextView)findViewById(R.id.foodname );
        sum = (TextView )findViewById(R.id.sum);
        address = (TextView)findViewById(R.id.address);
        content = (EditText)findViewById(R.id.content);
        btn_ok = (Button )findViewById(R.id.btn_ok);
        btn_cancel = (Button )findViewById(R.id.btn_cancel);


        order_id.setText(id);
        foodname.setText(foodname1);
        sum .setText(sum1);
        address.setText(address1);



        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //从控件获取数据
                String orderid=order_id.getText().toString().trim();
                String foodname1=foodname.getText().toString().trim();
                String content1=content.getText().toString().trim();

                //上传数据
                CommentAdapter adpter=new  CommentAdapter(CommentActivity.this);
                adpter.openDB();
                CommentInfo comment=new CommentInfo(orderid,foodname1,username,content1);
                adpter.Insert(comment);

                Toast.makeText(CommentActivity.this,"评价成功！",Toast.LENGTH_LONG).show();


                //传递数据
                Bundle bundle=new Bundle();
                bundle.putString("username",username);
                Intent intent=new Intent(CommentActivity.this,MainHomeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });



        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(CommentActivity.this,"取消成功！",Toast.LENGTH_LONG).show();

                //传递数据
                Bundle bundle=new Bundle();
                bundle.putString("username",username);
                Intent intent=new Intent(CommentActivity.this,MainHomeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });

    }
}
