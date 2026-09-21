package Service;


import Enums.UserRole;
import Exception.InvalidCredentialsException ;
import Model.User;
import Repository.impl.jdbc.JdbcUserRepository;
import Util.PasswordHasher;
import Util.SaltGeneratorUtil;
import Util.ValidationUtils;
import java.util.UUID;

public class AuthService {

    private static User currentUser ;
    private static JdbcUserRepository jdbcUserRepository = new JdbcUserRepository() ;

    public User inscreption(String email , String fullName, String phone, String password, UserRole userRole){
        ValidationUtils.EmailValidator(email);
        ValidationUtils.PasswordValidator(password) ;
        String salt = SaltGeneratorUtil.generateSalt() ;
//        ValidationUtils.ThisEmailShouldNotExist(inMemoryUserRepository.existsByEmail(email));
        User user = new User(fullName,email,phone,PasswordHasher.HashPassword(password,salt),userRole,UUID.randomUUID(),salt) ;
        jdbcUserRepository.save(user);
        currentUser = user ;
        return user ;
    }

    public User connexion(String email , String password)throws InvalidCredentialsException{
        ValidationUtils.EmailValidator(email);
        ValidationUtils.PasswordValidator(password);
        //ValidationUtils.ThisEmailShouldExist(inMemoryUserRepository.existsByEmail(email));
        //ValidationUtils.ThisEmailAndPassworShouldMatch(inMemoryUserRepository.isPasswordOfEmail(email,password)) ;
        currentUser = jdbcUserRepository.RetreveByCredantials(email,password) ;
        return currentUser ;
    }

    public Boolean deconnexion(){
        currentUser = null ;
        return null ;
    }

    public static User getCurrentUser(){
        return currentUser ;
    }

//    public User editProfile(User user,String newFullName,String newPhone,String newPassword,String newEmail){
//        ValidationUtils.EmailValidator(newEmail);
//        ValidationUtils.PasswordValidator(newPassword);
//        return inMemoryUserRepository.editProfile(user,newFullName,newPhone,newPassword,newEmail) ;
//    }
//
//    public User changePassword(User user,String newPassword){
//        return inMemoryUserRepository.changePassword(user,newPassword) ;
//    }
//
//    public Optional<User> findByEmail(String email){
//        return inMemoryUserRepository.findByEmail(email) ;
//    }
//
//    public Optional<User> findByUserId(UUID id){
//        return inMemoryUserRepository.findById(id) ;
//    }
//
//
//
}
