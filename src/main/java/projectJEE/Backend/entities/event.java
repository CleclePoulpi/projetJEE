package projectJEE.Backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.sql.Time;

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

    private Date date;

    private Time start_time;

    private String description;

    @OneToOne
    @JoinColumn(name = "discipline_id", referencedColumnName = "id")
    private discipline discipline;

    private Time end_time;

    @OneToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private type type;


    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private category category ;

    public event(Date date, Time start_time, String description, discipline discipline, Time end_time, Location location, type type, category category) {
        this.date = date;
        this.start_time = start_time;
        this.description = description;
        this.discipline = discipline;
        this.end_time = end_time;
        this.location = location;
        this.type = type;
        this.category = category;
    }
}
