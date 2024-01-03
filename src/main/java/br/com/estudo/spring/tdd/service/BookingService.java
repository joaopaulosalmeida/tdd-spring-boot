package br.com.estudo.spring.tdd.service;

import br.com.estudo.spring.tdd.model.BookingModel;
import br.com.estudo.spring.tdd.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;
    public int daysCalculatorWithDatabase(String name) {
        Optional<BookingModel> reserveBooking = bookingRepository.findByReserveName(name);
        return Period.between(reserveBooking.get().getCheckIn(), reserveBooking.get().getCheckOut()).getDays();
    }
}
