package projectJEE.Backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    @OneToOne
    @JoinColumn(name = "discipline_id", referencedColumnName = "id")
    private discipline discipline;

    private String gender;

    public athlete(String name, String surname, String nationality, Date dateOfBirth, discipline discipline, String gender){
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
        this.discipline = discipline;
        this.gender = gender;
    }
}
