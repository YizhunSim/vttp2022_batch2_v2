package vttp2022.csf.server.Repositories;

import java.lang.StackWalker.Option;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import static vttp2022.csf.server.Repositories.Queries.*;
import vttp2022.csf.server.models.Book;

@Repository
public class BooksRepository {
    
    @Autowired
    private JdbcTemplate template;

    public List<Book> getBooks(){
        SqlRowSet rs = template.queryForRowSet(SQL_SELECT_BOOKS);
        List<Book> results = new LinkedList<>();
        while (rs.next()){
            results.add(Book.create(rs));
        }

        return results;
    }

    public Optional<Book> getBook(String bookId){
        SqlRowSet rs = template.queryForRowSet(SQL_SELECT_BOOK_BY_BOOKID, bookId);

        if (!rs.next()){
            return Optional.empty();
        }

        return Optional.of(Book.create(rs));
    }
}
