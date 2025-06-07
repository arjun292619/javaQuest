package collections;

import collections.SetsDemo.Theater;

public class BookingAgent {
    public static void main(String[] args) {
        int rows = 10;
        int totalSeats = 100;
        Theater kellerAuditorium = new Theater("Keller Auditorium", rows, totalSeats);
        kellerAuditorium.printSeatMap();
        bookSeat(kellerAuditorium, 'A', 3);
        bookSeat(kellerAuditorium, 'A', 3);

        bookSeat(kellerAuditorium, 'B', 1);
        bookSeat(kellerAuditorium, 'B', 11);

        bookSeat(kellerAuditorium, 'M', 11);
    }

    private static void bookSeat(Theater theater, char row, int seat) {
        String reserved = theater.reserveSeat(row, seat);
        if (reserved != null) {
            System.out.println("Your reserved seat is " + reserved);
            theater.printSeatMap();
        } else {
            System.out.println("Seat Requested is already reserved or unavailable. Please pick another Seat.");
        }
    }
}
