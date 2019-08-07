/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetfood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import planetfood.dbutil.DBConnection;
import planetfood.pojo.OrderDetail;
import planetfood.pojo.Orders;

/**
 *
 * @author Shivam
 */
public class OrderDao {
    public static String getNewId() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        int id = 101;
        ResultSet rs = st.executeQuery("select count(*) from orders");
        if(rs.next()) {
            id = id + rs.getInt(1);
        }
        return "O" + id;
    }
    public static HashMap<String, Orders> getAllOrdersByAdmin() throws SQLException{
        HashMap<String, Orders> ordrList = new HashMap<>();
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement(); 
        ResultSet rs = st.executeQuery("select * from orders");
        while(rs.next()) {
            Orders ord = new Orders();
            String ordrid = rs.getString("ORD_ID");
            ord.setOrd_id(rs.getString("ORD_ID"));
            java.sql.Date date = rs.getDate("ORD_DATE");
            SimpleDateFormat sdf = new SimpleDateFormat();
            String d1 = sdf.format(date);
            ord.setDate(d1);
            ord.setGst(Double.parseDouble(rs.getString("GST")));
            ord.setGst_amount(Double.parseDouble(rs.getString("GST_AMOUNT")));
            ord.setDiscount(Double.parseDouble(rs.getString("DISCOUNT")));
            ord.setGrnd_total(Double.parseDouble(rs.getString("GRAND_TOTAL")));
            ord.setUserId(rs.getString("USERID"));
            ord.setOrderAmt(Double.parseDouble(rs.getString("order_amt")));
            ordrList.put(ordrid, ord);
        }
        return ordrList;
    }
     public static HashMap<String, Orders> getDateOrders(Date sdate, Date edate) throws SQLException{
        HashMap<String, Orders> ordrList = new HashMap<>();
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select * from orders where ord_date between ? and ?");
        long ms1 = sdate.getTime();
        long ms2 = edate.getTime();
        java.sql.Date start_date = new java.sql.Date(ms1);
       java.sql.Date end_date = new java.sql.Date(ms2);

        ps.setDate(1, start_date);
        ps.setDate(2, end_date);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            Orders ord = new Orders(); 
            String ord_id = rs.getString("ORD_ID");
            ord.setOrd_id(rs.getString("ORD_ID"));
            java.sql.Date d=rs.getDate("ord_date");
            SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
            String dateStr=sdf.format(d);
            ord.setDate(dateStr);
            ord.setOrderAmt(Double.parseDouble(rs.getString("ORDER_AMT")));
            ord.setGst(Double.parseDouble(rs.getString("GST")));
            ord.setGst_amount(Double.parseDouble(rs.getString("GST_AMOUNT")));
            ord.setDiscount(Double.parseDouble(rs.getString("DISCOUNT")));
            ord.setGrnd_total(Double.parseDouble(rs.getString("GRAND_TOTAL")));
            ord.setUserId(rs.getString("USERID"));
            ordrList.put(ord_id, ord);
        }
        return ordrList;
    }
     public static boolean addOrders(Orders ord,ArrayList<OrderDetail> orderlist) throws Exception {
         Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement("insert into orders values(?,?,?,?,?,?,?,?)");
         ps.setString(1, ord.getOrd_id());
         String str = ord.getDate();
         SimpleDateFormat sdf  = new SimpleDateFormat("dd-MM-yyyy");
         java.util.Date date1 = sdf.parse(str);
         java.sql.Date date2 = new java.sql.Date(date1.getTime());
         ps.setDate(2, date2);
         ps.setDouble(3, ord.getGst());
         ps.setDouble(4, ord.getGst_amount());
         ps.setDouble(5, ord.getDiscount());
         ps.setDouble(6, ord.getGrnd_total());
         ps.setString(7, ord.getUserId());
         ps.setDouble(8, ord.getOrderAmt());
         int i = ps.executeUpdate();
         PreparedStatement pss = conn.prepareStatement("insert into order_details values(?, ?, ?, ?)");
         int count = 0,y;
         for(OrderDetail order : orderlist) {
             pss.setString(1, order.getOrdId());
             pss.setString(2, order.getProdId());
             pss.setDouble(3, order.getQuantity());
             pss.setDouble(4, order.getCost());
             y = pss.executeUpdate();
             count =+ y;
        }
         if(i>0 && count == orderlist.size()) {
             return true;
         }
         else 
             return false;
    }
      public static HashMap<String, Orders> getAllOrdersByCashier(String userId, Date sdate, Date edate) throws SQLException{
       HashMap<String, Orders> ordrList = new HashMap<>();
         Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement("select * from orders where userid = ? and ord_date between ? and ?");
         ps.setString(1, userId);
         //long date1 = sdate.
         java.sql.Date d1 = new java.sql.Date(sdate.getTime());
         java.sql.Date d2 = new java.sql.Date(edate.getTime());
         ps.setDate(2, d1);
         ps.setDate(3, d2);
         ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            Orders ord = new Orders();
            String ordrid = rs.getString("ORD_ID");
            ord.setOrd_id(rs.getString("ORD_ID"));
            java.sql.Date date = rs.getDate("ORD_DATE");
            SimpleDateFormat sdf = new SimpleDateFormat();
            String date1 = sdf.format(date);
            ord.setDate(date1);
            ord.setGst(Double.parseDouble(rs.getString("GST")));
            ord.setGst_amount(Double.parseDouble(rs.getString("GST_AMOUNT")));
            ord.setDiscount(Double.parseDouble(rs.getString("DISCOUNT")));
            ord.setGrnd_total(Double.parseDouble(rs.getString("GRAND_TOTAL")));
            ord.setUserId(rs.getString("USERID"));
            ord.setOrderAmt(Double.parseDouble(rs.getString("order_amt")));
            ordrList.put(ordrid, ord);
        }
        return ordrList;
    }
    public static Orders getSingleOrder(String id) throws SQLException {
        Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement("select * from orders where ord_id = ?");
         ps.setString(1, id);
         ResultSet rs = ps.executeQuery();
         Orders ord = null;
         if(rs.next()) {
             ord = new Orders();
             ord.setOrd_id(id);
             java.sql.Date date =  rs.getDate("ORD_DATE");
             SimpleDateFormat sdf = new SimpleDateFormat();
             String date1 = sdf.format(date);
             ord.setDate(date1);
             ord.setGst(Double.parseDouble(rs.getString("GST")));
             ord.setGst_amount(Double.parseDouble(rs.getString("GST_AMOUNT")));
             ord.setDiscount(Double.parseDouble(rs.getString("DISCOUNT")));
             ord.setGrnd_total(Double.parseDouble(rs.getString("GRAND_TOTAL")));
             ord.setUserId(rs.getString("USERID"));
             ord.setOrderAmt(Double.parseDouble(rs.getString("ORDER_AMT")));
         }
        return ord;
    }
}
