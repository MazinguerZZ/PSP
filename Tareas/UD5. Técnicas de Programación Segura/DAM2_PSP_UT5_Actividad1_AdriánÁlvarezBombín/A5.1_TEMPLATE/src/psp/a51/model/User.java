package psp.a51.model;

public class User {
    private final String username;
    private final Roles role;

    public User(String username, Roles role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() { return username; }
    public Roles getRole() { return role; }
}
