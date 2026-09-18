package Model;

import java.util.UUID;

public class User {

    UUID id ;
    String fullName ;
    String email ;
    String phone ;
    String password ;

    public User(String fullName , String email , String phone ,  String password) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.fullName = fullName;
        this.phone = phone;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        StringBuilder userNameAndEmail = new StringBuilder();
        userNameAndEmail.append("[user] fullName : "+fullName+", email : "+email+" , id : "+id) ;
        return userNameAndEmail.toString();
    }
}
