package emotionalsongs.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;
/**
 * @author Alessandro Bugno
 * @author Daniele Maccagna
 * @author Tommaso Mariani
 */
@Entity
@SuppressWarnings("all")
public class User implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String cf;






}
