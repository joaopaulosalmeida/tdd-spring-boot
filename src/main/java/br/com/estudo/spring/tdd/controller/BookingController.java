package br.com.estudo.spring.tdd.controller;

import br.com.estudo.spring.tdd.model.BookingModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @GetMapping
    @ResponseBody
    public String getAll(){
        return "OK";
    }


    @PostMapping
    public ResponseEntity<BookingModel> save(BookingModel bookingModel){
        return ResponseEntity.status(HttpStatus.OK).body(bookingModel);
    }

    @DeleteMapping
    public ResponseEntity delete(String idBooking){
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping
    public ResponseEntity<BookingModel> update(BookingModel bookingModel){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookingModel);
    }


}
