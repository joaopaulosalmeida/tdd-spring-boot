package br.com.estudo.spring.tdd;


import br.com.estudo.spring.tdd.controller.BookingController;
import br.com.estudo.spring.tdd.model.BookingModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void bookingGetAllTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/bookings"))
                .andExpect(status().isOk());

    }

    @Test
    public void bookingSaveTest() throws Exception {

        LocalDate checkIn = LocalDate.parse("2024-01-10");
        LocalDate checkout = LocalDate.parse("2024-01-20");

        BookingModel bookingModel = new BookingModel("1", "Joao", checkIn, checkout, 2);


        mockMvc.perform(MockMvcRequestBuilders.post("/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookingModel)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void bookingUpdateTest() throws Exception {

        LocalDate checkIn = LocalDate.parse("2024-01-10");
        LocalDate checkout = LocalDate.parse("2024-01-20");

        BookingModel bookingModel = new BookingModel("1", "Joao", checkIn, checkout, 3);

        mockMvc.perform(MockMvcRequestBuilders.put("/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookingModel)))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
//                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(bookingModel)));

    }

    @Test
    public void bookingDeleteTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/bookings").param("bookingId", "1"))
                .andExpect(MockMvcResultMatchers.status().isAccepted());

    }
    @BeforeEach
    public void setUp() {
        //MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup( new BookingController() ).build();
    }
}
