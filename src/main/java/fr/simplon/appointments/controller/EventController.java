package fr.simplon.appointments.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.simplon.appointments.model.Event;
import fr.simplon.appointments.repository.EventRepository;

@RestController
@RequestMapping("/events")
public class EventController {

  @Autowired
  private EventRepository eventRepository;

  /**
   * Allow te retrieve all events stored in database.
   *
   * @return All events in database
   */
  @GetMapping("/")
  public List<Event> getEvents() {
    return eventRepository.findAll();
  }

}
