package fr.simplon.appointments.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import fr.simplon.appointments.model.Event;
import fr.simplon.appointments.model.User;
import fr.simplon.repository.EventRepository;
import fr.simplon.repository.UserRepository;
import fr.simplon.service.AgendaService;

@SpringBootTest
public class AgendaServiceTest {

  @Mock
  private EventRepository eventRepository;

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private AgendaService agendaService;

  @Test
  public void createEvent() {

    // Premier paramètre
    long idUser = 1;

    User user = User.builder()
        .id(1L)
        .username("shiipou")
        .build();

    // Mock user
    when(userRepository.findById(idUser)).thenReturn(Optional.of(user));

    // Second paramêtre
    LocalDate date = LocalDate.parse("2024-11-04");

    // Troixieme parametre
    String title = "Mon event";

    // Quatrieme parametre
    User invitee1 = User.builder()
        .id(2L)
        .username("katyanna")
        .build();
    User invitee2 = User.builder()
        .id(3L)
        .username("Jack")
        .build();

    // Mock Create event
    Event eventDb = Event.builder()
        .id(1L)
        .owner(user)
        .title(title)
        .date(date)
        .invitee(List.of(invitee1, invitee2))
        .build();

    when(eventRepository.save(eventDb)).thenReturn(eventDb);

    // Execution du code de l'app
    Event event = agendaService.createEvent(idUser, date, title, List.of(invitee1, invitee2));

    assertEquals(event.getOwner().getUsername(), user.getUsername());
    assertEquals(event.getDate(), date);
    assertEquals(event.getTitle(), title);
    assertEquals(invitee1.getUsername(), event.getInvitee().get(0).getUsername());
    assertEquals(invitee2.getUsername(), event.getInvitee().get(1).getUsername());

  }

}
