package projectJEE.Backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a user in the database
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class user {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private Long privilege;

    /**
     * Constructor
     * @param username username of the admin
     * @param password   password of the admin
     * @param privilege privilege of the admin
     */
    public user(String username, String password, Long privilege) {
        this.username = username;
        this.password = password;
        this.privilege = privilege;
    }

    /**
     * Get the privilege of the admin
     * @return privilege of the admin
     */
    public Long getPrivilege() {
        return privilege;
    }

}
