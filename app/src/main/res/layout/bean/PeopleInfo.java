package com.example.hungry.bean;

public class PeopleInfo {

	public int ID;
	public String Name;
	public String Phone_Number;
	public String Address;
	public String UserPass;

	
	public PeopleInfo( String name, String address, String phone_number, String userpass)
	{
		this.Name=name;
		this.Phone_Number=phone_number;
		this.Address=address;
		this.UserPass= userpass;

	}

	public String showInfo(){
		String text="";
		text+="ID:"+ID+"   ";
		text+="姓名:"+Name+"   ";
		text+="地址:"+Address+"   ";
		text+="电话号码:"+Phone_Number+"   ";
		text+="密码:"+UserPass+"   "+";\n";
		return  text;
	}
}
