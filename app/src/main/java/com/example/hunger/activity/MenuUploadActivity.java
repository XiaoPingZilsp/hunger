package com.example.hunger.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hunger.R;
import com.example.hunger.adapter.CommentAdapter;
import com.example.hunger.adapter.MenuAdapter;
import com.example.hunger.bean.CommentInfo;
import com.example.hunger.bean.MenuInfo;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

public class MenuUploadActivity extends AppCompatActivity {

    String username;
    Button btn_ok,btn_cancel,btn_pic;
    EditText news_title,news_info,price;
    ImageView news_thumb;
    Bitmap mBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_upload);

        Bundle bundle = this.getIntent().getExtras();
        username= bundle.getString("username");

        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_pic = (Button) findViewById(R.id.btn_pic);
        news_title = (EditText) findViewById(R.id.news_title);
        news_info = (EditText) findViewById(R.id.news_info);
        price = (EditText) findViewById(R.id.price);

        news_thumb = (ImageView) findViewById(R.id.news_thumb);








        btn_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MenuUploadActivity.this,"图片选择成功！",Toast.LENGTH_LONG).show();

                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,1);

            }

        });


        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //从控件获取数据
                String title=news_title.getText().toString().trim();
                String info=news_info.getText().toString().trim();
                String price1=price.getText().toString().trim();

            //将图片从Bitmap转为String类型
                ByteArrayOutputStream baos = new ByteArrayOutputStream();// outputstream
                mBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] appicon = baos.toByteArray();// 转为byte数组
                String pic=Base64.encodeToString(appicon, Base64.DEFAULT);

                //上传数据
                MenuAdapter adpter=new  MenuAdapter(MenuUploadActivity.this);
                adpter.openDB();
                MenuInfo menu=new MenuInfo(title,info,price1,pic);
                adpter.Insert(menu);

                Toast.makeText(MenuUploadActivity.this,"菜品上传成功！",Toast.LENGTH_LONG).show();


                //传递数据
                Bundle bundle=new Bundle();
                bundle.putString("username",username);
                Intent intent=new Intent(MenuUploadActivity.this,MainHomeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });



        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MenuUploadActivity.this,"取消成功！",Toast.LENGTH_LONG).show();

                //传递数据
                Bundle bundle=new Bundle();
                bundle.putString("username",username);
                Intent intent=new Intent(MenuUploadActivity.this,MainHomeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });










    }


    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        /*if(requestCode==RESULT_OK){*/
            Uri uri=data.getData();
            ContentResolver cr=MenuUploadActivity.this.getContentResolver();
            try {
                mBitmap= BitmapFactory.decodeStream(cr.openInputStream(uri));
                news_thumb.setImageBitmap(mBitmap);
                Toast.makeText(MenuUploadActivity.this,"图片显示成功！",Toast.LENGTH_LONG).show();
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
       // }
       super.onActivityResult(requestCode,resultCode,data);

    }



}
