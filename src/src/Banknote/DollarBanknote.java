package Banknote;

public class DollarBanknote extends Banknote {

    private static final int[] ALLOWED_NOMINAL = {1, 5, 10, 20, 50, 100};

    public DollarBanknote(int nominal) {
        super(Currency.USD, nominal);
        if (!validateNominal(nominal)){
            throw new IllegalArgumentException("Ошибка! Недопустимое значение номинала: " + nominal);
        }
    }

    private boolean validateNominal(int n){
        for (int i : ALLOWED_NOMINAL) if (i == n) return true;
        return false;
    }

    public double convertTo(Currency target) {
        return ExchangeRate.convert(nominal, Currency.USD, target);
    }

}
