package Util;
import Exception.InvalidCredentialsException ;
import Exception.EmailAlreadyExistsException ;
import Exception.InvalidReservationException ;
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
    public static void ThisEmailShouldExist(Boolean isExist){
        if(!isExist){
            throw new InvalidCredentialsException(" The email is not exist ");
        }
    }
    public static void ThisEmailAndPassworShouldMatch(Boolean isExist){
        if(!isExist){
            throw new InvalidCredentialsException(" The email or password is false ");
        }
    }
    public static void TheCapaciteOfRoomIsPossible(int numberOfGuests , Room room){
        if(numberOfGuests > room.getCapacite()){
            throw new InvalidReservationException(" Le numero des guests est plus que la capacite de chambre ");
        }
    }



}
