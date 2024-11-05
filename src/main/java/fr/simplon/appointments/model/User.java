package fr.simplon.appointments.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;


@Entity
@Data
@Builder
@Table(name="users")
public class User {

  @Id
  @GeneratedValue
  private Long id;

  private String username;

  @ManyToMany(mappedBy = "invitee")
  private List<Event> events;
}
