package org.example.accountThreadProject.model;

import org.apache.log4j.Logger;
import org.example.accountThreadProject.Main;


public class Transaction extends Thread {
    private static final Logger log = Logger.getLogger(Transaction.class);


    @Override
    public void run() {
        while (Main.isActive) {
            System.out.println(Main.isActive);
            int sum = (int) (Math.random() * ((10000 - 1) + 1)) + 1;
            int sleep = (int) (Math.random() * ((2000 - 1000) + 1)) + 1000;
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
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                log.error("Сбой в работе потока. Сон(мС): " + sleep);
            }
        }
    }

    private void transaction(Account from, Account to, int sum) {
        System.out.println("Сумма транзакции: " + sum);
        System.out.println("Отправитель ID: " + from.getId());
        System.out.println("Получатель ID: " + to.getId());
        if (from.getMoney() > sum) {
            from.setMoney(from.getMoney() - sum);
            to.setMoney(to.getMoney() + sum);
            Main.counter++;
        }

    }
}
