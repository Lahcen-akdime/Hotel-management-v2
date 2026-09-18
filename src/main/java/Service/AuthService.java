package main.java.Service;


import Exception.InvalidCredentialsException ;
import Exception.EmailAlreadyExistsException ;
import Util.ValidationUtils;
import main.java.Model.User;
import main.java.Repository.impl.InMemoryUserRepository;

import java.util.Optional;
import java.util.UUID;

public class AuthService {

    private static User currentUser ;
    private static InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository() ;

    public User inscreption(String email , String fullName,String phone,String password){
        ValidationUtils.EmailValidator(email);
        ValidationUtils.PasswordValidator(password);
        ValidationUtils.ThisEmailShouldNotExist(inMemoryUserRepository.existsByEmail(email));
        User user = new User(fullName,email,phone,password) ;
        inMemoryUserRepository.save(user);
        currentUser = user ;
        return user ;
    }

    public Optional<User> connexion(String email , String password)throws InvalidCredentialsException{
        ValidationUtils.EmailValidator(email);
        ValidationUtils.PasswordValidator(password);
        ValidationUtils.ThisEmailShouldExist(inMemoryUserRepository.existsByEmail(email));
        ValidationUtils.ThisEmailAndPassworShouldMatch(inMemoryUserRepository.isPasswordOfEmail(email,password)) ;
            Optional<User> user = findByEmail(email) ;
            currentUser = user.get() ;
            return user ;
    }

    public Boolean deconnexion(User user){
        currentUser = null ;
        return null ;
    }

    public User editProfile(User user,String newFullName,String newPhone,String newPassword,String newEmail){
        ValidationUtils.EmailValidator(newEmail);
        ValidationUtils.PasswordValidator(newPassword);
        return inMemoryUserRepository.editProfile(user,newFullName,newPhone,newPassword,newEmail) ;
    }

    public User changePassword(User user,String newPassword){
        return inMemoryUserRepository.changePassword(user,newPassword) ;
    }

    public Optional<User> findByEmail(String email){
        return inMemoryUserRepository.findByEmail(email) ;
    }

    public Optional<User> findByUserId(UUID id){
        return inMemoryUserRepository.findById(id) ;
    }


    public static User getCurrentUser(){
        return currentUser ;
    }

}
