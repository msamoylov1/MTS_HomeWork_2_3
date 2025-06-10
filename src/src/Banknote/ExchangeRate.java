package Banknote;

public class ExchangeRate {

    public static final double USD_TO_EUR = 0.9;
    public static final double USD_TO_RUB = 75;

    public static double convert(double amount, Currency from, Currency to) {
        if (from.equals(to)) return amount;
        double result = 0;

        switch (from) {
            case USD:
                if (to.equals(Currency.EUR)) {
                    result = amount * USD_TO_EUR;
                } else if (to.equals(Currency.RUB)) {
                    result = amount * USD_TO_RUB;
                }
                break;
            case EUR:
                if (to.equals(Currency.USD)) {
                    result = amount / USD_TO_EUR;
                } else if (to.equals(Currency.RUB)) {
                    result = (amount / USD_TO_EUR) * USD_TO_RUB;
                }
                break;
            case RUB:
                if (to.equals(Currency.USD)) {
                    result = amount / USD_TO_RUB;
                } else if (to.equals(Currency.EUR)) {
                    result = (amount / USD_TO_RUB) * USD_TO_EUR;
                }
                break;
        }
        return result;
    }
}