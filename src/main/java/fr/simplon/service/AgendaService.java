package fr.simplon.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.simplon.appointments.model.Event;
import fr.simplon.appointments.model.User;
import fr.simplon.repository.EventRepository;
import fr.simplon.repository.UserRepository;

@Service
public class AgendaService {

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private UserRepository userRepository;

  public Event createEvent(long idUser, LocalDate date, String title) {
    return this.createEvent(idUser, date, title, new ArrayList<User>());
  }

  public Event createEvent(long idUser, LocalDate date, String title, List<User> invitee)
      throws NoSuchElementException {
    User user = userRepository.findById(idUser).orElseThrow();

    Event event = Event.builder()
        .owner(user)
        .date(date)
        .title(title)
        .invitee(invitee)
        .build();

    return eventRepository.save(event);
  }

}
