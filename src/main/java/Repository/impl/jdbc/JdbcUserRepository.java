package Repository.impl.jdbc;
import Enums.UserRole;
import Model.User;
import Util.PasswordHasher;
import Util.ValidationUtils;
import db.DatabaseConnection;
import main.java.Repository.UserRepository ;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import Exception.DatabaseException ;
import Exception.InvalidCredentialsException ;

public class JdbcUserRepository implements UserRepository {
    static Connection connection = DatabaseConnection.getInstance().getConnection() ;
    static String saveQuery = "INSERT INTO users (id,full_name,email,phone,password_hash,user_role,salt) VALUES (?, ?, ?, ?, ?, ?, ?);" ;
    static String findByEmailQuery = "SELECT id,full_name,email,phone,password_hash,user_role,salt FROM users WHERE email = ?" ;
    @Override
    public void save(User user) {
        try {

        PreparedStatement statement = connection.prepareStatement(saveQuery) ;
            statement.setObject(1,user.getId());
            statement.setString(2,user.getFullName());
            statement.setString(3,user.getEmail());
            statement.setString(4,user.getPhone());
            statement.setString(5,user.getPassword());
            statement.setString(6,user.getUserRole().toString());
            statement.setString(7,user.getSalt());
            statement.execute() ;

        } catch (SQLException e) {
            System.out.println("From register : "+e.getMessage());
        }
    }

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public User RetreveByCredantials(String email,String password) {
        try {
            PreparedStatement statement = connection.prepareStatement(findByEmailQuery);
            statement.setString(1,email);
            ResultSet result = statement.executeQuery() ;
            if(result.next()){
                UUID id = result.getObject("id",UUID.class) ;
                String full_name = result.getString("full_name") ;
                String phone = result.getString("phone") ;
                String user_role = result.getString("user_role") ;
                String salt = result.getString("salt") ;
                String passwordHash = result.getString("password_hash") ;
                if(PasswordHasher.HashPassword(password,salt).equals(passwordHash)){
                    return new User(full_name,email,phone,passwordHash, UserRole.valueOf(user_role),id,salt);
                }
                else {
                    throw new InvalidCredentialsException("The password not match") ;
                }
            }else{
                throw new DatabaseException("L'email n'est pas existe") ;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (DatabaseException e) {
            System.out.println(e.getMessage());
        }
        return null ;
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public User editProfile(User user, String newFullName, String newPhone, String newPassword, String newEmail) {
        return null;
    }

    @Override
    public User changePassword(User user, String newPassword) {
        return null;
    }
}
