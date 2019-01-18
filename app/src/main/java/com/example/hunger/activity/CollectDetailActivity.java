package com.example.hunger.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hunger.R;
import com.example.hunger.adapter.CollectAdapter;
import com.example.hunger.adapter.CommentAdapter;
import com.example.hunger.bean.CollectInfo;
import com.example.hunger.bean.CommentInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectDetailActivity extends AppCompatActivity {
    ListView news_list;
    String username;
    Button btn_back;
    CollectAdapter adapter_collect;
    private List<Map<String, Object>> Data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collect);

        Bundle bundle = this.getIntent().getExtras();
        username= bundle.getString("username");

         news_list = (ListView)findViewById(R.id.listView4);
        btn_back = (Button) findViewById(R.id.btn_back);

        adapter_collect=new CollectAdapter(this);
        adapter_collect.openDB();


        Data = getData();
        MyAdapter adapter = new  MyAdapter(this);
        news_list.setAdapter(adapter);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(CollectDetailActivity.this,"返回成功！",Toast.LENGTH_LONG).show();

                //传递数据
                Bundle bundle=new Bundle();
                bundle.putString("username",username);
                Intent intent=new Intent(CollectDetailActivity.this,MainHomeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });

    }

    private List<Map<String, Object>> getData () {

        adapter_collect.openDB();
        CollectInfo[] collects = adapter_collect.queryAll(username);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        if(collects!=null) {
            for (int i = 0; i < collects.length; i++) {
                //  把图片从String 转为Bitmap
                Bitmap bitmap = null;
                try
                {
                    // out = new FileOutputStream("/sdcard/aa.jpg");
                    byte[] bitmapArray;
                    bitmapArray = Base64.decode(collects[i].getIcon(), Base64.DEFAULT);
                    bitmap =
                            BitmapFactory.decodeByteArray(bitmapArray, 0,
                                    bitmapArray.length);
                    // bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                    // return bitmap;
                }
                catch (Exception e)
                {
                    return null;
                }

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("collectid", collects[i].getID() + "");
                map.put("news_title", collects[i].getShopName());
                map.put("news_info", collects[i].getFoodName());
                map.put("username", collects[i].getUserName());
                map.put("price", collects[i].getPrice());
                map.put("news_thumb", bitmap);

                list.add(map);

            }
        }
        return list;
    }

    public final class ViewHolder{
        public ImageView news_thumb;
        public TextView news_title;
        public TextView news_info,price;
        public Button news_btn_detail;
        public Button news_btn_order;
        public Button btn_cancel;

    }

    public class MyAdapter extends BaseAdapter {

        private LayoutInflater mInflater;


        public MyAdapter(Context context){
            this.mInflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return Data.size();
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

            ViewHolder holder = null;
            if (convertView == null) {

                holder=new ViewHolder();

                convertView = mInflater.inflate(R.layout.collect_item, null);
                holder.news_thumb = (ImageView)convertView.findViewById(R.id.news_thumb);
                holder.news_title = (TextView)convertView.findViewById(R.id.news_title);
                holder.news_info = (TextView)convertView.findViewById(R.id.news_info);
                holder.price = (TextView)convertView.findViewById(R.id.price);
                holder.news_btn_detail = (Button)convertView.findViewById(R.id.news_btn_detail);
                holder.news_btn_order= (Button)convertView.findViewById(R.id.news_btn_order);
               holder.btn_cancel= (Button)convertView.findViewById(R.id.btn_cancel);

                convertView.setTag(holder);

            }else {

                holder = (ViewHolder)convertView.getTag();
            }


           // holder.news_thumb.setBackgroundResource((Integer)Data.get(position).get("news_thumb"));
            holder.news_thumb.setImageBitmap((Bitmap) Data.get(position).get("news_thumb"));
            holder.news_title.setText((String) Data.get(position).get("news_title"));
            holder.news_info.setText((String)Data.get(position).get("news_info"));
            holder.price.setText((String)Data.get(position).get("price"));
            holder.news_btn_detail.setTag(position);
            holder.news_btn_order.setTag(position);
            holder.btn_cancel.setTag(position);

            holder.news_btn_detail.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    showInfo(position);
                }
            });



            holder.btn_cancel.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    int id=(int)Double.parseDouble((String) Data.get(position).get("collectid"));
                    //删除数据
                    CollectAdapter adpter=new CollectAdapter(CollectDetailActivity.this);
                    adpter.openDB();
                    adpter.deleteById(id);

                    Toast.makeText(CollectDetailActivity.this,"移除成功！",Toast.LENGTH_LONG).show();

                    //传递数据
                    Bundle bundle=new Bundle();
                    bundle.putString("username",username);
                    Intent intent=new Intent(CollectDetailActivity.this,CollectDetailActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();

                }
            });




            holder.news_btn_order.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {




                    Bundle bundle=new Bundle();
                    bundle.putString("username",username);
                    bundle.putString("price", (String) Data.get(position).get("price"));
                    bundle.putString("news_info", (String) Data.get(position).get("news_info"));
                    Intent intent=new Intent(CollectDetailActivity.this,OrderActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            });
            return convertView;
        }

    }
    public void showInfo(int position){

        new AlertDialog.Builder(this)
                .setTitle(Data.get(position).get("news_info").toString())
               // .setMessage(Data.get(position).get("news_info").toString())
                .setMessage(Data.get(position).get("price").toString())
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();

    }



}
