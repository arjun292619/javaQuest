package streams;

import java.util.Random;

public record SpecialSeats(char rowMarker, int seatNumber, boolean isReserved) {
    public SpecialSeats(char rowMarker, int seatNumber) {
//        this(rowMarker, seatNumber, new Random().nextBoolean());
//        this(rowMarker, seatNumber, true);
        this(rowMarker, seatNumber, false);
}
}
