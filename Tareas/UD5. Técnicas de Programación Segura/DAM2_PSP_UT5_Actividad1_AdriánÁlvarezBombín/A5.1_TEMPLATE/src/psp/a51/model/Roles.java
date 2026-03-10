package psp.a51.model;

public enum Roles {
    USER("User"),
    ADMIN("Admin");

    private final String role;

    Roles(String role) {
        this.role= role;
    }

    // Devuelve la String asociada a la categoría
    public String getRole() {
        return role;
    }

    // Recupera un rol a partir del texto
    public static Roles getRole(String texto) {
        for (Roles rol : values()) {
            if (rol.getRole().equalsIgnoreCase(texto)) {
                return rol;
            }
        }
        return null; // o lanza una excepción si prefieres
    }
}
