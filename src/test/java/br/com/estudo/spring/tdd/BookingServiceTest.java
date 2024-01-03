package br.com.estudo.spring.tdd;

import br.com.estudo.spring.tdd.model.BookingModel;
import br.com.estudo.spring.tdd.repository.BookingRepository;
import br.com.estudo.spring.tdd.service.BookingService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
public class BookingServiceTest {
    @MockBean
    private BookingRepository bookingRepository;


    @TestConfiguration
    static class BookingServiceTestConfiguration {
        @Bean
        public BookingService bookingService(){
            return new BookingService();
        }
    }

    @Autowired
    BookingService bookingService;


    @Test
    public void bookingTestServiceDaysCalculator(){
        String name = "Joao";
        int days = bookingService.daysCalculatorWithDatabase(name);

        Assertions.assertEquals(days, 10);
    }

    @BeforeEach
    public void setup(){
        LocalDate checkIn = LocalDate.parse("2024-01-10");
        LocalDate checkout = LocalDate.parse("2024-01-20");

        BookingModel bookingModel = new BookingModel("1", "Joao", checkIn, checkout, 2);

        Mockito.when(bookingRepository.findByReserveName(Mockito.anyString())).thenReturn(Optional.of(bookingModel));
    }
}
