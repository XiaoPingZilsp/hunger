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
import com.example.hunger.bean.PeopleInfo;
import com.example.hunger.fragment.PersonInfoFragment;

public class OrderActivity extends AppCompatActivity {
    TextView food_name_dingdan,food_price_dingdan,tv,result,username_dingdan;
    EditText address_dingdan,num_dingdan;
    Button btn_ok_dingdan,btn_cancel_dingdan,order_num,order_jia;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        Bundle bundle=this.getIntent().getExtras();
        String price=bundle.getString("price");
        String news_info=bundle.getString("news_info");
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



        food_name_dingdan.setText(news_info);
        food_price_dingdan.setText(price);


        result.setText(Double.toString(Double.parseDouble(num_dingdan.getText().toString())* Double.parseDouble(food_price_dingdan.getText().toString())));
        username_dingdan.setText(username);

       // int a=(int)Double.parseDouble(num_dingdan.getText().toString());


        btn_ok_dingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //从控件获取数据
                String number=num_dingdan.getText().toString().trim();
                String username=username_dingdan.getText().toString().trim();
                String address=address_dingdan.getText().toString().trim();
                String food_name=food_name_dingdan.getText().toString().trim();
                String food_price=food_price_dingdan.getText().toString().trim();
                String sum=result.getText().toString().trim();

                //上传数据
                OrderAdapter adpter=new OrderAdapter(OrderActivity.this);
                adpter.openODB();
                Order order=new Order(username,address,food_name,food_price,sum,number);
                adpter.Insert(order);

                Toast.makeText(OrderActivity.this,"购买成功！",Toast.LENGTH_LONG).show();


                //传递数据
                Bundle bundle=new Bundle();
                bundle.putString("username",username);
                Intent intent=new Intent(OrderActivity.this,MainHomeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });

        btn_cancel_dingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(OrderActivity.this,"取消成功！",Toast.LENGTH_LONG).show();

                //传递数据
                Bundle bundle=new Bundle();
                bundle.putString("username",username);
                Intent intent=new Intent(OrderActivity.this,MainHomeActivity.class);
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
