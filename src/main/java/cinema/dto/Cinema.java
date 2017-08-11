package cinema.dto;

import javax.persistence.*;

/**
 * Created by Victoria on 8/11/17.
 */
@Entity
@Table(name = "cinema")
public class Cinema {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(unique = true, nullable = false, precision = 11)
    private long id;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
