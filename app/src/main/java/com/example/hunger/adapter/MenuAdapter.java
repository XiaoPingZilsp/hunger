package com.example.hunger.adapter;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import com.example.hunger.bean.MenuInfo;
import com.example.hunger.bean.PeopleInfo;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuAdapter {
    private static final String DB_NAME="menu.db";
    private static final String DB_TABLE="menuInfo";
    private static final int DB_VERSION=1;
    private static final String KEY_ID="_id";
    private static final String KEY_SHOPNAME="shopname";
    private static final String KEY_FOODNAME="foodname";
    private static final String KEY_PRICE="price";
    private static final String KEY_PICTURE="picture";


    private SQLiteDatabase db;
    private final Context context;
    private DBOpenHelper dbOpenHelper;

    private static class DBOpenHelper extends SQLiteOpenHelper
    {
        private static final String DB_CREATE="create table "+DB_TABLE+"("+KEY_ID+"" +
                " integer primary key autoincrement, "+KEY_SHOPNAME+" " +
                "text,"+KEY_FOODNAME+" " +
                "text,"+KEY_PRICE+" " +
                "text,"+KEY_PICTURE+" text);";

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
    public MenuAdapter(Context _context)
    {
        context=_context;
    }
    public void openDB() throws SQLiteException
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

    public long Insert(MenuInfo menu)
    {
        ContentValues newValue=new ContentValues();
        newValue.put(KEY_SHOPNAME,menu.ShopName);
        newValue.put(KEY_FOODNAME,menu.FoodName);
        newValue.put(KEY_PRICE,menu.Price);
        newValue.put(KEY_PICTURE,menu.Picture);

        return db.insert(DB_TABLE, null, newValue);
    }

    public long deleteAll()
    {
        return db.delete(DB_TABLE, null, null);
    }
    public long deleteByName(String name)
    {
        return db.delete(DB_TABLE,KEY_FOODNAME + "=" + "'" + name + "'", null);
    }

    /*public long updateByName(PeopleInfo people,String name)
    {
        ContentValues updateValue=new ContentValues();

        updateValue.put(KEY_ADDRESS,people.Address);
        updateValue.put(KEY_PHONE_NUMBER,people.Phone_Number);

        return db.update(DB_TABLE, updateValue, KEY_NAME + "=" + "'" + name + "'", null);
    }*/


   /* public long updatePassword(PeopleInfo people,String name)
    {
        ContentValues updateValue=new ContentValues();

        updateValue.put(KEY_USERPASS,people.UserPass);

        return db.update(DB_TABLE, updateValue, KEY_NAME + "=" + "'" + name + "'", null);
    }*/


    public MenuInfo[] queryById(int id)
    {
        Cursor result=db.query(DB_TABLE, new String[]{}, KEY_ID+"="+id, null, null, null, null);
        return ConvertToMenuInfo(result);
    }

   /* public PeopleInfo[] queryByName(String name)
    {
        Cursor result=db.query(DB_TABLE, new String[]{},KEY_NAME + "=" + "'" + name + "'", null, null, null, null);
        return ConvertToPeopleInfo(result);
    }*/


   /* public PeopleInfo[] queryByNamePass(String name,String password)
    {
        Cursor result=db.query(DB_TABLE, new String[]{},KEY_NAME + "=" + "'" + name + "'"+
                "and" +" "+KEY_USERPASS + "=" + "'" + password + "'", null, null, null, null);
        return ConvertToPeopleInfo(result);
    }*/


    public MenuInfo[] queryAll()	{
        Cursor result=db.query(DB_TABLE, new String[]{}, null, null, null, null, null);
        return ConvertToMenuInfo(result);
    }

    private MenuInfo[] ConvertToMenuInfo(Cursor cursor){
        int resultCounts=cursor.getCount();
        if(resultCounts==0||!cursor.moveToFirst()){
            return null;
        }
        MenuInfo[] menus=new MenuInfo[resultCounts];
        for(int i=0;i<resultCounts;i++)
        {

                                                                                                                /*    private static final String KEY_ID="_id";
                                                        private static final String KEY_SHOPNAME="shopname";
                                                        private static final String KEY_FOODNAME="foodname";
                                                        private static final String KEY_PRICE="price";
                                                        private static final String KEY_PICTURE="picture";*/
            menus[i] = new MenuInfo(cursor.getString(cursor.getColumnIndex(KEY_SHOPNAME)), cursor.getString(cursor.getColumnIndex(KEY_FOODNAME)),cursor.getString(cursor.getColumnIndex(KEY_PRICE)),cursor.getString(cursor.getColumnIndex(KEY_PICTURE)));
            menus[i].ID = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            cursor.moveToNext();
        }

        return menus;

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
