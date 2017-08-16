package cinema.dto.user;

/**
 * Created by Tory on 12.08.2017.
 */
public enum Role {
    ROLE_ADMIN("ADMIN"),
    ROLE_MANAGER("MANAGER"),
    ROLE_USER("USER"),
    ROLE_ANONYMOUS("ANONIMOUS");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
