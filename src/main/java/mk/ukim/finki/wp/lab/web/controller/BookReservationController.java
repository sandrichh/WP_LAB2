package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookService;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bookReservation")
public class BookReservationController {

    private final BookService bookService;
    private final BookReservationService reservationService;

    public BookReservationController(BookService bookService,
                                     BookReservationService reservationService) {
        this.bookService = bookService;
        this.reservationService = reservationService;
    }

    @PostMapping
    public String makeReservation(@RequestParam Long bookId,
                                  @RequestParam String readerName,
                                  @RequestParam String readerAddress,
                                  @RequestParam int numCopies,
                                  HttpServletRequest request,
                                  Model model) {

        Book book = bookService.findById(bookId);

        String ip = request.getRemoteAddr();

        BookReservation reservation =
                reservationService.placeReservation(book.getTitle(), readerName, readerAddress, numCopies);

        model.addAttribute("readerName", readerName);
        model.addAttribute("ipAddress", ip);
        model.addAttribute("readerAddress",readerAddress);
        model.addAttribute("title",book.getTitle());
        model.addAttribute("numCopies",numCopies);

        return "reservationConfirmation";
    }
}
