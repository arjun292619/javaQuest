package nutsAndBolts.dateTime;

import java.awt.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public class InstantDurationPeriod {
    public static void main(String[] args) {
        System.out.println("-".repeat(50));
        System.setProperty("user.timezone", "US/Pacific");

        Instant instantNow = Instant.now();
        System.out.println(instantNow);

        for (ZoneId z : List.of(ZoneId.of("US/Hawaii"), ZoneId.of("US/Central"),
                ZoneId.of("US/Alaska"), ZoneId.of("US/Michigan"), ZoneId.of("US/Mountain"), ZoneId.of("US/Pacific"))) {
            DateTimeFormatter zoneFormat = DateTimeFormatter.ofPattern("z:zzzz");
            System.out.println(z);
            System.out.println("\t" + instantNow.atZone(z).format(zoneFormat));
            System.out.println("\t" + z.getRules().getDaylightSavings(instantNow));
            System.out.println("\t" + z.getRules().isDaylightSavings(instantNow));
        }
        System.out.println("-".repeat(50));
//        converting instant to localeDateTime
        Instant dobInstant = Instant.parse("2022-03-26T08:01:00Z");
        LocalDateTime dob = LocalDateTime.ofInstant(dobInstant, ZoneId.systemDefault());
        System.out.println("kids birthDate, US/Pacific= " + dob.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

//        Using Zoned Date time

        ZonedDateTime dobSydney = ZonedDateTime.ofInstant(dobInstant, ZoneId.of("Australia/Sydney"));
        System.out.println("kids birth date, Sydney time= " + dobSydney.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        System.out.println("-".repeat(50));
        ZonedDateTime dobHere = dobSydney.withZoneSameInstant(ZoneId.systemDefault());
        System.out.println("kids birth date, Here time= " + dobSydney.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        System.out.println("-".repeat(50));
//        using temporal adjusters for selected dates
        ZonedDateTime firstOfMonth = ZonedDateTime.now().with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.printf("First of next month= %tD %n", firstOfMonth);
        LocalDateTime lastDayOfMonth = LocalDateTime.now().with(TemporalAdjusters.lastDayOfMonth());
        System.out.printf("First of next month= %tD %n", lastDayOfMonth);
        System.out.println("-".repeat(50));
//        periods and between method
        Period timePast = Period.between(LocalDate.EPOCH, dob.toLocalDate());
        System.out.println(timePast);
        Duration timePassed = Duration.between(Instant.EPOCH, dob.toInstant(ZoneOffset.UTC));
        System.out.println(timePassed);
        System.out.println("-".repeat(50));
//        add 2nd child based onthe 1st child
        LocalDateTime dob2 = dob.plusYears(2).plusMonths(4)
                .plusDays(15).plusHours(7)
                .plusMinutes(16)
                .plusSeconds(37);
        System.out.println("1st kids birthDate, US/Pacific= " + dob.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        System.out.printf("2nd kids birthday= " + dob2.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)) + "\n");

        Period timepast2 = Period.between(LocalDate.EPOCH, dob2.toLocalDate());
        System.out.println(timepast2);
        Duration timeSince2 = Duration.between(Instant.EPOCH, dob2.toInstant(ZoneOffset.UTC));
        System.out.println(timeSince2);
        System.out.println("-".repeat(50));
//        ChrnoUnit between
        for (ChronoUnit u : ChronoUnit.values()) {
            if (u.isSupportedBy(LocalDate.EPOCH)) {
                long val = u.between(LocalDate.EPOCH, dob2.toLocalDate());
                System.out.println(u + " past =  " + val);
            } else {
                System.out.println("-- Not supported ChronoUnit# " + u);
            }
        }
        System.out.println("-".repeat(50));
        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.EPOCH, ZoneOffset.UTC);
        for (ChronoUnit u : ChronoUnit.values()) {
            if (u.isSupportedBy(ldt)) {
                long val = u.between(ldt, dob2);
                System.out.println(u + " past =  " + val);
            } else {
                System.out.println("-- Not supported ChronoUnit# " + u);
            }
        }

    }
}
