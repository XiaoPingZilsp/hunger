package com.example.hunger.adapter;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import com.example.hunger.bean.CollectInfo;
import com.example.hunger.bean.PeopleInfo;

import java.util.ArrayList;
import java.util.HashMap;

public class CollectAdapter {
    private static final String DB_NAME="collect.db";
    private static final String DB_TABLE="collectInfo";
    private static final int DB_VERSION=2;
    private static final String KEY_ID="_id";
    private static final String KEY_SHOPNAME="shopname";
    private static final String KEY_FOODNAME="foodname";
    private static final String KEY_PRICE="price";
    private static final String KEY_USERNAME="username";
    private static final String KEY_ICON="icon";



    private SQLiteDatabase db;
    private final Context context;
    private DBOpenHelper dbOpenHelper;

    private static class DBOpenHelper extends SQLiteOpenHelper
    {
        private static final String DB_CREATE="create table "+DB_TABLE+"("+KEY_ID+"" +
                " integer primary key autoincrement, "+KEY_SHOPNAME+" " +
                "text,"+KEY_FOODNAME+" " +
                "text,"+KEY_USERNAME+" " +
                "text,"+KEY_ICON+" " +
                "text,"+KEY_PRICE+" text);";

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
    public CollectAdapter(Context _context)
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




    public long Insert(CollectInfo collect,String  icon)
    {
        ContentValues newValue=new ContentValues();
        newValue.put(KEY_SHOPNAME,collect.ShopName);
        newValue.put(KEY_FOODNAME,collect.FoodName);
        newValue.put(KEY_PRICE,collect.Price);
        newValue.put(KEY_USERNAME,collect.UserName);
        newValue.put(KEY_ICON,icon);


        return db.insert(DB_TABLE, null, newValue);
    }

    public long deleteAll()
    {
        return db.delete(DB_TABLE, null, null);
    }
    public long deleteById(int id)
    {
        return db.delete(DB_TABLE, KEY_ID + "=" + "'" + id + "'", null);
    }



    public CollectInfo[] queryById(int id)
    {
        Cursor result=db.query(DB_TABLE, new String[]{}, KEY_ID+"="+id, null, null, null, null);
        return ConvertToCollectInfo(result);
    }

    public CollectInfo[] queryByName(String name)
    {
        Cursor result=db.query(DB_TABLE, new String[]{},KEY_FOODNAME + "=" + "'" + name + "'", null, null, null, null);
        return ConvertToCollectInfo(result);
    }


    public CollectInfo[] queryAll(String name)	{
        Cursor result=db.query(DB_TABLE, new String[]{},KEY_USERNAME + "=" + "'" + name + "'", null, null, null, null);
        return ConvertToCollectInfo(result);
    }

    private CollectInfo[] ConvertToCollectInfo(Cursor cursor){
        int resultCounts=cursor.getCount();
        if(resultCounts==0||!cursor.moveToFirst()){
            return null;
        }
        CollectInfo[] collects=new CollectInfo[resultCounts];
        for(int i=0;i<resultCounts;i++)
        {
            collects[i] = new CollectInfo(cursor.getString(cursor.getColumnIndex(KEY_SHOPNAME)), cursor.getString(cursor.getColumnIndex(KEY_FOODNAME)),cursor.getString(cursor.getColumnIndex(KEY_PRICE)),cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
            collects[i].ID = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            collects[i].Icon=cursor.getString(cursor.getColumnIndex(KEY_ICON));
           // collects[i].Icon = cursor.getString(cursor.getColumnIndex(KEY_ICON));
           /* Bitmap BitMap;
            byte[] bytes;*/

           // bytes = cursor.getBlob(cursor.getColumnIndex("bit_image"));
          //  collects[i].BitMap = BitmapFactory.decodeByteArray(cursor.getBlob(cursor.getColumnIndex("bit_image")), 0, cursor.getBlob(cursor.getColumnIndex("bit_image")).length, null);




            cursor.moveToNext();
        }

        return collects;

    }




  /* cursor = db.query("image", null, null, null, null, null, null);
    if (cursor.moveToFirst()) {

        // byte[] —> Bitmap
        byte[] bytes = cursor.getBlob(cursor.getColumnIndex("bit_image"));
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, null);
        Image.setImageBitmap(bitmap);
        cursor.close();
        db.close();
        helper.close();
    }*/




    public ArrayList Query() {

        //------将更新后的全部数据返回---------
        //定义一个键值对数组将数据返回到第一个列表Activity中更新进行显示
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        Cursor cursor = db.query(DB_TABLE, new String[]{}, null, null, null, null, null);
        while(cursor.moveToNext()){
            HashMap<String, Object> map = new HashMap<String, Object>();

            map.put("shopname", cursor.getString(cursor.getColumnIndex("shopname")));
            map.put("foodname", cursor.getString(cursor.getColumnIndex("foodname")));
            map.put("price", cursor.getString(cursor.getColumnIndex("price")));

            list.add(map);
        }
        return list;   //返回全部数据list用于列表显示
    }

}
