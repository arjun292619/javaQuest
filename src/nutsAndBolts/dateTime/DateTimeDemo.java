package nutsAndBolts.dateTime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class DateTimeDemo {
    public static void main(String[] args) {
        System.out.println("-".repeat(50));
        LocalDate today = LocalDate.now();
        System.out.println(today);
        System.out.println("-".repeat(50));

//        create date 5th may 2022
        LocalDate five5 = LocalDate.of(2022, 5, 5);
        System.out.println(five5);
        System.out.println("-".repeat(50));
        LocalDate may5th = LocalDate.of(2022, Month.MAY, 5);
        System.out.println(may5th);
        System.out.println("-".repeat(50));
        LocalDate day125 = LocalDate.ofYearDay(2022, 125);
        System.out.println(day125);
        System.out.println("-".repeat(50));
        LocalDate may5 = LocalDate.parse("2023-05-05");
        System.out.println(may5);
        System.out.println("-".repeat(50));
//        printing months
        System.out.println(may5.getYear());
        System.out.println(may5.getMonth());
        System.out.println(may5.getMonthValue());
        System.out.println(may5.getDayOfMonth());
        System.out.println("-".repeat(50));
//        printing days
        System.out.println(may5.getDayOfMonth());
        System.out.println(may5.getDayOfWeek());
        System.out.println(may5.getDayOfYear());
        System.out.println("-".repeat(50));
//        Using Chronofield
        System.out.println(may5th.get(ChronoField.DAY_OF_YEAR));
        System.out.println(may5th.get(ChronoField.DAY_OF_MONTH));
        System.out.println(may5th.get(ChronoField.MONTH_OF_YEAR));
        System.out.println(may5th.get(ChronoField.YEAR));
        System.out.println("-".repeat(50));
//using with methods to reset
        System.out.println(may5.withYear(2000));
        System.out.println(may5.withMonth(3));
        System.out.println(may5.withDayOfMonth(4));
        System.out.println(may5);
        System.out.println(may5.with(ChronoField.DAY_OF_YEAR, 127));
//        using plus and minus to adjust date
        System.out.println("-".repeat(50));
        System.out.println(may5.plusYears(2));
        System.out.println(may5.plusMonths(12));
        System.out.println(may5.plusWeeks(52));
        System.out.println(may5.plus(365, ChronoUnit.DAYS));
        System.out.println("-".repeat(50));
//        Date comparisions
        System.out.println("May5 > today: " + may5.isAfter(today));
        System.out.println("May5 < today: " + may5.isBefore(today));
        System.out.println("May5 > today: " + may5.compareTo(today));
        System.out.println("today > May5: " + today.compareTo(may5));
        System.out.println("today > May5: " + today.compareTo(LocalDate.now()));
        System.out.println("today > May5: " + today.equals(LocalDate.now()));
        System.out.println("Is this a Leap Year: " + today.isLeapYear());
        System.out.println("Is 2024 a Leap Year: " + today.minusYears(1).isLeapYear());
        System.out.println("-".repeat(50));
//        STREAM OF LOCAL DATES
        may5.datesUntil(may5.plusDays(7))
                .forEach((date) -> System.out.print(date + " | "));
        System.out.println();
        System.out.println("-".repeat(50));
        may5.datesUntil(may5.plusYears(1), Period.ofDays(7))
                .forEach((date) -> System.out.print(date + " | "));
        System.out.println();
        System.out.println("-".repeat(50));
        LocalTime timeNow = LocalTime.now();
        System.out.println(timeNow);
        System.out.println("-".repeat(50));
        LocalTime sevenAM = LocalTime.of(7, 0);
        System.out.println(sevenAM);
        System.out.println("-".repeat(50));
        LocalTime sevenThirty = LocalTime.of(7, 30, 15);
        System.out.println(sevenThirty);
        System.out.println("-".repeat(50));
        LocalTime seven7AM = LocalTime.parse("07:00");
        LocalTime sevenThirtyPM = LocalTime.parse("19:30:25.1500");
        System.out.println(sevenAM);
        System.out.println(sevenThirtyPM);
        System.out.println(seven7AM.get(ChronoField.AMPM_OF_DAY));
        System.out.println(sevenThirtyPM.get(ChronoField.AMPM_OF_DAY));
        System.out.println("-".repeat(50));
//        GET THE METADATA FROM TIME VARIABLE
        System.out.println(sevenThirtyPM.getHour());
        System.out.println(sevenThirtyPM.get(ChronoField.CLOCK_HOUR_OF_DAY));
//        System.out.println(sevenThirtyPM.get(ChronoField.YEAR));
//        Add time with plus methods
//        System.out.println(sevenThirtyPM.plus(1, ChronoUnit.DAYS));
        System.out.println(sevenThirtyPM.plus(1, ChronoUnit.HOURS));
        System.out.println(sevenThirtyPM.range(ChronoField.HOUR_OF_DAY));
        System.out.println(sevenThirtyPM.range(ChronoField.MINUTE_OF_HOUR));
        System.out.println(sevenThirtyPM.range(ChronoField.SECOND_OF_MINUTE));
        System.out.println(sevenThirtyPM.range(ChronoField.SECOND_OF_DAY));
        System.out.println("-".repeat(50));
//        LOCALDATETIME FIELD DECLARATIONS AND FORMATTING
        LocalDateTime todayAndNow = LocalDateTime.now();
        System.out.println(todayAndNow);
        LocalDateTime mar26Noon = LocalDateTime.of(2022, 03, 26, 16, 45, 36, 564642365);
//        System.out.println(mar26Noon);
        System.out.printf("%tD %tr %n", mar26Noon, mar26Noon);
        System.out.printf("%1$tF %1$tT %n", mar26Noon);
        System.out.println("-".repeat(50));
//        DATE TIME FORMATTING AND PRINTING
        System.out.printf(todayAndNow.format(DateTimeFormatter.ISO_WEEK_DATE) + "\n");
        System.out.printf(todayAndNow.format(DateTimeFormatter.BASIC_ISO_DATE) + "\n");
        var dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        System.out.printf(todayAndNow.format(dtf) + "\n");
        System.out.printf(todayAndNow.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)) + "\n");
        System.out.println("-".repeat(50));
//        PLUS on LocalDateTime
        LocalDateTime mar27Noon = mar26Noon.plusHours(24);
        System.out.printf(mar27Noon.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)) + "\n");
    }
}
