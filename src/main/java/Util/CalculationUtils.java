package Util;

import Enums.RoomType;

public class CalculationUtils {

    public static int CapaciteValueFromRoomType(RoomType roomType){
        if(roomType == RoomType.SINGLE){
            return 1;
        } else if (roomType == RoomType.DOUBLE) {
            return 2;
        } else {
            return 3;
        }
    }
}
