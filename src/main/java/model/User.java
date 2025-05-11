package main.java.model;


public class User {

    private int id;
    private String username;
    private String password;
    private String fullName;
    private String phoneNumber;
    private UserRole role;


    public User() {
    }
    public User( String username, String password, String fullName, String phoneNumber, UserRole role) {

        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public String getFullName() {return fullName;}
    public void setFullName(String fullName) {this.fullName = fullName;}

    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public UserRole getRole() {return role;}
    public void setRole(UserRole role) {this.role = role;}
}
