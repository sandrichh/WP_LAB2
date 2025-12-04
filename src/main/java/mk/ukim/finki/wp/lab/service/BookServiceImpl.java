package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

//    @Override
//    public List<Book> searchBooks(String text, Double rating) {
//        return bookRepository.searchBooks(text,rating);
//    }

    @Override
    public Book findById(long id) {
        return bookRepository.findById(id).orElse(null);
    }

//    @Override
//    public void editBook(Long bookId, String title, String genre, Double avgRate, Long authorId) {
//        bookRepository.editBook(bookId,title,genre,avgRate,authorId);
//    }

    @Override
    public Book create(String title, String genre, double averageRating, Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow();
        Book book = new Book(title, genre, averageRating, author);
        return bookRepository.save(book);
    }

    @Override
    public Book update(Long id, String title, String genre, double averageRating, Long authorId) {
        Book book = bookRepository.findById(id).orElseThrow();
        Author author = authorRepository.findById(authorId).orElseThrow();

        book.setTitle(title);
        book.setGenre(genre);
        book.setAverageRating(averageRating);
        book.setAuthor(author);

        return bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findAllByAuthor(Long authorId) {
        return bookRepository.findAllByAuthor_Id(authorId);
    }


}
