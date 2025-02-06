package it.crm.bd.model;

import it.crm.bd.other.Role;

public class Credentials {
    private  String username;
    private  String password;
    private Role role;
    public Credentials(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = (role!=null) ? role : Role.NON_RICONOSCIUTO;
    }
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public Role getRole() {return role;}
}
