package com.example.hunger.bean;

import android.graphics.Bitmap;

public class CollectInfo {

    public String ShopName;
    public String FoodName;
    public String Price;
    public String UserName;
    public int ID;
    public Bitmap BitMap;
    public String Icon;

    public String getIcon() {

        String text="";
        text+=""+"";
        text+=""+"";
        text+=""+"";
        text+=""+Icon+"";
        text+=""+"";

        return text;
    }

    public void setIcon(String icon) {

        Icon = icon;
    }

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
        text+=""+"";
        text+=""+Price+"";

        return text;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getUserName() {
        String text="";
        text+=""+"";
        text+=""+"";
        text+=""+"";
        text+=""+UserName+"";
        text+=""+"";


        return text;
    }

    public void setUserName(String userName) {
        UserName = userName;
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




    public CollectInfo(String shopname, String foodname, String price,String username/*,String icon*/)
    {
        this.ShopName=shopname;
        this.FoodName=foodname;
        this.Price=price;
        this.UserName=username;
       // this.Icon=icon;
       // this.BitMap=bitmap;

    }


}
