package com.example.hunger.adapter;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hunger.bean.CommentInfo;
import com.example.hunger.bean.PeopleInfo;

import java.util.ArrayList;
import java.util.HashMap;

public class CommentAdapter {
    private static final String DB_NAME="comment.db";
    private static final String DB_TABLE="commentInfo";
    private static final int DB_VERSION=1;
    private static final String KEY_ID="_id";
    private static final String KEY_ORDERID="orderid";
    private static final String KEY_FOODNAME="foodname";
    private static final String KEY_USERNAME="username";
    private static final String KEY_COMMENT="comment";


    private SQLiteDatabase db;
    private final Context context;
    private DBOpenHelper dbOpenHelper;

    private static class DBOpenHelper extends SQLiteOpenHelper
    {
        private static final String DB_CREATE="create table "+DB_TABLE+"("+KEY_ID+"" +
                " integer primary key autoincrement, "+KEY_ORDERID+" " +
                "text,"+KEY_FOODNAME+" " +
                "text,"+KEY_USERNAME+" " +
                "text,"+KEY_COMMENT+" text);";

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
    public CommentAdapter(Context _context)
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



    public long Insert(CommentInfo comment)
    {
        ContentValues newValue=new ContentValues();
        newValue.put(KEY_ORDERID,comment.OrderId);
        newValue.put(KEY_FOODNAME,comment.FoodName);
        newValue.put(KEY_USERNAME,comment.UserName);
        newValue.put(KEY_COMMENT,comment.Comment);

        return db.insert(DB_TABLE, null, newValue);
    }

    public long deleteAll()
    {
        return db.delete(DB_TABLE, null, null);
    }

    public long deleteByOrderId(int id ,String name)
    {
        return db.delete(DB_TABLE, KEY_ID + "=" + "'" + id + "'"+
                "and" +" "+KEY_USERNAME + "=" + "'" + name + "'", null);
    }

    public long updateById(CommentInfo comment,int id)
    {
        ContentValues updateValue=new ContentValues();

        updateValue.put(KEY_COMMENT,comment.Comment);


        return db.update(DB_TABLE, updateValue, KEY_ID + "=" + "'" + id+ "'", null);
    }


    public CommentInfo[] queryByFoodName(String foodname)
    {
        Cursor result=db.query(DB_TABLE, new String[]{}, KEY_FOODNAME+"="+foodname, null, null, null, null);
        return ConvertToPeopleInfo(result);
    }



    public CommentInfo[] queryAll()	{
        Cursor result=db.query(DB_TABLE, new String[]{}, null, null, null, null, null);
        return ConvertToPeopleInfo(result);
    }

    private CommentInfo[] ConvertToPeopleInfo(Cursor cursor){
        int resultCounts=cursor.getCount();
        if(resultCounts==0||!cursor.moveToFirst()){
            return null;
        }
        CommentInfo[] comments=new CommentInfo[resultCounts];
        for(int i=0;i<resultCounts;i++)
        {
            comments[i] = new CommentInfo(cursor.getString(cursor.getColumnIndex(KEY_ORDERID)), cursor.getString(cursor.getColumnIndex(KEY_FOODNAME)),cursor.getString(cursor.getColumnIndex(KEY_USERNAME)),cursor.getString(cursor.getColumnIndex(KEY_COMMENT)));
            comments[i].ID = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            cursor.moveToNext();
        }

        return comments;

    }

    public ArrayList Query() {

        //------将更新后的全部数据返回---------
        //定义一个键值对数组将数据返回到第一个列表Activity中更新进行显示
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        Cursor cursor = db.query(DB_TABLE, new String[]{}, null, null, null, null, null);
        while(cursor.moveToNext()){
            HashMap<String, Object> map = new HashMap<String, Object>();

            map.put("orderid", cursor.getString(cursor.getColumnIndex("orderid")));
            map.put("foodname", cursor.getString(cursor.getColumnIndex("foodname")));
            map.put("username", cursor.getString(cursor.getColumnIndex("username")));
            map.put("comment", cursor.getString(cursor.getColumnIndex("comment")));

            list.add(map);
        }
        return list;   //返回全部数据list用于列表显示
    }

}
