package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;

public interface BookService {
    List<Book> listAll();
    //List<Book> searchBooks(String text, Double rating);
    Book findById(long id);
    //void editBook(Long bookId,String title,String genre,Double avgRate,Long authorId);

    Book create(String title, String genre, double averageRating, Long authorId);

    Book update(Long id, String title, String genre, double averageRating, Long authorId);

    void delete(Long id);

    List<Book> findAllByAuthor(Long authorId);

}
