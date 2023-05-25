package projectJEE.Backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a login entity for the database.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "login")
public class login {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;

    /**
     * Constructor for login entity.
     * @param username username of the admin
     * @param password password of the admin
     */
    public login(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
