package com.example.hunger.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hunger.R;
import com.example.hunger.adapter.CommentAdapter;
import com.example.hunger.adapter.OrderAdapter;
import com.example.hunger.bean.CommentInfo;
import com.example.hunger.bean.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentDetailActivity extends AppCompatActivity {
    private ListView listView3;

    private List<Map<String, Object>> Data_order;
    String username;
    CommentAdapter adapter_comment;
    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_detail);

        Bundle bundle = this.getIntent().getExtras();
        username= bundle.getString("username");

        listView3 = (ListView) findViewById(R.id.listView3);
        btn_back = (Button) findViewById(R.id.btn_back);

        adapter_comment=new CommentAdapter(this);
        adapter_comment.openDB();

        Data_order = getData_order();
        Adapter_order adapter = new Adapter_order(this);
        listView3.setAdapter(adapter);




        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(CommentDetailActivity.this,"返回成功！",Toast.LENGTH_LONG).show();

                //传递数据
                Bundle bundle=new Bundle();
                bundle.putString("username",username);
                Intent intent=new Intent(CommentDetailActivity.this,MainHomeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });

    }





    private List<Map<String, Object>> getData_order () {

        adapter_comment.openDB();
        CommentInfo[] comments = adapter_comment.queryAll();

            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        if(comments!=null) {
            for (int i = 0; i < comments.length; i++) {

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("commentid", comments[i].getID() + "");
               // map.put("orderid", comments[i].getOrderId());
                map.put("foodname", comments[i].getFoodName());
                map.put("username", comments[i].getUserName());
                map.put("comment", comments[i].getComment());
                list.add(map);

            }
        }
        return list;
    }
    public final class ViewHolder_order {

        public TextView order_id, order_foodname, comment_id, comment, comment_username ;
        public Button btn_cal_order,order_delete;
        private LinearLayout comment_item;

    }


    public class Adapter_order extends BaseAdapter {

        private LayoutInflater mInflater;


        public Adapter_order(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return Data_order.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

           ViewHolder_order holder = null;
            if (convertView == null) {

                holder = new ViewHolder_order();

                convertView = mInflater.inflate(R.layout.comment_item, null);

                holder.order_foodname = (TextView) convertView.findViewById(R.id.order_foodname);
                holder.comment = (TextView) convertView.findViewById(R.id.comment);
                holder.comment_username = (TextView) convertView.findViewById(R.id.comment_username);
                holder.order_delete=(Button)convertView.findViewById(R.id.order_delete);

                holder.comment_item = (LinearLayout) convertView.findViewById(R.id.comment_item);
                convertView.setTag(holder);

            } else {

                holder = (ViewHolder_order) convertView.getTag();
            }


            holder.order_foodname.setText((String) Data_order.get(position).get("foodname"));
            holder.comment.setText((String) Data_order.get(position).get("comment"));
            holder.comment_username.setText((String) Data_order.get(position).get("username"));


            holder.order_delete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    int commentid=(int)Double.parseDouble((String) Data_order.get(position).get("commentid"));

                //删除数据
                    adapter_comment.deleteByOrderId(commentid ,username);


                    //传递数据
                   Bundle bundle=new Bundle();
                    bundle.putString("username",username);
                    Intent intent=new Intent(CommentDetailActivity.this,CommentDetailActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();


                }
            });

            return convertView;
        }

    }

}