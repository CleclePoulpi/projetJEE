package projectJEE.Backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * This class represents an athlete.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "athlete")
public class athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String surname;

    private String nationality;

    private Date dateOfBirth;

    /**
     * This attribute represents the discipline of the athlete and is a foreign key.
     */
    @OneToOne
    @JoinColumn(name = "discipline_id", referencedColumnName = "id")
    private discipline discipline;

    private String gender;

    /**
     * Constructor of the class athlete.
     * @param name name of the athlete
     * @param surname surname of the athlete
     * @param nationality nationality of the athlete
     * @param dateOfBirth date of birth of the athlete
     * @param discipline discipline of the athlete
     * @param gender gender of the athlete
     */
    public athlete(String name, String surname, String nationality, Date dateOfBirth, discipline discipline, String gender){
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
        this.discipline = discipline;
        this.gender = gender;
    }

    /**
     * This method sets the name of the athlete.
     */
    public void setName(String x) {
        this.name = x;
    }

    /**
     * This methods sets the gender of the athletes.
     * @param x gender of the athlete
     */
    public void setGender(String x) {
        this.gender = x;
    }
}
