package fr.simplon.appointments.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Event {

  @Id
  @GeneratedValue
  private Long id;

  private LocalDate date;

  private String title;

  @ManyToOne
  private User owner;

  @ManyToMany
  @JoinTable(name = "events_users", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<User> invitee;
}
