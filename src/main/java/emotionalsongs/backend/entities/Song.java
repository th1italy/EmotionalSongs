package emotionalsongs.backend.entities;

import java.io.Serializable;

/**
 * @author Alessandro Bugno
 * @author Daniele Maccagna
 * @author Tommaso Mariani
 */
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
@Entity
@SuppressWarnings("all")
public class Song implements Serializable {


    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private Long duration;
    private String type;
    private int year;
    private String author;

    public Song(){
        
    }



}
