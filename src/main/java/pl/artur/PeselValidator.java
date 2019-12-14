package pl.artur;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.stream.IntStream;

public class PeselValidator implements ConstraintValidator<Pesel, String> {

    @Override
    public void initialize(Pesel pesel) {
    }

    @Override
    public boolean isValid(String pesel, ConstraintValidatorContext cxt) {
        if (!pesel.matches("^\\d{11}$"))
            return false;
        int[] PESEL = pesel.chars().map(ch -> ch - '0').toArray();

        int year = getYear(PESEL);
        int month = getMonth(PESEL) % 20;
        int day = getDay(PESEL);
        if (month > 12)
            return false;
        if (day > maxDay(month, year))
            return false;
        return checkPeselSum(PESEL);
    }

    private int getYear(int[] PESEL) {
        int[] century = {1900, 2000, 2100, 2200, 1800};
        int year = 10 * PESEL[0] + PESEL[1];
        return century[getMonth(PESEL) / 20] + year;
    }

    private int getDay(int[] PESEL) {
        return 10 * PESEL[4] + PESEL[5];
    }

    private int getMonth(int[] PESEL) {
        return 10 * PESEL[2] + PESEL[3];
    }

    private int maxDay(int month, int year) {
        int[] max = {31, leapYear(year) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return max[month - 1];
    }

    private boolean leapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0);
    }

    private boolean checkPeselSum(int[] PESEL) {
        int[] wages = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        int sum = IntStream.rangeClosed(0, 9).map(i -> PESEL[i] * wages[i]).sum();
        sum = (10 - (sum % 10)) % 10;
        return (sum == PESEL[10]);
    }

    public boolean simplePeselCheck(String pesel) {
        if (!pesel.matches("^\\d{11}$"))
            return false;
        int[] wages = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        int sum = IntStream.rangeClosed(0, 9)
                           .map(i -> wages[i] * (pesel.charAt(i) - '0'))
                           .sum();
        sum = (10 - (sum % 10)) % 10;
        return (sum == pesel.charAt(10) - '0');
    }
}