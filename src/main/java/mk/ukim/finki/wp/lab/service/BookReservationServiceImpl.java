package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Service;

@Service
public class BookReservationServiceImpl implements BookReservationService{
    @Override
    public BookReservation placeReservation(String bookTitle, String readerName, String readerAddress, int numberOfCopies) {
        return null;
    }

}
