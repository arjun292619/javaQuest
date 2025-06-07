package streams;

import java.util.Comparator;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsIntermediate {
    public static void main(String[] args) {
        var letterStream = IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1);
        System.out.println("-".repeat(36));


//        letterStream.forEach((letter) -> System.out.printf("%c ", letter));
        letterStream
                .filter(Character::isAlphabetic)
//                .dropWhile(ch -> ch <= 'm')
//                .dropWhile(ch -> Character.toUpperCase(ch) <= 'E')
//                .takeWhile(ch -> ch < 'm')
//                .filter(ch -> Character.toUpperCase(ch) < 'E')
//                .skip(5)
                .map(Character::toUpperCase)
                .distinct()
                .forEach((letter) -> System.out.printf("%c ", letter));
        System.out.println();
        System.out.println("-".repeat(36));

        Random random = new Random();

        Stream.generate(() -> random.nextInt('A', 'Z' + 1))
                .limit(50)
                .distinct()
                .sorted()
                .forEach((letter) -> System.out.printf("%c ", letter));

        System.out.println();
        System.out.println("-".repeat(36));

        int maxSeats = 100;
        int seatsInRow = 10;

        var seatStream = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                .map(n -> new Seat((char) ('A' + (n / seatsInRow)), n % seatsInRow + 1))
                .skip(5)
                .limit(15)
                .peek(s -> System.out.println("-->" + s))
                .sorted(Comparator.comparing(Seat::price)
                        .thenComparing(Seat::seatNumber));
//                .mapToDouble(Seat::price)
//                .boxed()
//                .map("%.2f"::formatted);
//                .mapToObj("%.2f"::formatted);
//                .map(Seat::toString);
//                .map(Seat::price);

        seatStream.forEach(System.out::println);

    }
}
