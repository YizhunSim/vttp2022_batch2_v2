package sg.edu.nus.iss.vttp.workshop24.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.vttp.workshop24.models.LineItem;

import static sg.edu.nus.iss.vttp.workshop24.repository.Queries.*;

@Repository
public class LineItemRepository {
    @Autowired 
    private JdbcTemplate template;

    public void addAllLineItem(List<LineItem> items, String orderId){
        List<Object[]> data = items.stream()
        .map(li -> {
            Object[] i = new Object[3];
            i[0] = li.getDescription();
            i[1] = li.getQuantity();
            i[2] = orderId;
            return i;
        }).toList();

        template.batchUpdate(SQL_INSERT_LINE_ITEM, data);
    }

}
