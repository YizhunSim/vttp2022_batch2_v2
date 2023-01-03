package sg.edu.nus.iss.vttp.workshop23.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.vttp.workshop23.models.OrderDetails;
import sg.edu.nus.iss.vttp.workshop23.repository.OrderDetailsRepository;

@Service
public class OrderDetailsService {
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public OrderDetails getDiscountedOrderDetails(Integer orderId){
        return orderDetailsRepository.getDiscountedOrderDetails(orderId);
    }
}
