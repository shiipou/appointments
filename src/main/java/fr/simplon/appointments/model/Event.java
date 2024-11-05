package fr.simplon.appointments.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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

  private User owner;

  private List<User> invitee;
}
