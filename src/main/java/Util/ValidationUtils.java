package Util;
import Enums.UserRole;
import Exception.InvalidCredentialsException ;
import Exception.EmailAlreadyExistsException ;
import Exception.InvalidReservationException ;
import Exception.UnothorizedRequestException ;
import Model.Room;

import java.time.LocalDate;

public class ValidationUtils {

    public static void EmailValidator(String email){
        if (!email.contains("@gmail.com")) {
            throw new InvalidCredentialsException("The email not have @gmail.com !!") ;
        }
    }
    public static void PasswordValidator(String password){
        if (password.length() < 6 || password.isEmpty()) {
            throw new InvalidCredentialsException("The password is invalid !!") ;
        }
    }
    public static void ThisEmailShouldNotExist(Boolean isExist){
        if(isExist == true){
            throw new EmailAlreadyExistsException("The email is already exist !!") ;
        }
    }
    public static void CheckisAdmin(UserRole userRole){
        if(!userRole.equals(UserRole.admin)){
            throw new UnothorizedRequestException("You cant access this page its just for admins") ;
        }
    }
    public static void ThisEmailShouldExist(Boolean isExist){
        if(!isExist){
            throw new InvalidCredentialsException(" The email is not exist ");
        }
    }
    public static void ThisEmailAndPassworShouldMatch(String email , String password){

    }
    public static void TheCapaciteOfRoomIsPossible(int numberOfGuests , Room room){
        if(numberOfGuests > room.getCapacite()){
            throw new InvalidReservationException(" Le numero des guests est plus que la capacite de chambre ");
        }
    }



}
