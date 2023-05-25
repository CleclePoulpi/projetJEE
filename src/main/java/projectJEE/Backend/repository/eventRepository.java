package projectJEE.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectJEE.Backend.entities.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;


public interface eventRepository extends JpaRepository<event, Long> {
    List<event> findEventByCode(String code);

    List<event> findEventByCodeAndDiscipline(String code, discipline discipline);
}
