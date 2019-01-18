package com.example.hunger.bean;



public class Order {
    public String Number;
    public String UserName;
    public String Address;
    public String FoodName;
    public String FoodPrice;
    public String Sum;
    public int  ID;

  /*  public String getID() {

        String text="";
        text+=""+ID+"";
        //int id=(int)Double.parseDouble(text);

        return text;

       // return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }*/

    public String getNumber() {
        String text="";
        text+=""+"";
        text+=""+"";
        text+=""+"";
        text+=""+"";
        text+=""+"";
        text+=""+"";
        text+=""+Number+"";

        return text;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getUserName() {
        String text="";
        text+=""+"";
        text+=""+UserName+"";
        text+=""+"";
        text+=""+"";
        text+=""+"";
        text+=""+"";
        text+=""+"";

        return text;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getAddress() {
        String text="";
        text+=""+"";
        text+=""+"";
        text+=""+Address+"   ";
        text+=""+"";
        text+=""+"";
        text+=""+"";
        text+=""+"";

        return text;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getFoodName() {

        String text="";
        text+=""+"";
        text+=""+"";
        text+=""+"";
        text+=""+FoodName+"";
        text+=""+"";
        text+=""+"";
        text+=""+"";

        return text;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public String getFoodPrice() {

        String text="";
        text+=""+"";
        text+=""+"";
        text+=""+"";
        text+=""+"";
        text+=""+FoodPrice+"";
        text+=""+"";
        text+=""+"";

        return text;
    }

    public void setFoodPrice(String foodPrice) {
        FoodPrice = foodPrice;
    }

    public String getSum() {

        String text="";
        text+=""+"";
        text+=""+"";
        text+=""+"";
        text+=""+"";
        text+=""+"";
        text+=""+Sum+"";
        text+=""+"";

        return text;
    }

    public void setSum(String sum) {
        Sum = sum;
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



    public Order( String name, String address, String foodname, String foodprice,String sum,String number)
    {
        this.UserName=name;
        this.Address=address;
        this.FoodName=foodname;
        this.FoodPrice= foodprice;
        this.Sum= sum;
        this.Number= number;

    }

    public Order( String address,String number,String sum)
    {

        this.Address=address;
        this.Number= number;
        this.Sum= sum;

    }


    public String show(){
        String text="";
        text+="ID:"+"     "+ID+"   "+"\n\n\n";
        text+="用户名:"+"     "+UserName+"   "+"\n\n\n";
        text+="地址:"+"     "+Address+"   "+"\n\n\n";
        text+="菜品名:"+"     "+FoodName+"   "+"\n\n\n";
        text+="单价:"+"     "+FoodPrice+"   "+"\n\n\n";
        text+="总价:"+"     "+Sum+"   "+"\n\n\n";
        text+="数量:"+"     "+Number+"   "+"\n";

        return  text;
    }





}
