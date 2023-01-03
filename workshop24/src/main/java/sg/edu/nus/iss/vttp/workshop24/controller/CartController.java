package sg.edu.nus.iss.vttp.workshop24.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.vttp.workshop24.models.LineItem;
import sg.edu.nus.iss.vttp.workshop24.models.PurchaseOrder;

@Controller
@RequestMapping(path = "/cart")
public class CartController {

    @PostMapping
    public String postCart(@RequestBody MultiValueMap<String, String> form, Model model, HttpSession session){
        List<LineItem> lineItems = (List<LineItem>) session.getAttribute("cart");

        if (lineItems == null){
            lineItems = new LinkedList<>();
            session.setAttribute("cart", lineItems);
        }

        String item = form.getFirst("item");
        Integer quantity = Integer.parseInt(form.getFirst("quantity"));
        lineItems.add(new LineItem(item, quantity));

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        String customerName = form.getFirst("name");

        purchaseOrder.setName(customerName);
        purchaseOrder.setLineItems(lineItems);

        session.setAttribute("checkoutcart", purchaseOrder);
        model.addAttribute("lineItems", lineItems);

        return "cart_template";
    }

    
}
