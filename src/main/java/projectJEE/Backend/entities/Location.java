package projectJEE.Backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Site")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String location_name;
    private String location_city;
    private String location_category;

    public Location(String name, String city, String category) {
        this.location_name = name;
        this.location_city = city;
        this.location_category = category;
    }

    public Location(Long id) {
        this.id = id;
    }



}
