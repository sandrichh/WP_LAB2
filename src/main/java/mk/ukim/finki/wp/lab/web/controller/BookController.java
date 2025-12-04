package mk.ukim.finki.wp.lab.web.controller;


import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService ;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

//    @GetMapping
//    public String getBooksPage(@RequestParam(required = false) String error,
//                               Model model,
//                               @RequestParam(required = false) String filterName,
//                               @RequestParam(required = false) Double filterRating){
//
//        List<Book> books;
//
//        if (filterName != null && filterRating != null && !filterName.isEmpty() && filterRating != 0){
//            books = bookService.listAll().stream().filter(t->t.getTitle().contains(filterName) && t.getAverageRating()>= filterRating).toList();
//            model.addAttribute("books", books);
//        }
//        else{
//            model.addAttribute("books", bookService.listAll());
//        }
//
//        return "listBooks";
//    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) Long authorId,
                               @RequestParam(required = false) String filterName,
                               @RequestParam(required = false) Double filterRating,
                               Model model) {

        List<Book> books;

        if (authorId != null) {
            books = bookService.findAllByAuthor(authorId);
        }
        else if (filterName != null && filterRating != null &&
                !filterName.isEmpty() && filterRating != 0) {

            books = bookService.listAll().stream()
                    .filter(b -> b.getTitle().contains(filterName)
                            && b.getAverageRating() >= filterRating)
                    .toList();
        }
        else {
            books = bookService.listAll();
        }

        model.addAttribute("books", books);
        model.addAttribute("avtori", authorService.findAll());
        model.addAttribute("selectedAuthorId", authorId);

        return "listBooks";
    }



    @PostMapping
    public String filterBooks(@RequestParam String filterName,
                              @RequestParam Double filterRating){
        return "redirect:/books?filterName="+filterName+"&filterRating="+filterRating;
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id,
                           Model model){

        Book kniga = bookService.findById(id);
        model.addAttribute("kniga",kniga);
        model.addAttribute("isEdit",true);
        model.addAttribute("avtori",authorService.findAll());
        return "book-form";
    }


    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Long id,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId){
        bookService.update(id,title,genre,averageRating,authorId);
        return "redirect:/books";
    }

    @GetMapping("/add")
    public String addBook(Model model){

        model.addAttribute("isEdit",false);
        model.addAttribute("avtori",authorService.findAll());
        return "book-form";
    }

    @PostMapping("/add")
    public String addBook( @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId){
//        Author a = authorService.findAll().stream().filter(p->p.getId().equals(authorId)).findFirst().orElse(null);
//        bookService.listAll().add(new Book(title,genre,averageRating,a));
        bookService.create(title, genre, averageRating, authorId);
        return "redirect:/books";
    }

    @GetMapping("/delete/{bookId}")
    public String deleteBook(@PathVariable Long bookId){
//        bookService.listAll().removeIf(k->k.getId()==bookId);
        bookService.delete(bookId);
        return "redirect:/books";
    }




}
