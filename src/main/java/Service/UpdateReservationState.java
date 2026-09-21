//package main.java.Service;
//
//
//import Service.ReservationService;
//
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//public class UpdateReservationState {
//private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//private ReservationService reservationService ;
//
//public void UpdateReservationState(){
//    executor.scheduleWithFixedDelay(
//            () -> reservationService.updateRoomStates() ,
//            0,
//            1,
//            TimeUnit.DAYS
//    );
//}
//}
