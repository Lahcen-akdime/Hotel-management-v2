package Repository;

import Model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    void save(User user);
    Optional<User> findById(UUID id) ;
    Optional<User> findByEmail(String email) ;
    boolean existsByEmail(String email);
    List<User> findAll();
    User editProfile(User user , String newFullName,String newPhone,String newPassword,String newEmail) ;
    User changePassword(User user,String newPassword) ;

}
