package emotionalsongs.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @author Alessandro Bugno
 * @author Daniele Maccagna
 * @author Tommaso Mariani
 */
@Entity
@SuppressWarnings("all")
public class User implements Serializable {
    @Id
    @GeneratedValue()
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String cf;
    private String password;
    private final MessageDigest digest = MessageDigest.getInstance("SHA-256");

    public User(String name, String surname, String email, String cf,String password) throws NoSuchAlgorithmException {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.cf = cf;
        this.password= digest.digest(password.getBytes(StandardCharsets.UTF_8)).toString();
    }
}
