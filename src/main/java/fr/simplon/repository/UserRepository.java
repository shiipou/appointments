package fr.simplon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.simplon.appointments.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
