package streams;

import java.util.Arrays;
import java.util.stream.IntStream;

public class StreamTerminal {
    public static void main(String[] args) {
        System.out.println("-".repeat(36));

        var result = IntStream
                .iterate(0, i -> i <= 1000, i -> i + 3)
                .summaryStatistics();

        System.out.println("Result= " + result);

        var leapYearData = IntStream
                .iterate(2000, i -> i <= 2040, i -> i + 1)
                .filter(n -> n % 4 == 0)
                .peek(System.out::println)
                .summaryStatistics();
        System.out.println("leap year data= " + leapYearData);

        System.out.println("-".repeat(36));

        SpecialSeats[] specialSeats = new SpecialSeats[100];
        Arrays.setAll(specialSeats, i -> new SpecialSeats((char) ('A' + i / 10), i % 10 + 1));
//        Arrays.asList(specialSeats).forEach(System.out::println);

        long reservationCount = Arrays.stream(specialSeats)
                .filter(SpecialSeats::isReserved)
                .count();
        System.out.println("Reserved Seats count: " + reservationCount);

        boolean hasBookings = Arrays.stream(specialSeats)
                .anyMatch(SpecialSeats::isReserved);
        System.out.println("has Bookings: " + hasBookings);

        boolean fullyBooked = Arrays.stream(specialSeats)
                .allMatch(SpecialSeats::isReserved);
        System.out.println("fully Booked: " + fullyBooked);

        boolean eventWashedOut = Arrays.stream(specialSeats)
                .noneMatch(SpecialSeats::isReserved);
        System.out.println("eventWashedOut: " + eventWashedOut);


    }
}
