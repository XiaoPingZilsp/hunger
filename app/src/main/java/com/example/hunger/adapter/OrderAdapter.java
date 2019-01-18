package com.example.hunger.adapter;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hunger.bean.Order;
import com.example.hunger.bean.PeopleInfo;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderAdapter {

    private static final String DB_NAME="order.db";
    private static final String DB_TABLE="OrderInfo";
    private static final int DB_VERSION=1;
    private static final String KEY_ID="_id";
    private static final String KEY_NAME="username";
    private static final String KEY_ADDRESS="address";
    private static final String KEY_FOODNAME="foodname";
    private static final String KEY_FOODPRICE="foodprice";
    private static final String KEY_SUM="sum";
    private static final String KEY_NUMBER="number";


    private SQLiteDatabase db;
    private final Context context;
    private DBOpenHelper dbOpenHelper;

    private static class DBOpenHelper extends SQLiteOpenHelper
    {
        private static final String DB_CREATE="create table "+DB_TABLE+"("+KEY_ID+"" +
                " integer primary key autoincrement, "+KEY_NAME+" " +
                "text,"+KEY_ADDRESS+" " +
                "text,"+KEY_FOODNAME+" " +
                "text,"+KEY_FOODPRICE+" " +
                "text,"+KEY_SUM+" " +
                "text,"+KEY_NUMBER+" text);";

        public DBOpenHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase arg0) {
            // TODO Auto-generated method stub
            arg0.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
            // TODO Auto-generated method stub
            arg0.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
            onCreate(arg0);
        }
    }



    public OrderAdapter(Context _context)
    {
        context=_context;
    }


    public void openODB() throws SQLiteException
    {
        dbOpenHelper=new DBOpenHelper(context, DB_NAME, null, DB_VERSION);
        try{
            db=dbOpenHelper.getWritableDatabase();
        }
        catch(SQLiteException ex)
        {
            db=dbOpenHelper.getReadableDatabase();
        }
    }


    public void close()
    {
        if(db!=null)
        {
            db.close();
            db=null;
        }
    }


    public long Insert(Order order)
    {
        ContentValues newValue=new ContentValues();
        newValue.put(KEY_NAME,order.UserName);
        newValue.put(KEY_ADDRESS,order.Address);
        newValue.put(KEY_FOODNAME,order.FoodName);
        newValue.put(KEY_FOODPRICE,order.FoodPrice);
        newValue.put(KEY_SUM,order.Sum);
        newValue.put(KEY_NUMBER,order.Number);

        return db.insert(DB_TABLE, null, newValue);
    }

    public long updateById(Order order,int id)
    {
        ContentValues updateValue=new ContentValues();

        updateValue.put(KEY_ADDRESS,order.Address);
        updateValue.put(KEY_NUMBER,order.Number);
        updateValue.put(KEY_SUM,order.Sum);

        return db.update(DB_TABLE, updateValue, KEY_ID + "=" + "'" + id + "'", null);
    }


    public long deleteAll()
    {
        return db.delete(DB_TABLE, null, null);
    }


    public long deleteByID(int id)
    {
        return db.delete(DB_TABLE, KEY_ID + "=" + "'" +id + "'", null);
    }



    public Order[] qurryAll(String name)	{
        Cursor result=db.query(DB_TABLE, new String[]{},KEY_NAME + "=" + "'" + name + "'", null, null, null, null);
        return ConvertToPeopleInfo(result);
    }

    private Order[] ConvertToPeopleInfo(Cursor cursor){
        int resultCounts=cursor.getCount();
        if(resultCounts==0||!cursor.moveToFirst()){
            return null;
        }
        Order[] orders=new Order[resultCounts];
        for(int i=0;i<resultCounts;i++)
        {

            orders[i] = new Order(cursor.getString(cursor.getColumnIndex(KEY_NAME)), cursor.getString(cursor.getColumnIndex(KEY_ADDRESS)),cursor.getString(cursor.getColumnIndex(KEY_FOODNAME)),cursor.getString(cursor.getColumnIndex(KEY_FOODPRICE)),cursor.getString(cursor.getColumnIndex(KEY_SUM)),cursor.getString(cursor.getColumnIndex(KEY_NUMBER)));
            orders[i].ID = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            cursor.moveToNext();
        }

        return orders;

    }



    public ArrayList Query() {

        //------将更新后的全部数据返回---------
        //定义一个键值对数组将数据返回到第一个列表Activity中更新进行显示
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        Cursor cursor = db.query(DB_TABLE, new String[]{}, null, null, null, null, null);
        while(cursor.moveToNext()){
            HashMap<String, Object> map = new HashMap<String, Object>();

            map.put("name", cursor.getString(cursor.getColumnIndex("name")));
            map.put("address", cursor.getString(cursor.getColumnIndex("address")));
            map.put("phone_number", cursor.getString(cursor.getColumnIndex("phone_number")));
            map.put("userpass", cursor.getString(cursor.getColumnIndex("userpass")));

            list.add(map);
        }
        return list;   //返回全部数据list用于列表显示
    }


}
