package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return bookRepository.searchBooks(text,rating);
    }

    @Override
    public Book findBookById(long id) {
        return bookRepository.findBookById(id);
    }

    @Override
    public void editBook(Long bookId, String title, String genre, Double avgRate, Long authorId) {
        bookRepository.editBook(bookId,title,genre,avgRate,authorId);
    }


}
