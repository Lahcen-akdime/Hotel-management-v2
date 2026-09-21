package Initializer;

import Enums.UserRole;
import Model.User;
import Service.AuthService;
import Util.SaltGeneratorUtil;

import java.util.UUID;

public class AdminInitializer {
    String email = "mr@gmail.com" ;
    String password = "mr@gmail.com" ;
    AuthService authService = new AuthService() ;
    {
        if(authService.connexion(email,password) == null){
            authService.inscreption(email,
                    "moul chi",
                    "0444444444",
                    password,
                    UserRole.admin
            );
        }
    }
}
