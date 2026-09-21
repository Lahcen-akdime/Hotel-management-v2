//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


import Enums.UserRole;
import Service.AuthService;
import Util.InputUtils;
import Exception.InvalidCredentialsException ;

import java.time.LocalDate;

public class Main {



    // ================ Hotel Menu =================



    // ============== For User ===============

    //public static void updatePasswordForm(User user){
    //    System.out.println("==== Change password Form ====");
    //    String newPassword = inputUtils.lireString("neauvau mot de pass ") ;
    //    authService.changePassword(user,newPassword) ;
    //    System.out.println("Your password is updated seccussfuly");
    //}

    //public static void updateProfileForm(User user){
    //    System.out.println("=== Update Profile Form ===");
    //    String newFullName = inputUtils.lireString("neauvau full name ");
    //    String newEmail = inputUtils.lireString("neauvau email ");
    //    String newPassword = inputUtils.lireString("neauvau mot de pass ");
    //    String newPhone = inputUtils.lireString("neauvau phone number ");
    //    authService.editProfile(user,newFullName,newPhone,newPassword,newEmail) ;
    //    System.out.println("User updated seccessfuly");
    //}

    // ============== For reservations =============

    //public static void createReservation(){
    //    System.out.println("====== Cree ton reservation ici ======\n");
    //    System.out.println("Le nombre de chambre ?");
    //    Integer roomNumber = inputUtils.lireInt();
    //    String checkin = inputUtils.lireString("date d'entree en ce format : 2026-09-09") ;
    //    String checkout = inputUtils.lireString("date de sortie en ce format : 2026-09-09") ;
    //    System.out.println("Combien de guest ?");
    //    int numberOfGuests = inputUtils.lireInt();
    //    reservationService.createReservation(roomNumber,checkin,checkout,numberOfGuests);
    //}
//
    //public static void updateReservation(){
    //    System.out.println("Shoose reservation number that you want to update it");
    //    reservationService.getMyReservations();
    //    String reservationNumber = inputUtils.lireString(" code de chambre");
    //    reservationService.getReservationByNumber(reservationNumber);
    //}
//
    //public static void cancelReservation(){
    //    System.out.println("Copy and paste the reservation code that you want to cancel it");
    //    reservationService.getMyReservations();
    //    String code = inputUtils.lireString("reservation code");
    //    reservationService.cancelReservation(code);
    //}
//
    // =============== Principal main ================
//
    //public void main(){
//
    //    mainMenu();
    //}
}
