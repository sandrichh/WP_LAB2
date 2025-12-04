package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    //public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();
//    public static List<Author> authors = new ArrayList<>();

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataHolder(AuthorRepository authorRepository,
                      BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }
    @PostConstruct
    public void init(){

        if (authorRepository.count() == 0 && bookRepository.count() == 0){
            Author a1 = authorRepository.save(new Author("Marko","Markovski","Makedonija", "bio1"));
            Author a2 = authorRepository.save(new Author("Petko","Petkovski","Srbija","bio2"));
            Author a3 = authorRepository.save(new Author("Trajko","Trajkovski","Slovenija","bio3"));

            bookRepository.save(new Book("The Alchemist", "Adventure", 4.1, a1));
            bookRepository.save(new Book("Pride and Prejudice", "Romance", 4.7, a2));
            bookRepository.save(new Book("The Da Vinci Code", "Thriller", 3.9, a3));
            bookRepository.save(new Book("To Kill a Mockingbird", "Drama", 4.8, a1));
            bookRepository.save(new Book("The Martian", "Science Fiction", 4.6, a2));
            bookRepository.save(new Book("Ender's Game", "Sci-Fi", 4.2, a3));
            bookRepository.save(new Book("Frankenstein", "Horror", 3.8, a1));
            bookRepository.save(new Book("The Odyssey", "Epic", 4.3, a2));
            bookRepository.save(new Book("Moby Dick", "Adventure", 3.7, a3));
            bookRepository.save(new Book("Catch-22", "Satire", 4.0, a1));
            bookRepository.save(new Book("The Great Gatsby", "Classic", 4.4, a2));

        }


//        authors.add(new Author("Marko","Markovski","Makedonija", "bio1"));
//        authors.add(new Author("Petko","Petkovski","Srbija","bio2"));
//        authors.add(new Author("Trajko","Trajkovski","Slovenija","bio3"));
//
//
//        books.add(new Book("The Alchemist", "Adventure", 4.1, authors.get(0)));
//        books.add(new Book("Pride and Prejudice", "Romance", 4.7,authors.get(1)));
//        books.add(new Book("The Da Vinci Code", "Thriller", 3.9, authors.get(2)));
//        books.add(new Book("To Kill a Mockingbird", "Drama", 4.8, authors.get(0)));
//        books.add(new Book("The Martian", "Science Fiction", 4.6, authors.get(1)));
//        books.add(new Book("Ender's Game", "Sci-Fi", 4.2, authors.get(2)));
//        books.add(new Book("Frankenstein", "Horror", 3.8, authors.get(0)));
//        books.add(new Book("The Odyssey", "Epic", 4.3, authors.get(1)));
//        books.add(new Book("Moby Dick", "Adventure", 3.7, authors.get(2)));
//        books.add(new Book("Catch-22", "Satire", 4.0, authors.get(0)));
//        books.add(new Book("The Great Gatsby", "Classic", 4.4, authors.get(1)));
    }
}
