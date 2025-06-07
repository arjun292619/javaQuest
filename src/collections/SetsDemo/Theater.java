package collections.SetsDemo;

import java.util.NavigableSet;
import java.util.TreeSet;

public class Theater {
    class Seat implements Comparable<Seat> {
        private String seatNum;
        private boolean isReserved;

        public Seat(char rowChar, int seatNo) {
            this.seatNum = "%c%03d".formatted(rowChar, seatNo);
        }

        @Override
        public String toString() {
            return this.seatNum;
        }

        @Override
        public int compareTo(Seat o) {
            return seatNum.compareTo(o.seatNum);
        }
    }

    private String theaterName;
    private int seatsPerRow;
    private NavigableSet<Seat> seats;

    public Theater(String theaterName, int rows, int totalSeats) {
        this.theaterName = theaterName;
        this.seatsPerRow = totalSeats / rows;
        seats = new TreeSet<>();
        for (int i = 0; i < totalSeats; i++) {
            char rowChar = (char) ((int) 'A' + (i / seatsPerRow));
            int seatNum = (i % seatsPerRow) + 1;
            seats.add(new Seat(rowChar, seatNum));
        }
    }

    public void printSeatMap() {
        String lineSeparator = "-".repeat(90);
        System.out.printf("%1$s%n%2$s Seat Map%n%1$s%n", lineSeparator, theaterName);
        int index = 0;
        for (Seat s : seats) {
            System.out.printf("%-8s%s",
                    s.seatNum + ((s.isReserved ? "(\u25CF)" : "")),
                    ((index++ + 1) % seatsPerRow == 0 ? "\n" : ""));
        }
        System.out.println(lineSeparator);
    }

    public String reserveSeat(char row, int seat) {
        Seat requestedSeat = new Seat(row, seat);
        Seat foundSeat = seats.floor(requestedSeat);
        if (foundSeat == null || !foundSeat.seatNum.equals(requestedSeat.seatNum)) {
            System.out.print("--> No such Seat: " + requestedSeat);
            System.out.printf(": Seat must be between %s and %s %n",
                    seats.first().seatNum, seats.last().seatNum);
        } else {
            if (!foundSeat.isReserved) {
                foundSeat.isReserved = true;
                return foundSeat.seatNum;
            } else {
                System.out.println("Seat Requested is already reserved. Please pick another Seat.");
            }
        }
        return null;
    }
}
