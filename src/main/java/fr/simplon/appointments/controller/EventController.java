package fr.simplon.appointments.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.simplon.appointments.model.Event;
import fr.simplon.appointments.repository.EventRepository;

@RestController
@RequestMapping("/events")
public class EventController {

  @Autowired
  private EventRepository eventRepository;

  /**
   * Retrieve all events stored in database.
   *
   * @return All events in database
   */
  @GetMapping
  public List<Event> getEvents() {
    return eventRepository.findAll();
  }

  /**
   * Retrieve an event stored in database with it's ID
   *
   * @param id The requested Id
   * @return An event that match the requested ID
   */
  @GetMapping("/{id}")
  public Optional<Event> getEvent(@PathVariable("id") Long id) {
    return eventRepository.findById(id);
  }

}
