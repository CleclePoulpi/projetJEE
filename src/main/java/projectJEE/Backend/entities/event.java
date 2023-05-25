package projectJEE.Backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Random;

/**
 * This class represents an event.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event")
public class event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;

    private LocalDate date;

    private LocalTime start_time;

    private String description;

    @OneToOne
    @JoinColumn(name = "discipline_id", referencedColumnName = "id")
    private discipline discipline;

    private LocalTime end_time;

    @OneToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private type type;


    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private category category ;



    public event(LocalDate date, LocalTime start_time, String description, discipline discipline, LocalTime end_time, Location location, type type, category category, String code) {
        this.date = date;
        this.start_time = start_time;
        this.description = description;
        this.discipline = discipline;
        this.end_time = end_time;
        this.location = location;
        this.type = type;
        this.category = category;
        this.code = code;
    }
}
