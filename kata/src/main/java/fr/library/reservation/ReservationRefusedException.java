package fr.library.reservation;

public class ReservationRefusedException extends RuntimeException {

    public ReservationRefusedException(String reason) {
        super(reason);
    }
}
