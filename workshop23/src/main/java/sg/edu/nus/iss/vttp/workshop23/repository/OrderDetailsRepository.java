package sg.edu.nus.iss.vttp.workshop23.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.vttp.workshop23.models.OrderDetails;

import static sg.edu.nus.iss.vttp.workshop23.repository.Queries.*;

@Repository
public class OrderDetailsRepository {
    @Autowired
    private JdbcTemplate template;

    public OrderDetails getDiscountedOrderDetails(Integer orderId){
        final List<OrderDetails> orderDetails = new LinkedList<>();
        SqlRowSet result = template.queryForRowSet(SQL_SELECT_ORDER_DETAILS_WITH_DISCOUNT, orderId);

        while(result.next()){
            orderDetails.add(OrderDetails.create(result));
        }

        return orderDetails.get(0);

    }
}
