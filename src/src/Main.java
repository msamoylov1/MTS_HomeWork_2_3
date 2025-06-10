import Banknote.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("--- Меню ---");
            System.out.println("1. Внести");
            System.out.println("2. Снять");
            System.out.println("3. Показать общий баланс в выбранной валюте");
            System.out.println("4. Выход");
            System.out.println("------------");
            System.out.print("Выберите вариант: ");
            String choice = scanner.next();

            switch (choice) {
                case "1": {
                    printFindCurrency();
                    String cur = scanner.next().toUpperCase();
                    switch (cur) {
                        case "USD", "EUR", "RUB": break;
                        default: throw new IllegalArgumentException("Ошибка! Введено неизвестное значение валюты: " + cur);
                    }

                    System.out.print("Какой наминал: ");
                    String nom = scanner.next();
                    Banknote note = null;

                    try {
                        int nominal = Integer.parseInt(nom.trim());

                        note = switch (cur) {
                            case "USD" -> new DollarBanknote(nominal);
                            case "EUR" -> new EuroBanknote(nominal);
                            case "RUB" -> new RubBanknote(nominal);
                            default -> note;
                        };

                        boolean check = atm.deposit(note);
                        if (check) System.out.println("Купюра успешно внесена!");
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Ошибка! Значение номинала должно быть целым числом: " + nom);
                    }
                    break;
                }
                case "2": {
                    printFindCurrency();
                    String cur = scanner.next().toUpperCase();
                    Currency currency = Currency.valueOf(cur);
                    System.out.print("Введите сумму для вывода: ");
                    int amount = scanner.nextInt();

                    atm.withdraw(amount, currency);
                    break;
                }
                case "3": {
                    printFindCurrency();
                    String cur = scanner.next().toUpperCase();
                    double total = switch (cur) {
                        case "USD" -> atm.getTotal(Currency.USD);
                        case "EUR" -> atm.getTotal(Currency.EUR);
                        case "RUB" -> atm.getTotal(Currency.RUB);
                        default -> throw new IllegalArgumentException("Ошибка! Введено неизвестное значение валюты: " + cur);
                    };
                    System.out.println("Общий баланс в " + cur + ": " + total);
                    break;
                }
                case "4": {
                    running = false;
                    System.out.println("Конец программы.");
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Ошибка! Введено неизвестное значение: " + choice);
                }
            }
        }
        scanner.close();
    }

    public static void printFindCurrency() {
        System.out.print("Выбери валюту (USD, EUR, RUB): ");
    }
}