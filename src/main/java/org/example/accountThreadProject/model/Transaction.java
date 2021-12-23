package org.example.accountThreadProject.model;

import org.apache.log4j.Logger;
import org.example.accountThreadProject.Main;


public class Transaction extends Thread {
    private static final Logger log = Logger.getLogger(Transaction.class);

    /**
     * Через конструктор супер класса присваем имя потоку
     *
     * @param name имя потока
     */
    public Transaction(String name) {
        super(name);
    }

    /**
     * Точка запуска потока.
     * sum = Рандомная сумма транзакции от 1 до 10т.
     * accountFrom - рандомный аккаунт списания
     * accountTo - рандомный аккаунт пополнения
     * Транзакция не производится со одного счета на тот же счет(проверка через if в цикле)
     * Вызов метода, который производит транзакцию
     * Поток засыпает на рандомное количество мС. от 1000мС до 2000мС
     */
    @Override
    public void run() {
        while (Main.isActive) {
            int sum = (int) (Math.random() * ((10000 - 1) + 1)) + 1;
            int accountFrom = (int) (Math.random() * (Main.accountList.size()));
            int accountTo = 0;

            while (true) {
                accountTo = (int) (Math.random() * (Main.accountList.size()));
                if (accountFrom != accountTo) {
                    break;
                }
            }
            transaction(Main.accountList.get(accountFrom), Main.accountList.get(accountTo), sum);

            try {
                Thread.sleep((int) (Math.random() * ((2000 - 1000) + 1)) + 1000);
            } catch (InterruptedException e) {
                log.error("Сбой в работе потока " + getName());
            }
        }
    }

    /**
     * Производит транзакцию списания и зачисления в случае, если транзакция не приведет к отрицательному балансу
     * Выводит информациюю в консоль
     * Вызывает методы списания и пополнения, которые реализованы в экземпляре счета
     * Вызывает метод главного класса, который учитывает количество транзакций  - на основании этого занчения останавливает все потоки
     *
     * @param from счет списания
     * @param to   счет пополнения
     * @param sum  сумма транзакции
     */
    private void transaction(Account from, Account to, int sum) {

        if (from.getMoney() >= sum) {
            System.out.println(" Имя потока: " + getName() + " Сумма транзакции:" + sum + " Между счетами: " + from.getId() + " : " + to.getId());
            from.writeOffOperation(sum);
            to.replenishmentOperation(sum);
            Main.counter();


        }

    }
}
