package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findAll();
    List<Book> searchBooks(String text, Double rating);
    Book findBookById(long id);
    void editBook(Long bookId,String title,String genre,Double avgRate,Long authorId);
}
