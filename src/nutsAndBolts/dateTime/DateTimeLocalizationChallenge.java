package nutsAndBolts.dateTime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.zone.ZoneRules;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DateTimeLocalizationChallenge {
    private record Employee(String name, Locale locale, ZoneId zone) {
        public Employee(String name, String locale, String zone) {
            this(name, Locale.forLanguageTag(locale), ZoneId.of(zone));
        }

        public Employee(String name, Locale locale, String zone) {
            this(name, locale, ZoneId.of(zone));
        }

        String getDateInfo(ZonedDateTime zdt, DateTimeFormatter dtf) {
            return "%s [%s] : %s".formatted(name, zone, zdt.format(dtf.localizedBy(locale)));
        }
    }

    public static void main(String[] args) {
        System.out.println("-".repeat(50));

        Employee jane = new Employee("Jane", Locale.US, "America/New_York");
        Employee joe = new Employee("Joe", "en-AU", "Australia/Sydney");

        ZoneRules joeRules = joe.zone().getRules();
        ZoneRules janeRules = jane.zone().getRules();
        System.out.println(jane + " " + janeRules);
        System.out.println(joe + " " + joeRules);
        System.out.println("-".repeat(50));

        ZonedDateTime janeNow = ZonedDateTime.now(jane.zone());
        ZonedDateTime joeNow = ZonedDateTime.of(janeNow.toLocalDateTime(), joe.zone);
        long hoursBetween = Duration.between(joeNow, janeNow).toHours();
        long minBetween = Duration.between(joeNow, janeNow).toMinutesPart();
        System.out.println("Joe is " + Math.abs(hoursBetween) + " hours " +
                Math.abs(minBetween) + " minutes " +
                ((hoursBetween < 0) ? "behind" : "ahead"));
        System.out.println("-".repeat(50));
        System.out.println("Joe in daylight savings? " +
                joeRules.isDaylightSavings(joeNow.toInstant()) + " " +
                joeRules.getDaylightSavings(joeNow.toInstant()) + " : " +
                joeNow.format(DateTimeFormatter.ofPattern("zzzz z")));

        System.out.println("Jane in daylight savings? " +
                janeRules.isDaylightSavings(janeNow.toInstant()) + " " +
                janeRules.getDaylightSavings(janeNow.toInstant()) + " : " +
                janeNow.format(DateTimeFormatter.ofPattern("zzzz z")));
        System.out.println("-".repeat(50));
        int days = 10;
        var overlap = schedule(joe, jane, days);
        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.SHORT);

        for (LocalDate ld : overlap.keySet()) {
            System.out.println(ld.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
            for (ZonedDateTime zdt : overlap.get(ld)) {
                System.out.println("\t" +
                        jane.getDateInfo(zdt, dtf) +
                        " <----> " +
                        joe.getDateInfo(zdt.withZoneSameInstant(joe.zone()), dtf));
            }
        }

    }

    private static Map<LocalDate, List<ZonedDateTime>> schedule(Employee one, Employee two, int days) {
        Predicate<ZonedDateTime> zdtPredicate = zdt -> zdt.getDayOfWeek() != DayOfWeek.SATURDAY
                && zdt.getDayOfWeek() != DayOfWeek.SUNDAY && zdt.getHour() >= 7 && zdt.getHour() < 21;

        LocalDate startingDate = LocalDate.now().plusDays(2);

        return startingDate.datesUntil(startingDate.plusDays(days + 1))
                .map(date -> date.atStartOfDay(one.zone))
                .flatMap(dt -> IntStream.range(0, 24).mapToObj(dt::withHour))
                .filter(zdtPredicate)
                .map(zonedDateTime -> zonedDateTime.withZoneSameInstant(two.zone))
                .filter(zdtPredicate)
                .collect(Collectors.groupingBy(ZonedDateTime::toLocalDate, TreeMap::new, Collectors.toList()));
    }
}
