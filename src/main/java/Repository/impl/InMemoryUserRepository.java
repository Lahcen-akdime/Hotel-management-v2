package main.java.Repository.impl;


import main.java.Repository.UserRepository;
import main.java.Model.User;
import main.java.Repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryUserRepository implements UserRepository {

    private static Map<UUID, User> users = new HashMap<>() ;

    {
        User user = new User("lahcen","lahcen@gmail.com","lahcen","lahcenlahcen");
        users.put(user.getId(),user);
    }

    public void save(User user){
        users.put(user.getId(),user) ;
    }

    public Optional<User> findById(UUID id) {
        return Optional.of(users.get(id)) ;
    }

    public Optional<User> findByEmail(String email) {
        return users.entrySet().stream().map(user->user.getValue()).filter(user -> user.getEmail().equalsIgnoreCase(email)).findFirst();
    }

    public boolean existsByEmail(String email) {
        return users.entrySet().stream().map(user -> user.getValue()).filter(user->user.getEmail().equalsIgnoreCase(email)).findAny().isPresent();
    }

    public List<User> findAll() {
        return List.of();
    }

    public User editProfile(User user,String newFullName,String newPhone,String newPassword,String newEmail){
        User Storeduser = users.get(user.getId());
        Storeduser.setFullName(newFullName);
        Storeduser.setPhone(newPhone);
        Storeduser.setPassword(newPassword);
        Storeduser.setEmail(newEmail);
        return Storeduser ;
    }

    public User changePassword(User user,String newPassword){
        User Storeduser = users.get(user.getId());
        Storeduser.setPassword(newPassword);
        return Storeduser ;
    }

    public Boolean isPasswordOfEmail(String email , String password){
        return findByEmail(email).get().getPassword().equals(password) ;
    }

}
