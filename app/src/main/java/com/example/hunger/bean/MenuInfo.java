package com.example.hunger.bean;

import android.graphics.Bitmap;

public class MenuInfo {

    public String ShopName;
    public String FoodName;
    public String Price;
    public String Picture;

    public int ID;


    public String getShopName() {
        String text="";
        text+=""+"";
        text+=""+ShopName+"";
        text+=""+"";
        text+=""+"";
        text+=""+"";
        return text;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public String getFoodName() {

        String text="";
        text+=""+"";
        text+=""+"";
        text+=""+FoodName+"";
        text+=""+"";
        text+=""+"";
        return text;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public String getPrice() {

        String text="";
        text+=""+"";
        text+=""+"";
        text+=""+"";
        text+=""+Price+"";
        text+=""+"";
        return text;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getPicture() {

        String text="";
        text+=""+"";
        text+=""+"";
        text+=""+"";
        text+=""+"";
        text+=""+Picture+"";
        return text;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public int getID() {

        String text="";
        text+=""+ID+"";
        int id=(int)Double.parseDouble(text);
        return id;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public MenuInfo(String shopname, String foodname, String price, String picture)
    {
        this.ShopName=shopname;
        this.FoodName=foodname;
        this.Price=price;
        this.Picture= picture;

    }





   /* public String showInfo(){
        String text="";
        text+="ID:"+"     "+ID+"   "+"\n\n\n";
        text+="姓名:"+"     "+Name+"   "+"\n\n\n";
        text+="地址:"+"     "+Address+"   "+"\n\n\n";
        text+="电话号码:"+"     "+Phone_Number+"   "+"\n";

        return  text;
    }*/


}
