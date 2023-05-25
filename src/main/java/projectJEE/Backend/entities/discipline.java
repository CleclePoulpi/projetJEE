package projectJEE.Backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a discipline.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "discipline")
public class discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private boolean paralympic;

    /**
     * Constructor of the class discipline.
     * @param name  name of the discipline
     * @param paralympic boolean if the discipline is paralympic
     */
    public discipline(String name, boolean paralympic) {
        this.name = name;
        this.paralympic = paralympic;
    }

    /**
     * Constructor of the class discipline.
     * @param id id of the discipline
     */
    public discipline(Long id) {
        this.id = id;
    }

}
