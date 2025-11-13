package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;

public interface BookService {
    List<Book> listAll();
    List<Book> searchBooks(String text, Double rating);
    Book findBookById (long id);
    void editBook(Long bookId,String title,String genre,Double avgRate,Long authorId);

}
