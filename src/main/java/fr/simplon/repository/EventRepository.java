package fr.simplon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.simplon.appointments.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
