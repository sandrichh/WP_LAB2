package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryBookRepository implements BookRepository{
    @Override
    public List<Book> findAll() {
        return DataHolder.books;
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return DataHolder.books.stream().filter(b -> b.getTitle().contains(text) && b.getAverageRating()>=rating).collect(Collectors.toList());
    }

    @Override
    public Book findBookById(long id) {
        return DataHolder.books.stream().filter(b-> b.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void editBook(Long bookId, String title, String genre, Double avgRate, Long authorId) {
        Book kniga= DataHolder.books.stream().filter(b ->b.getId()==bookId).findFirst().orElse(null);
        Author avtor= DataHolder.authors.stream().filter(av -> Long.compare(av.getId(),authorId)==0).findFirst().orElse(null);
        kniga.setAuthor(avtor);
        kniga.setTitle(title);
        kniga.setGenre(genre);
        kniga.setAverageRating(avgRate);
    }


}
