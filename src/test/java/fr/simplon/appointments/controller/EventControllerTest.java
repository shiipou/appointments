package fr.simplon.appointments.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import fr.simplon.appointments.model.Event;
import fr.simplon.appointments.model.User;
import fr.simplon.appointments.repository.EventRepository;

@WebMvcTest(EventController.class)
@ExtendWith(MockitoExtension.class)
public class EventControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private EventRepository eventRepository;

    @Test
    public void getEventByIdWhenExists() throws Exception {

        User user = User.builder()
                .id(1L)
                .username("shiipou")
                .build();

        LocalDate date = LocalDate.parse("2024-11-04");

        String title = "Mon event";

        User invitee1 = User.builder()
                .id(2L)
                .username("katyanna")
                .build();
        User invitee2 = User.builder()
                .id(3L)
                .username("Jack")
                .build();

        Event eventFromDB = Event.builder()
                .id(1L)
                .owner(user)
                .title(title)
                .date(date)
                .invitee(List.of(invitee1, invitee2))
                .build();

        when(eventRepository.findById(1L)).thenReturn(Optional.of(eventFromDB));

        // Appel au controleur
        mvc.perform(
                get("/events/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.owner.username").value(user.getUsername()))
                .andExpect(jsonPath("$.date").value(date.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"))))
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.invitee[0].username").value(invitee1.getUsername()))
                .andExpect(jsonPath("$.invitee[1].username").value(invitee2.getUsername()));
    }

}
