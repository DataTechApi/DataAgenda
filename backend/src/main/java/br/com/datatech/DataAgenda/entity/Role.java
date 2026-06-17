package br.com.datatech.DataAgenda.entity;

public enum Role {
    TECNICO("ROLE_TECNICO"),
    ADMIN("ROLE_ADMIN");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
