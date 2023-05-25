package projectJEE.Backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a location.
 */
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

    /**
     * Constructor of the class location.
     * @param name name of the location
     * @param city city of the location
     * @param category category of the location
     */
    public Location(String name, String city, String category) {
        this.location_name = name;
        this.location_city = city;
        this.location_category = category;
    }

    /**
     * Constructor of the class location.
     * @param id id of the location
     */
    public Location(Long id) {
        this.id = id;
    }



}
