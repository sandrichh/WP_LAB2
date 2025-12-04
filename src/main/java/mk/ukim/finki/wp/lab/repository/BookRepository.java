package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
//    List<Book> findAll();
//    List<Book> searchBooks(String text, Double rating);
//    Book findById(long id);
//    void editBook(Long bookId,String title,String genre,Double avgRate,Long authorId);
    List<Book> findAllByAuthor_Id(Long authorId);
}
