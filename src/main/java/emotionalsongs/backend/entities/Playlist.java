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
public class Playlist implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
