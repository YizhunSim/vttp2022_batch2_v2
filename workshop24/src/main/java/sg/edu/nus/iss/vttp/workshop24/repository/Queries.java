package sg.edu.nus.iss.vttp.workshop24.repository;

public class Queries {
    public static String SQL_INSERT_PURCHASE_ORDER = """
            INSERT INTO purchase_order(order_id, name, order_date) VALUES (?, ?, sysdate())
            """;
    public static String SQL_INSERT_LINE_ITEM = """
            INSERT INTO line_item (description, quantity, order_id) VALUES (?, ? , ?)
            """;
}