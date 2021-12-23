package org.example.accountThreadProject;

import org.example.accountThreadProject.model.Account;
import org.example.accountThreadProject.model.Transaction;
import org.example.accountThreadProject.service.InitService;

import java.util.List;

public class Main {
    /**
     * counter - счетчик транзакций
     * initService - экземпляр класса -сервиса, инициализирует все необходимые для работы значения
     * accountList - список счетов
     * listTransaction - список объектов, производящих транзакции(потоки)
     * isActive - флаг, который останавливает потоки
     */
    public static int counter = 0;
    private static InitService initService = new InitService();
    public static List<Account> accountList = initService.initAccountList();
    public static List<Transaction> listTransaction = initService.transactionListInit();
    public static boolean isActive = true;

    /**
     * Точка входа в программу - делегирует старт потоков
     * Выводит статистическую информацию
     *
     * @param args
     */
    public static void main(String[] args) {
        int sumBank = accountList.stream().mapToInt((s) -> s.getMoney()).sum();
        startPotok();
        System.out.println("=============================================================");
        System.out.println("Сумма всех средств в банке вначале работы " + sumBank);
        System.out.println("=============================================================");
        System.out.println(accountList);
        System.out.println("=============================================================");
        sumBank = accountList.stream().mapToInt((s) -> s.getMoney()).sum();
        System.out.println("Сумма на момент окончания работы " + sumBank);


    }

    /**
     * Изменяет счетчик транзакций  - counter
     * на основании значения counter устанавливает флаг остановки потоков
     */
    public synchronized static void counter() {
        counter++;
        if (counter >= 30)
            isActive = false;

    }

    /**
     * Стартует потоки и и просит главный поток дождаться выполнения дочерних потоков
     */
    public static void startPotok() {
        for (Transaction el : listTransaction) {
            el.start();
        }
        for (Transaction el : listTransaction) {
            try {
                el.join();
            } catch (InterruptedException e) {
            }
        }
    }

}