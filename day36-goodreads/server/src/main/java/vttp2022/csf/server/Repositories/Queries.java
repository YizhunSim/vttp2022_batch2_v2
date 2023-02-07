package vttp2022.csf.server.Repositories;

public interface Queries {
    public static String SQL_SELECT_BOOKS = "select * from book2018 limit 20";
    
    public static String SQL_SELECT_BOOK_BY_BOOKID = "select * from book2018 WHERE book_id = ?";
}
