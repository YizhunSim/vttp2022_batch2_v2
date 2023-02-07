package vttp2022.csf.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.csf.server.Repositories.BooksRepository;
import vttp2022.csf.server.models.Book;

@Service
public class BookService {
    
    @Autowired
    private BooksRepository bookRepo;

    public List<Book> getBooks(){
        return bookRepo.getBooks();
    }

    public Optional<Book> getBookById(String bookId){
        return bookRepo.getBook(bookId);
    }
}
