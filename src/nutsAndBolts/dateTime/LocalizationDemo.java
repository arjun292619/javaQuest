package nutsAndBolts.dateTime;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class LocalizationDemo {
    public static void main(String[] args) {
        System.out.println("-".repeat(50));
        Locale.setDefault(Locale.UK);
        System.out.println("Default Locale; " + Locale.getDefault());

//        creating locales
        Locale en = new Locale("en ");
        Locale enAU = new Locale("en", "AU");
        Locale enCA = new Locale("en", "CA");

        Locale enIn = new Locale.Builder().setLanguage("en").setRegion("IN").build();
        Locale enNZ = new Locale.Builder().setLanguage("en").setRegion("NZ").build();

        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        System.out.println("-".repeat(50));
        for (Locale l : List.of(Locale.getDefault(), en, enAU, enCA, enIn, enCA, Locale.US)) {
            System.out.println(l.getDisplayName() + "= "
                    + LocalDateTime.now().format(dtf.withLocale(l)));
        }
        System.out.println("-".repeat(50));
//        non- english locales
        DateTimeFormatter wDayMonth = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");
        LocalDate may5 = LocalDate.of(2020, 5, 5);
        System.out.println("-".repeat(50));
        for (Locale l : List.of(Locale.CANADA, Locale.CANADA_FRENCH,
                Locale.FRANCE, Locale.GERMANY, Locale.TAIWAN, Locale.JAPAN, Locale.ITALY)) {
            System.out.println(l.getDisplayName() + " : " +
                    l.getDisplayName(l) + "=\n\t" +
                    may5.format(wDayMonth.withLocale(l)));

//            System.out.printf(l, "\t %1$tA, %1$tB %1$te, %1$tY %n", may5);
//            System.out.printf(String.format(l, "\t %1$tA, %1$tB %1$te, %1$tY %n", may5));
            NumberFormat decimalInfo = NumberFormat.getNumberInstance(l);
            decimalInfo.setMaximumFractionDigits(6);
//            System.out.println("#".repeat(30));
            System.out.println(decimalInfo.format(1234562.878265644));
            NumberFormat currency = NumberFormat.getCurrencyInstance(l);
            Currency localCurrency = Currency.getInstance(l);
            System.out.println(currency.format(555.756) + " [" +
                    localCurrency.getCurrencyCode() + "] " +
                    localCurrency.getDisplayName(l) + "/" + localCurrency.getDisplayName());
            System.out.println("-".repeat(50));
        }
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.ITALY);
        System.out.print("Enter the loan amount: ");
        BigDecimal myLoan = scanner.nextBigDecimal();
        NumberFormat decimalInfo = NumberFormat.getNumberInstance(Locale.ITALY);
        System.out.println("My Loan: " + decimalInfo.format(myLoan));
        System.out.println("-".repeat(50));
    }
}
