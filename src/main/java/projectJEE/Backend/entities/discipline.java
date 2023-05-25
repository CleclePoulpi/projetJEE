package projectJEE.Backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public discipline(String name, boolean paralympic) {
        this.name = name;
        this.paralympic = paralympic;
    }

    public discipline(Long id) {
        this.id = id;
    }

}
