package it.crm.bd.model.domain;

public class Credentials {
    private final String username;
    private final String password;
    private final Role role;
    public Credentials(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public Role getRole() {return role;}
}
