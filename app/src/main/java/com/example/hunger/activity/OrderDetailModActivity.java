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

public class OrderDetailModActivity extends AppCompatActivity {
    TextView food_name_dingdan,food_price_dingdan,tv,result,username_dingdan;
    EditText address_dingdan,num_dingdan;
    Button btn_ok_dingdan,btn_cancel_dingdan,order_num;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);

        Bundle bundle=this.getIntent().getExtras();
        id=bundle.getString("order_id");
        String foodname=bundle.getString("order_foodname");
        String price=bundle.getString("order_price");
        String numb=bundle.getString("order_numb");
        String sum=bundle.getString("order_sum");
        String address=bundle.getString("order_address");
        final String username=bundle.getString("username");


        num_dingdan = (EditText)findViewById(R.id.num_dingdan);
        username_dingdan = (TextView)findViewById(R.id.username_dingdan);
        address_dingdan = (EditText)findViewById(R.id.address_dingdan );
        food_name_dingdan = (TextView )findViewById(R.id.food_name_dingdan);
        food_price_dingdan = (TextView)findViewById(R.id.food_price_dingdan);
        tv = (TextView)findViewById(R.id.tv);
        result = (TextView)findViewById(R.id.result);
        btn_ok_dingdan = (Button )findViewById(R.id.btn_ok_dingdan);
        btn_cancel_dingdan = (Button )findViewById(R.id.btn_cancel_dingdan);
        order_num=(Button )findViewById(R.id.order_num);


        num_dingdan.setText(numb);
        username_dingdan.setText(username);
        address_dingdan.setText(address);
        food_name_dingdan.setText(foodname);
        food_price_dingdan.setText(price);
        result.setText(sum);


        btn_ok_dingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //从控件获取数据
                String number=num_dingdan.getText().toString().trim();
                String address=address_dingdan.getText().toString().trim();
                String sum=result.getText().toString().trim();
                int orderid=(int)Double.parseDouble(id);

                //上传数据
                OrderAdapter adpter=new OrderAdapter(OrderDetailModActivity.this);
                adpter.openODB();
                Order order=new Order(address,number,sum);
                adpter.updateById(order,orderid);

                Toast.makeText(OrderDetailModActivity.this,"修改成功！",Toast.LENGTH_LONG).show();


                //传递数据
                Bundle bundle=new Bundle();
                bundle.putString("username",username);
                Intent intent=new Intent(OrderDetailModActivity.this,MainHomeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });


        btn_cancel_dingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(OrderDetailModActivity.this,"取消成功！",Toast.LENGTH_LONG).show();

                //传递数据
                Bundle bundle=new Bundle();
                bundle.putString("username",username);
                Intent intent=new Intent(OrderDetailModActivity.this,MainHomeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });

        //数量减
        order_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(Double.toString(Double.parseDouble(num_dingdan.getText().toString())* Double.parseDouble(food_price_dingdan.getText().toString())));

            }
        });


    }
}
