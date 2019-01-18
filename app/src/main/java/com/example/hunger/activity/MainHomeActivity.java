package com.example.hunger.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hunger.adapter.CollectAdapter;
import com.example.hunger.adapter.MenuAdapter;
import com.example.hunger.adapter.OrderAdapter;
import com.example.hunger.bean.CollectInfo;
import com.example.hunger.bean.MenuInfo;
import com.example.hunger.bean.Order;
import com.example.hunger.fragment.OtherFragment;
import com.example.hunger.fragment.ShopListFragment;
import com.example.hunger.R;
import com.example.hunger.fragment.OrderFragment;
import com.example.hunger.adapter.DBAdapter;
import com.example.hunger.bean.PeopleInfo;
import com.example.hunger.fragment.PersonInfoFragment;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainHomeActivity extends AppCompatActivity implements PersonInfoFragment.PersonInfoFragmentListener,ShopListFragment.imageListener,OrderFragment.OrderFragmentListener,OtherFragment.OtherFragmentListener {      //AppCompatActivity

    String username;
    OrderAdapter adpter_order;
    MenuAdapter adpter_menu;

    //声明ViewPager
    private ViewPager mViewPager;
    //适配器
    private FragmentPagerAdapter mAdapter;
    //装载Fragment的集合
    private List<Fragment> mFragments;



    //四个Tab对应的布局
    private LinearLayout mTabWechat;
    private LinearLayout mTabFriend;
    private LinearLayout mTabContact;
    private LinearLayout mTabSetting;

    //四个Tab对应的ImageButton
    private ImageButton mImgWechat;
    private ImageButton mImgFriend;
    private ImageButton mImgContact;
    private ImageButton mImgSetting;


    private ListView news_list;
    private List<Map<String, Object>> mData;


    private List<Map<String, Object>> Data_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        initViews();//初始化控件
        initEvents();//初始化事件
        initDatas();//初始化数据

        Bundle bundle=this.getIntent().getExtras();
        username=bundle.getString("username");

        adpter_order=new OrderAdapter(this);
        adpter_order.openODB();

        adpter_menu=new MenuAdapter(this);
        adpter_menu.openDB();

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void initDatas() {
        mFragments = new ArrayList<>();
        //将四个Fragment加入集合中

        mFragments.add(new ShopListFragment());
        mFragments.add(new OrderFragment());
        mFragments.add(new OtherFragment());
        mFragments.add(new PersonInfoFragment());


        //初始化适配器
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {//从集合中获取对应位置的Fragment
                return mFragments.get(position);
            }

            @Override
            public int getCount() {//获取集合中Fragment的总数
                return mFragments.size();
            }

        };
        //不要忘记设置ViewPager的适配器
        mViewPager.setAdapter(mAdapter);
        //设置ViewPager的切换监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            //页面滚动事件
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //页面选中事件
            @Override
            public void onPageSelected(int position) {
                //设置position对应的集合中的Fragment
                mViewPager.setCurrentItem(position);
                resetImgs();
                selectTab(position);
            }

            @Override
            //页面滚动状态改变事件
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initEvents() {
        //设置四个Tab的点击事件
        mTabWechat.setOnClickListener(onClickListener);
        mTabFriend.setOnClickListener(onClickListener);
        mTabContact.setOnClickListener(onClickListener);
        mTabSetting.setOnClickListener(onClickListener);


    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //先将四个ImageButton置为灰色
            resetImgs();

            //根据点击的Tab切换不同的页面及设置对应的ImageButton为绿色
            switch (v.getId()) {
                case R.id.id_tab_wechat:
                    selectTab(0);
                    break;
                case R.id.id_tab_friend:
                    selectTab(1);
                    break;
                case R.id.id_tab_contact:
                    selectTab(2);
                    break;
                case R.id.id_tab_setting:
                    selectTab(3);
                    break;

            }
        }
    };

    //初始化控件
    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        mTabWechat = (LinearLayout) findViewById(R.id.id_tab_wechat);
        mTabFriend = (LinearLayout) findViewById(R.id.id_tab_friend);
        mTabContact = (LinearLayout) findViewById(R.id.id_tab_contact);
        mTabSetting = (LinearLayout) findViewById(R.id.id_tab_setting);


        mImgWechat = (ImageButton) findViewById(R.id.id_tab_wechat_img);
        mImgFriend = (ImageButton) findViewById(R.id.id_tab_friend_img);
        mImgContact = (ImageButton) findViewById(R.id.id_tab_contact_img);
        mImgSetting = (ImageButton) findViewById(R.id.id_tab_setting_img);


    }

    private void selectTab(int i) {
        //根据点击的Tab设置对应的ImageButton为绿色
        switch (i) {
            case 0:
                mImgWechat.setImageResource(R.mipmap.shouye1);
                break;
            case 1:
                mImgFriend.setImageResource(R.mipmap.tab_find_frd_pressed);
                break;
            case 2:
                mImgContact.setImageResource(R.mipmap.tab_address_pressed);
                break;
            case 3:
                mImgSetting.setImageResource(R.mipmap.tab_settings_pressed);
                break;

        }
        //设置当前点击的Tab所对应的页面
        mViewPager.setCurrentItem(i);
    }

    //将四个ImageButton设置为灰色
    private void resetImgs() {
        mImgWechat.setImageResource(R.mipmap.shouye);
        mImgFriend.setImageResource(R.mipmap.tab_find_frd_normal);
        mImgContact.setImageResource(R.mipmap.tab_address_normal);
        mImgSetting.setImageResource(R.mipmap.tab_settings_normal);
    }

  public PeopleInfo[] showInfo(){
      //  获取username的值

        Bundle bundle=this.getIntent().getExtras();
        String username=bundle.getString("username");
        DBAdapter adpter=new DBAdapter(this);
        adpter.openDB();

      return adpter.queryByName(username);

    }

    public void onFinishMod() {

        //获取login中传递的数据
        Bundle bundle=this.getIntent().getExtras();
        String username=bundle.getString("username");

        //传递数据
        Bundle bundle_mod=new Bundle();
        bundle_mod.putString("username",username);
        Intent intent=new Intent(this,ModActivity.class);
        intent.putExtras(bundle_mod);
        startActivity(intent);
        finish();

    }


    public void onFinishModPass() {

        //获取login中传递的数据
        Bundle bundle=this.getIntent().getExtras();
        String username=bundle.getString("username");

        //传递数据
        Bundle bundle_mod=new Bundle();
        bundle_mod.putString("username",username);
        Intent intent=new Intent(this,ModPassActivity.class);
        intent.putExtras(bundle_mod);
        startActivity(intent);
        finish();

    }

    public void onListItemClick(ListView l, View v, int position, long id) {

    }

//ShopListFragment中ListView的适配器
    public MyAdapter SetAdapter(){

        mData = getData();
        MyAdapter adapter = new MyAdapter(this);
        return   adapter;
    }

    //OrderFragment中ListView的适配器
    public Adapter_order SetAdapter_order(){
        Data_order = getData_order();
        Adapter_order adapter1 = new Adapter_order(this);

        return   adapter1;

    }


    //OrderFragment的调用显示方法
    public  Order[] showOrderInfo(){
        //获取login中传递的数据
        Bundle bundle=this.getIntent().getExtras();
        String username=bundle.getString("username");
        return adpter_order.qurryAll(username);
    }

    //OtherFragment的调用菜品上传方法
    public  void onUpload(){

        //传递数据
        Bundle bundle=new Bundle();
        bundle.putString("username",username);
        Intent intent=new Intent(this,MenuUploadActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();

    }



    //OtherFragment的调用收藏列表显示方法
    public  void onCollect(){

        //传递数据
        Bundle bundle=new Bundle();
        bundle.putString("username",username);
        Intent intent=new Intent(this,CollectDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();

    }



    //OtherFragment的调用评价列表显示方法
    public  void onComment(){
        //传递数据
        Bundle bundle=new Bundle();
        bundle.putString("username",username);
        Intent intent=new Intent(this,CommentDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();

    }



    //菜品显示列表
    private List<Map<String, Object>> getData() {

        MenuInfo[] menus=adpter_menu.queryAll();

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();


        for (int i = 0; i < menus.length; i++) {
        //  把图片从String 转为Bitmap
            Bitmap bitmap = null;
            try
            {
                // out = new FileOutputStream("/sdcard/aa.jpg");
                byte[] bitmapArray;
                bitmapArray = Base64.decode(menus[i].getPicture(), Base64.DEFAULT);
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
            map.put("menu_id",menus[i].getID()+"");

            map.put("news_title",menus[i].getShopName());
            map.put("news_info",menus[i].getFoodName());
            map.put("price",menus[i].getPrice());
            map.put("news_thumb",bitmap);

            list.add(map);

        }

        return list;





    }

    public final class ViewHolder{
        public ImageView news_thumb;
        public TextView news_title;
        public TextView news_info,price;
        public Button news_btn_detail;
        public Button news_btn_order;
        public Button btn_collect;

    }
    //订餐的适配器
    public class MyAdapter extends BaseAdapter {

        private LayoutInflater mInflater;


        public MyAdapter(Context context){
            this.mInflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mData.size();
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

                convertView = mInflater.inflate(R.layout.shop_item, null);
                holder.news_thumb = (ImageView)convertView.findViewById(R.id.news_thumb);
                holder.news_title = (TextView)convertView.findViewById(R.id.news_title);
                holder.news_info = (TextView)convertView.findViewById(R.id.news_info);
                holder.price = (TextView)convertView.findViewById(R.id.price);
                holder.news_btn_detail = (Button)convertView.findViewById(R.id.news_btn_detail);
                holder.news_btn_order= (Button)convertView.findViewById(R.id.news_btn_order);
                holder.btn_collect= (Button)convertView.findViewById(R.id.btn_collect);

                convertView.setTag(holder);

            }else {

                holder = (ViewHolder)convertView.getTag();
            }


           // holder.news_thumb.setBackgroundResource((Integer)mData.get(position).get("news_thumb"));
            holder.news_thumb.setImageBitmap((Bitmap) mData.get(position).get("news_thumb"));
            holder.news_title.setText((String) mData.get(position).get("news_title"));
            holder.news_info.setText((String)mData.get(position).get("news_info"));
            holder.price.setText((String)mData.get(position).get("price"));
            holder.news_btn_detail.setTag(position);
            holder.news_btn_order.setTag(position);
            holder.btn_collect.setTag(position);

            holder.news_btn_detail.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    showInfo(position);
                }
            });



            holder.btn_collect.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    //  把图片从Bitmap 转为String

                   ByteArrayOutputStream baos = new ByteArrayOutputStream();// outputstream
                    Bitmap mBitmap=(Bitmap) mData.get(position).get("news_thumb");
                    mBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    byte[] appicon = baos.toByteArray();// 转为byte数组
                    String icon=Base64.encodeToString(appicon, Base64.DEFAULT);

                    String shopname=(String) mData.get(position).get("news_title");
                    String foodname=(String)mData.get(position).get("news_info");
                    String price=(String)mData.get(position).get("price");
                   // String  icon=(String)mData.get(position).get("news_thumb");

                    //上传数据
                    CollectAdapter adpter=new CollectAdapter(MainHomeActivity.this);
                    adpter.openDB();
                    CollectInfo collect=new CollectInfo(shopname,foodname,price,username);
                    adpter.Insert(collect,icon);

                    Toast.makeText(MainHomeActivity.this,"收藏成功！",Toast.LENGTH_LONG).show();

                }
            });




            holder.news_btn_order.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {




                    Bundle bundle=new Bundle();
                    bundle.putString("username",username);
                    bundle.putString("price", (String) mData.get(position).get("price"));
                    bundle.putString("news_info", (String) mData.get(position).get("news_info"));
                    Intent intent=new Intent(MainHomeActivity.this,OrderActivity.class);
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
                .setTitle(mData.get(position).get("news_info").toString())
               // .setMessage(mData.get(position).get("news_info").toString())
                .setMessage(mData.get(position).get("price").toString())
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();

    }





    //订单列表
    private List<Map<String, Object>> getData_order(){

        Bundle bundle=this.getIntent().getExtras();
        String username=bundle.getString("username");
        Order[] orders=adpter_order.qurryAll(username);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();


        for (int i = 0; i < orders.length; i++) {

        Map<String, Object> map = new HashMap<String, Object>();
            map.put("order_id",orders[i].getID()+"");

        map.put("order_foodname",orders[i].getFoodName());
        map.put("order_price",orders[i].getFoodPrice());
        map.put("order_numb",orders[i].getNumber());
        map.put("order_sum",orders[i].getSum());
        map.put("order_address",orders[i].getAddress());
        list.add(map);

        }

        return list;
    }

    public final class ViewHolder_order{

        public TextView order_id,order_foodname,order_price,order_numb,order_sum,order_address;
        public Button btn_cal_order;
        private LinearLayout item;

    }


    public class Adapter_order extends BaseAdapter {

        private LayoutInflater mInflater;


        public Adapter_order(Context context){
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

                holder=new ViewHolder_order();

                convertView = mInflater.inflate(R.layout.order_item, null);
                holder.order_id = (TextView)convertView.findViewById(R.id.order_id);
                holder.order_foodname = (TextView)convertView.findViewById(R.id.order_foodname);
                holder.order_price = (TextView)convertView.findViewById(R.id.order_price);
                holder.order_numb = (TextView)convertView.findViewById(R.id.order_numb);
                holder.order_sum = (TextView)convertView.findViewById(R.id.order_sum);
                holder.order_address = (TextView)convertView.findViewById(R.id.order_address);

                holder.item=(LinearLayout)convertView.findViewById(R.id.item);
                convertView.setTag(holder);

            }else {

                holder = (ViewHolder_order)convertView.getTag();
            }

            holder.order_id.setText((String) Data_order.get(position).get("order_id"));
            holder.order_foodname.setText((String)Data_order.get(position).get("order_foodname"));
            holder.order_price.setText((String)Data_order.get(position).get("order_price"));
            holder.order_numb.setText((String)Data_order.get(position).get("order_numb"));
            holder.order_sum .setText((String)Data_order.get(position).get("order_sum"));
            holder.order_address.setText((String)Data_order.get(position).get("order_address"));


            holder.item.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                   // showInfo1(position);
                    //传递数据
                    Bundle bundle=new Bundle();
                    bundle.putString("order_id",(String) Data_order.get(position).get("order_id"));
                    bundle.putString("order_foodname",(String) Data_order.get(position).get("order_foodname"));
                    bundle.putString("order_price",(String) Data_order.get(position).get("order_price"));
                    bundle.putString("order_numb",(String) Data_order.get(position).get("order_numb"));
                    bundle.putString("order_sum",(String) Data_order.get(position).get("order_sum"));
                    bundle.putString("order_address",(String) Data_order.get(position).get("order_address"));
                    bundle.putString("username",username);

                    Intent intent=new Intent(MainHomeActivity.this,OrderDetailActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();

                }
            });

            return convertView;
        }

    }







}
