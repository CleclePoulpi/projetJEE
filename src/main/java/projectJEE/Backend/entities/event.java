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

    /**
     * This attribute represents the id of the event.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * This attribute represents the code of the event.
     */
    private String code;

    /**
     * This attribute represents the date of the event.
     */
    private LocalDate date;

    /**
     * This attribute represents the start time of the event.
     */
    private LocalTime start_time;

    /**
     * This attribute represents the description of the event.
     */
    private String description;

    /**
     * This attribute represents the discipline of the event.
     */
    @OneToOne
    @JoinColumn(name = "discipline_id", referencedColumnName = "id")
    private discipline discipline;

    /**
     * This attribute represents the end time of the event.
     */
    private LocalTime end_time;

    /**
     * This attribute represents the location of the event.
     */
    @OneToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    /**
     * This attribute represents the type of the event.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private type type;

    /**
     * This attribute represents the category of the event.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private category category ;


    /**
     * Constructor of the class event.
     * @param date date of the event
     * @param start_time start time of the event
     * @param description description of the event
     * @param discipline discipline of the event
     * @param end_time end time of the event
     * @param location location of the event
     * @param type type of the event
     * @param category category of the event
     * @param code code of the event
     */
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

    public LocalTime getStarting_hour() {
        return this.start_time;
    }

    public LocalTime getEnding_hour() {
        return this.end_time;
    }
}
