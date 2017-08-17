package cinema.dto.user;

import cinema.dto.Booking;

import javax.persistence.*;
import java.util.List;
//import javax.validation.constraints.NotNull;

/**
 * Created by Tory on 12.08.2017.
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(unique = true, nullable = false, precision = 11)
    private Long id;

    @Column(length = 30)
    private String login;

    @Column(length = 64)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_ANONYMOUS;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Booking> bookings;

    @Column(length = 12, name = "key_hash")
    private Integer keyhash;


    public User(){}

    public User(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(final User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.role = user.getRole();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Integer getKeyhash() {
        return keyhash;
    }

    public void setKeyhash(Integer keyhash) {
        this.keyhash = keyhash;
    }
}
