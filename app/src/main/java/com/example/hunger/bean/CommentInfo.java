package com.example.hunger.bean;

public class CommentInfo {

    public String OrderId;
    public String FoodName;
    public String UserName;
    public String Comment;

    public int ID;


    public String getOrderId() {

        String text="";
        text+=""+"";
        text+=""+OrderId+"";
        text+=""+"";
        text+=""+"";
        text+=""+"";

        return text;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
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

    public String getComment() {

        String text="";
        text+=""+"";
        text+=""+"";
        text+=""+"";
        text+=""+"";
        text+=""+Comment+"";
        return text;
    }

    public void setComment(String comment) {
        Comment = comment;
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

    public CommentInfo(String orderid, String foodname, String username, String comment)
    {
        this.OrderId=orderid;
        this.FoodName=foodname;
        this.UserName=username;
        this.Comment= comment;

    }



    public String showInfo(){
        String text="";
        text+="ID:"+"     "+ID+"   "+"\n\n\n";
        text+="订单编号:"+"     "+OrderId+"   "+"\n\n\n";
        text+="菜品名:"+"     "+FoodName+"   "+"\n\n\n";
        text+="买家:"+"     "+UserName+"   "+"\n\n\n";
        text+="评价内容:"+"     "+Comment+"   "+"\n";

        return  text;
    }


}
