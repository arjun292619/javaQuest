package nutsAndBolts.dateTime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class TimeZonesDemo {
    public static void main(String[] args) {
        System.out.println("-".repeat(50));
        System.setProperty("user.timezone", "US/Central");
//        System.setProperty("user.timezone", "UTC");
//        System.setProperty("user.timezone", "GMT");
        System.out.println(ZoneId.systemDefault());
        System.out.println("Number of TZs# " + ZoneId.getAvailableZoneIds().size());
        System.out.println("-".repeat(50));
        ZoneId.getAvailableZoneIds().stream()
//                .filter(tz -> tz.startsWith("America/N"))
//                .filter(tz -> tz.startsWith("US"))
                .sorted()
                .map(ZoneId::of)
                .forEach(tz -> System.out.println(tz.getId() + ": " + tz.getRules()));
        System.out.println("-".repeat(50));
        Set<String> jdk8Zones = ZoneId.getAvailableZoneIds();
        String[] alternate = TimeZone.getAvailableIDs();
//        Set<String> oldZones = new HashSet<>(Arrays.asList());
        Set<String> oldZones = new HashSet<>(Set.of(alternate));
//        jdk8Zones.removeAll(oldZones);
        oldZones.removeAll(jdk8Zones);
        System.out.println(oldZones);
//        using old time zone code on ZoneId
//        ZoneId mit = ZoneId.of("MIT");
        ZoneId mit = ZoneId.of("MIT", ZoneId.SHORT_IDS);
        System.out.println(mit);
        System.out.println("-".repeat(50));
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        System.out.println("-".repeat(50));
        Instant instantNow = Instant.now();
        System.out.println(instantNow);
    }
}
