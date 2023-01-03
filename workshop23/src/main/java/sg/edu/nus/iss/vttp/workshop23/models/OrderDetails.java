package sg.edu.nus.iss.vttp.workshop23.models;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class OrderDetails {
    private Integer orderId;
    private DateTime orderDate;
    private Integer customerId;
    private Double discountedPrice;
    private Double costPrice;

    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public DateTime getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(DateTime orderDate) {
        this.orderDate = orderDate;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Double getDiscountedPrice() {
        return discountedPrice;
    }
    public void setDiscountedPrice(Double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }
    public Double getCostPrice() {
        return costPrice;
    }
    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }


    public static OrderDetails create(SqlRowSet rs) {
        System.out.println("rs: " + rs);
        OrderDetails od = new OrderDetails();
        od.setOrderId(rs.getInt("order_id"));
        od.setOrderDate(new DateTime(
                DateTimeFormat.forPattern("dd/MM/yyyy")
                        .parseDateTime(rs.getString("order_date"))));
        od.setCustomerId(rs.getInt("customer_id"));
        od.setDiscountedPrice(rs.getDouble("discounted_price"));
        od.setCostPrice(rs.getDouble("cost_price"));
        return od;
    }

    public JsonObject toJSON(){
        return Json.createObjectBuilder()
                .add("order_id", getOrderId())
                .add("order_date", getOrderDate() != null ? getOrderDate().toString() : "")
                .add("customer_id", getCustomerId())
                .add("discounted_price", getDiscountedPrice().toString())
                .add("cost_price", getCostPrice().toString())
                .build();
    }
}
