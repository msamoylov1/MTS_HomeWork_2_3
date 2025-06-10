import Banknote.Banknote;
import Banknote.Currency;

public class ATM {
    private final Banknote[] usdNotes = new Banknote[100];
    private final Banknote[] eurNotes = new Banknote[100];
    private final Banknote[] rubNotes = new Banknote[100];

    public boolean deposit(Banknote note) {
        Banknote[] arr = getArray(note.currency);
        int index = findEmptyIndex(arr);
        if (index == -1) {
            System.out.println("Кассета с " + note.currency + " заполнена.");
            return false;
        }
        arr[index] = note;
        return true;
    }

    public boolean withdraw(int amount, Currency currency) {
        Banknote[] arr = getArray(currency);
        int[] indices = new int[arr.length];
        int sum = 0;
        int count = 0;

        // Подбор купюр
        for (int i = 0; i < arr.length && sum < amount; i++) {
            if (arr[i] != null && arr[i].nominal + sum <= amount) {
                sum += arr[i].nominal;
                indices[count++] = i;
            }
        }

        if (sum != amount) {
            System.out.println("Невозможно вывести " + amount + " " + currency + ".");
            return false;
        }

        // Выполнение снятия
        for (int i = 0; i < count; i++) {
            arr[indices[i]] = null;
        }
        System.out.println("Выдано: " + amount + " " + currency);
        return true;
    }

    public double getTotal(Currency target) {
        return sumArray(usdNotes, target) + sumArray(eurNotes, target) + sumArray(rubNotes, target);
    }

    private Banknote[] getArray(Currency c) {
        if (c == Currency.USD) return usdNotes;
        if (c == Currency.EUR) return eurNotes;
        return rubNotes;
    }

    private int findEmptyIndex(Banknote[] arr) {
        for (int i = 0; i < arr.length; i++) if (arr[i] == null) return i;
        return -1;
    }

    private double sumArray(Banknote[] arr, Currency to) {
        double sum = 0;
        for (Banknote b : arr) if (b != null) sum += b.convertTo(to);
        return sum;
    }
}