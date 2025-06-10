package Banknote;

public abstract class Banknote {

    public final Currency currency;
    public final int nominal;

    protected Banknote(Currency currency, int nominal) {
        this.currency = currency;
        this.nominal = nominal;
    }

    public abstract double convertTo(Currency target);

}