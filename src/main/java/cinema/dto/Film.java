package cinema.dto;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by Tory on 23.04.2017.
 */
@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(unique = true, nullable = false, precision = 11)
    private long id;
    private String name;
    private short year;
    private String genre;

    public Film(){
        super();
    }

    public Film(String genre, String name, short year) {
        this.genre = genre;
        this.name = name;
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public String getName() {
        return name;
    }

    public short getYear() {
        return year;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(short year) {
        this.year = year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, year, genre);
    }

}
