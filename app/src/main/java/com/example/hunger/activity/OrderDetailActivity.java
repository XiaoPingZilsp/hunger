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
import com.example.hunger.adapter.OrderAdapter;
import com.example.hunger.bean.Order;

public class OrderDetailActivity extends AppCompatActivity {
    TextView order_id,order_foodname,order_price,order_numb,order_sum,order_address;
    Button order_ok,order_mod,order_delete,order_back;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_item_detail);
        Bundle bundle=this.getIntent().getExtras();
        String id=bundle.getString("order_id");
        String foodname=bundle.getString("order_foodname");
        String price=bundle.getString("order_price");
        String numb=bundle.getString("order_numb");
        String sum=bundle.getString("order_sum");
        String address=bundle.getString("order_address");
        username=bundle.getString("username");


        order_id = ( TextView)findViewById(R.id.order_id);
        order_foodname = (TextView)findViewById(R.id.order_foodname);
        order_price = ( TextView)findViewById(R.id.order_price );
        order_numb = (TextView )findViewById(R.id.order_numb);
        order_sum = (TextView)findViewById(R.id.order_sum);
        order_address = (TextView)findViewById(R.id.order_address);
        order_ok = ( Button)findViewById(R.id.order_ok);
        order_mod = ( Button)findViewById(R.id.order_mod);
        order_delete= ( Button)findViewById(R.id.order_delete);
        order_back = ( Button)findViewById(R.id.order_back);


        order_id.setText(id);
        order_foodname.setText(foodname);
        order_price.setText(price);
        order_numb.setText(numb);
        order_sum.setText(sum);
        order_address.setText(address);


        order_ok .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //传递数据
                Bundle bundle=new Bundle();
                bundle.putString("username",username);
                bundle.putString("order_id",order_id.getText().toString());
                bundle.putString("order_foodname",order_foodname.getText().toString());
                bundle.putString("order_sum",order_sum.getText().toString());
                bundle.putString("order_address",order_address.getText().toString());
                Intent intent=new Intent(OrderDetailActivity.this,CommentActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();



                int orderid=(int)Double.parseDouble(order_id.getText().toString());
                //上传数据
                OrderAdapter adpter=new OrderAdapter(OrderDetailActivity.this);
                adpter.openODB();
                adpter.deleteByID(orderid);




            }
        });


        order_mod .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //传递数据
                Bundle bundle=new Bundle();
                bundle.putString("username",username);
                bundle.putString("order_id",order_id.getText().toString());
                bundle.putString("order_foodname",order_foodname.getText().toString());
                bundle.putString("order_price",order_price.getText().toString());
                bundle.putString("order_numb",order_numb.getText().toString());
                bundle.putString("order_sum",order_sum.getText().toString());
                bundle.putString("order_address",order_address.getText().toString());
                Intent intent=new Intent(OrderDetailActivity.this,OrderDetailModActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });


        order_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int orderid=(int)Double.parseDouble(order_id.getText().toString());
                //上传数据
                OrderAdapter adpter=new OrderAdapter(OrderDetailActivity.this);
                adpter.openODB();
                adpter.deleteByID(orderid);

                Toast.makeText(OrderDetailActivity.this,"取消订单成功！",Toast.LENGTH_LONG).show();


                //传递数据
                Bundle bundle=new Bundle();
                bundle.putString("username",username);
                Intent intent=new Intent(OrderDetailActivity.this,MainHomeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();


            }
        });


        order_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //传递数据
                Bundle bundle=new Bundle();
                bundle.putString("username",username);
                Intent intent=new Intent(OrderDetailActivity.this,MainHomeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });


    }
}
