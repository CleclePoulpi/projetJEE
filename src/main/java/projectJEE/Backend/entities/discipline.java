package projectJEE.Backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

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

    /**
     * Constructor of the class discipline.
     * @param id id of the discipline
     * @param name name of the discipline
     * @param paralympic boolean if the discipline is paralympic
     */
    public discipline(long id, String name, boolean paralympic) {
        this.id = id;
        this.name = name;
        this.paralympic = paralympic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        discipline that = (discipline) o;
        return paralympic == that.paralympic && Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, paralympic);
    }

    /**
     * This method returns the name of the discipline.
     * @return name of the discipline
     */
    @Override
    public String toString() {
        return "discipline{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", paralympic=" + paralympic +
                '}';
    }

    public Long getId() {
        return id;
    }
}
