package mk.ukim.finki.wp.lab.web;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "BookReservationServlet", urlPatterns = "/bookReservation")
public class BookReservationServlet extends HttpServlet {
    private final BookReservationService bookReservationService;
    private final SpringTemplateEngine templateEngine;

    public BookReservationServlet(BookReservationService bookReservationService, SpringTemplateEngine templateEngine) {
        this.bookReservationService = bookReservationService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        String bookTitle = req.getParameter("bookTitle");
        String readerName = req.getParameter("readerName");
        String readerAddress = req.getParameter("readerAddress");
        String numberOfCopies = req.getParameter("numberOfCopies");

        WebContext context = new WebContext(webExchange);
        context.setVariable("readerName",readerName);
        context.setVariable("readerAddress",readerAddress);
        context.setVariable("bookTitle",bookTitle);
        context.setVariable("numberOfCopies",numberOfCopies);

        context.setVariable("ipAddress",req.getRemoteAddr());
        templateEngine.process("reservationConfirmation.html",context,resp.getWriter());


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookTitle = req.getParameter("bookTitle");
        String readerName = req.getParameter("readerName");
        String readerAddress = req.getParameter("readerAddress");
        int numberOfCopies = Integer.parseInt(req.getParameter("numCopies"));

        bookReservationService.placeReservation(bookTitle,readerName,readerAddress, numberOfCopies);

        String params = String.format("bookTitle=%s&readerName=%s&readerAddress=%s&numberOfCopies=%s",bookTitle,readerName,readerAddress,numberOfCopies);

        resp.sendRedirect("/bookReservation?"+params);
    }
}
