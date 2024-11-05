package fr.simplon.appointments.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class User {

  @Id
  @GeneratedValue
  private Long id;

  private String username;

}
