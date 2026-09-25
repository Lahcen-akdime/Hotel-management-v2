package Model;


import Enums.UserRole;
import Util.SaltGeneratorUtil;

import java.util.Random;
import java.util.UUID;

public class User {

    private UUID id ;
    private String fullName ;
    private String email ;
    private String phone ;
    private String password ;
    private UserRole userRole ;
    private String NotHashedpassword ;
    private String salt ;

    public User(String fullName , String email , String phone , String NotHashedpassword ,  String password , UserRole userRole,UUID id,String salt) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.phone = phone;
        this.password = password;
        this.userRole = userRole ;
        this.NotHashedpassword = NotHashedpassword ;
        this.salt = salt ;
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

    public UserRole getUserRole() {
        return userRole;
    }

    public String getSalt() {
        return salt;
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

    public String getNotHashedpassword() {
        return NotHashedpassword;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        StringBuilder userNameAndEmail = new StringBuilder();
        userNameAndEmail.append("[user] fullName : "+fullName+", email : "+email+" , phone : "+phone) ;
        return userNameAndEmail.toString();
    }
}
