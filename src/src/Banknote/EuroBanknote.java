package Banknote;

public class EuroBanknote extends Banknote {

    private static final int[] ALLOWED_NOMINAL = {5,10,20,50,100,200};

    public EuroBanknote(int nominal) {
        super(Currency.EUR, nominal);
        if (!validateNominal(nominal)){
            throw new IllegalArgumentException("Ошибка! Недопустимое значение номинала " + nominal);
        }
    }

    private boolean validateNominal(int n){
        for (int i : ALLOWED_NOMINAL) if (i == n) return true;
        return false;
    }

    public double convertTo(Currency target) {
        return ExchangeRate.convert(nominal, Currency.EUR, target);
    }
}