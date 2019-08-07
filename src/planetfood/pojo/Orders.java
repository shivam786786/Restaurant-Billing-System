package planetfood.pojo;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shivam
 */
public class Orders {
    private String ord_id;
    private String date;
    private double gst;
    private double gst_amount;
    private double discount;
    private double orderAmt;

    public double getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(double orderAmt) {
        this.orderAmt = orderAmt;
    }
    private double grnd_total;
    private String userId;

    public String getOrd_id() {
        return ord_id;
    }

    public void setOrd_id(String ord_id) {
        this.ord_id = ord_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

 

    public double getGst() {
        return gst;
    }

    public void setGst(double gst) {
        this.gst = gst;
    }

    public double getGst_amount() {
        return gst_amount;
    }

    public void setGst_amount(double gst_amount) {
        this.gst_amount = gst_amount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getGrnd_total() {
        return grnd_total;
    }

    public void setGrnd_total(double grnd_total) {
        this.grnd_total = grnd_total;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
}
