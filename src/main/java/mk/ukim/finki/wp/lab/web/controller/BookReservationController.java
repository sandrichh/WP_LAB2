package mk.ukim.finki.wp.lab.web.controller;


import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/bookReservation")
public class BookReservationController {
    private final BookReservationService bookReservationService;

    public BookReservationController(BookReservationService bookReservationService) {
        this.bookReservationService = bookReservationService;
    }

    @PostMapping
    public String reserveBooks(@RequestParam String readerName, @RequestParam String readerAddress, @RequestParam int numCopies, @RequestParam String bookTitle){
        return "redirect:/bookReservation?readerName="+readerName+"&readerAddress="+readerAddress+"&numCopies="+numCopies+"&bookTitle="+bookTitle;
    }

    @GetMapping
    public String getBookReservation(@RequestParam String readerName, @RequestParam String readerAddress, @RequestParam int numCopies, @RequestParam String bookTitle, HttpServletRequest req, Model model){

        model.addAttribute("readerName",readerName);
        model.addAttribute("readerAddress",readerAddress);
        model.addAttribute("ipAddress",req.getRemoteAddr());
        model.addAttribute("numCopies",numCopies);
        model.addAttribute("bookTitle",bookTitle);


        return "reservationConfirmation";
    }

}
