package com.example.hungry.adapter;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBAdapter {
	private static final String DB_NAME="people.db";
	private static final String DB_TABLE="peopleInfo";
	private static final int DB_VERSION=1;
	private static final String KEY_ID="_id";
	private static final String KEY_NAME="name";
	private static final String KEY_ADDRESS="address";
	private static final String KEY_PHONE_NUMBER="phone_number";
	private static final String KEY_USERPASS="userpass";


	private SQLiteDatabase db;
	private final Context context;
	private DBOpenHelper dbOpenHelper;

	private static class DBOpenHelper extends SQLiteOpenHelper
	{
		private static final String DB_CREATE="create table "+DB_TABLE+"("+KEY_ID+"" +
				" integer primary key autoincrement, "+KEY_NAME+" " +
				"text,"+KEY_ADDRESS+" " +
				"text,"+KEY_PHONE_NUMBER+" " +
			"text,"+KEY_USERPASS+" text);";

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
	public DBAdapter(Context _context)
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


	public long Insert(com.example.hungry.bean.PeopleInfo people)
	{
		ContentValues newValue=new ContentValues();
		newValue.put(KEY_NAME,people.Name);
		newValue.put(KEY_ADDRESS,people.Address);
		newValue.put(KEY_PHONE_NUMBER,people.Phone_Number);
		newValue.put(KEY_USERPASS,people.UserPass);

		return db.insert(DB_TABLE, null, newValue);
	}

	public long deleteAll()
	{
		return db.delete(DB_TABLE, null, null);
	}
	public long deleteByName(String name)
	{
		return db.delete(DB_TABLE, KEY_NAME + "=" + "'" + name + "'", null);
	}

	public long updateByName(com.example.hungry.bean.PeopleInfo people, String name)
	{
		ContentValues updateValue=new ContentValues();

		updateValue.put(KEY_NAME,people.Name);
		updateValue.put(KEY_ADDRESS,people.Address);
		updateValue.put(KEY_PHONE_NUMBER,people.Phone_Number);
		updateValue.put(KEY_USERPASS,people.UserPass);

		return db.update(DB_TABLE, updateValue, KEY_NAME + "=" + "'" + name + "'", null);
	}

	public com.example.hungry.bean.PeopleInfo[] queryById(int id)
	{
		Cursor result=db.query(DB_TABLE, new String[]{}, KEY_ID+"="+id, null, null, null, null);
		return ConvertToPeopleInfo(result);
	}

	public com.example.hungry.bean.PeopleInfo[] queryByName(String name)
	{
		Cursor result=db.query(DB_TABLE, new String[]{},KEY_NAME + "=" + "'" + name + "'", null, null, null, null);
		return ConvertToPeopleInfo(result);
	}

	public com.example.hungry.bean.PeopleInfo[] qurryAll()	{
		Cursor result=db.query(DB_TABLE, new String[]{}, null, null, null, null, null);
		return ConvertToPeopleInfo(result);
	}

	private com.example.hungry.bean.PeopleInfo[] ConvertToPeopleInfo(Cursor cursor){
		int resultCounts=cursor.getCount();
		if(resultCounts==0||!cursor.moveToFirst()){
			return null;
		}
		com.example.hungry.bean.PeopleInfo[] peoples=new com.example.hungry.bean.PeopleInfo[resultCounts];
		for(int i=0;i<resultCounts;i++)
		{
			peoples[i] = new com.example.hungry.bean.PeopleInfo(cursor.getString(cursor.getColumnIndex(KEY_NAME)), cursor.getString(cursor.getColumnIndex(KEY_ADDRESS)),cursor.getString(cursor.getColumnIndex(KEY_PHONE_NUMBER)),cursor.getString(cursor.getColumnIndex(KEY_USERPASS)));
			peoples[i].ID = cursor.getInt(cursor.getColumnIndex(KEY_ID));
			cursor.moveToNext();
		}
		return peoples;

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
