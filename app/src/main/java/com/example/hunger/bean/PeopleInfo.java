package com.example.hunger.bean;

public class PeopleInfo {

	public String Name;
	public String Phone_Number;
	public String Address;
	public String UserPass;

	public int ID;


	public PeopleInfo( String name, String address, String phone_number, String userpass)
	{
		this.Name=name;
		this.Phone_Number=phone_number;
		this.Address=address;
		this.UserPass= userpass;

	}

	public PeopleInfo(String address, String phone_number)
	{

		this.Phone_Number=phone_number;
		this.Address=address;


	}

	public PeopleInfo(String userpass)
	{

		this.UserPass= userpass;

	}



	public String showInfo(){
		String text="";
		text+="ID:"+"     "+ID+"   "+"\n\n\n";
		text+="姓名:"+"     "+Name+"   "+"\n\n\n";
		text+="地址:"+"     "+Address+"   "+"\n\n\n";
		text+="电话号码:"+"     "+Phone_Number+"   "+"\n";

		return  text;
	}


}
