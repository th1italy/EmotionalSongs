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

    private Song(){};

    public Song(String title, Long duration, String type, int year, String author) {
        this.title = title;
        this.duration = duration;
        this.type = type;
        this.year = year;
        this.author = author;
    }

    //getters
    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public Long getDuration() {
        return duration;
    }
    public String getType() {
        return type;
    }
    public int getYear() {
        return year;
    }
    public String getAuthor() {
        return author;
    }
}
