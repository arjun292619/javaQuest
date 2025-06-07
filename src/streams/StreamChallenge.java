package streams;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class StreamChallenge {
    static int counter;

    public static void main(String[] args) {
        System.out.println("-".repeat(36));

        int seed = 1;
        Stream<String> streamB = Stream.iterate(seed, i -> i <= 15, i -> i + 1)
                .map(integer -> "B" + integer);

//        streamB.forEach(System.out::println);
        seed += 15;
        Stream<String> streamI = Stream.iterate(seed, i -> i + 1)
                .limit(15)
                .map(integer -> "I" + integer);
//        streamI.forEach(System.out::println);
        int nSeed = seed + 15;
        String[] nLabels = new String[15];
        Arrays.setAll(nLabels, i -> "N" + (nSeed + i));
        Stream<String> streamN = Stream.of(nLabels);
//        streamN.forEach(System.out::println);

        var streamG = Stream.of("G46", "G47", "G48", "G49", "G50", "G51",
                "G52", "G53", "G54", "G55", "G56", "G57", "G58", "G59", "G60");
//        streamG.forEach(System.out::println);

        seed += 30;
        int rSeed = seed;
        var streamO = Stream.generate(StreamChallenge::getCounter)
                .limit(15)
                .map(i -> "O" + (rSeed + i));

        var streamBI = Stream.concat(streamB, streamI);
        var streamNG = Stream.concat(streamN, streamG);

        var streamBING = Stream.concat(streamBI, streamNG);

        var streamBINGO = Stream.concat(streamBING, streamO);
        streamBINGO.forEach(System.out::println);
        System.out.println("-".repeat(36));

        Stream.generate(() -> new Random().nextInt(rSeed, rSeed + 15))
                .distinct()
                .limit(15)
                .map(i -> "T" + i)
                .sorted()
                .forEach(System.out::println);
    }

    private static int getCounter() {
        return counter++;
    }
}
