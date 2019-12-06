package artur.database;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

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
        int month = getMonth(PESEL);
        int year = 10 * PESEL[0] + PESEL[1];
        if (month < 13) {
            year += 1900;
        } else if (month > 20 && month < 33) {
            year += 2000;
        } else if (month > 40 && month < 53) {
            year += 2100;
        } else if (month > 60 && month < 73) {
            year += 2200;
        } else if (month > 80 && month < 93) {
            year += 1800;
        }
        return year;
    }

    private int getDay(int[] PESEL) {
        return 10 * PESEL[4] + PESEL[5];
    }

    private int getMonth(int[] PESEL) {
        return 10 * PESEL[2] + PESEL[3];
    }

    private int maxDay(int month, int year) {
        int max = 31;
        if (month == 4 || month == 6 || month == 9 || month == 11)
            max = 30;
        if (month == 2)
            max = leapYear(year) ? 29 : 28;
        return max;
    }

    private boolean leapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0);
    }

    @SuppressWarnings("PointlessArithmeticExpression")
    private boolean checkPeselSum(int[] PESEL) {
        int sum = 1 * PESEL[0] +
                3 * PESEL[1] +
                7 * PESEL[2] +
                9 * PESEL[3] +
                1 * PESEL[4] +
                3 * PESEL[5] +
                7 * PESEL[6] +
                9 * PESEL[7] +
                1 * PESEL[8] +
                3 * PESEL[9];
        sum = (10 - (sum % 10)) % 10;

        return (sum == PESEL[10]);
    }
}