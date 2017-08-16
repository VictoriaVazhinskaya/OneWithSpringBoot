package cinema.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
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
    private Long id;

    @Column(length = 40)
    private String name;
    private short year;

    @Column(length = 20)
    private String genre;

    public Film(){}

    public Film(String genre, String name, short year) {
        this.genre = genre;
        this.name = name;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
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
