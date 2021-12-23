package org.example.accountThreadProject.service;

import org.apache.log4j.Logger;
import org.example.accountThreadProject.model.Account;
import org.example.accountThreadProject.model.Transaction;

import java.util.*;

public class InitService {
    /**
     * accountVal - количество счетов
     * threadVal - количество потоков
     */
    private static final Logger log = Logger.getLogger(InitService.class);
    private int accountVal;
    private int threadVal;

    /**
     * Конструктор принимает из консоли и иницилизирует первоначальные значения: accountVal и threadVal
     */
    public InitService() {
        try (Scanner sc = new Scanner(System.in);) {
            System.out.println("Введите желаемое количество счетов(целое число):");
            accountVal = sc.nextInt();
            System.out.println("Введите желаемое количество потоков(целое число):");
            threadVal = sc.nextInt();
        } catch (InputMismatchException e) {
            accountVal = 4;
            threadVal = 2;
            System.out.println("Введен невалидный парамметр. Установлены дефолтные значения - 2/2.");
            log.error("Введен невалидный парамметр. Установлены дефолтные значения.");
        }
    }

    /**
     * Создает коллекцию уникальных id. Создает список сет и помещает в него рандомные числа до тех пор
     * пока длина Set не станет равна количеству необходимых счетов
     *
     * @param numberAccount - количество аккаунтов
     * @return - отдает список уникальных значений
     */
    public Set<Long> initSetID() {
        Set<Long> idGenerate = new HashSet<>();
        while (idGenerate.size() < accountVal)
            idGenerate.add((long) (0 + Math.random() * 10000));
        return idGenerate;
    }

    /**
     * Создает список с необходимым количсетвом счетов
     * Значения id берутся из переданого из метода initSetID() списка
     * @return Отдает лист счетов
     */
    public List<Account> initAccountList() {
        List<Account> accountList = new ArrayList<>();
        for (Long el : initSetID())
            accountList.add(new Account(el));
        return accountList;
    }

    /**
     * Создает и инициализирует список объектов-потоков, которые производят транзакции, назаначает имя с порядковым номером
     * @return отдает результат работы
     */
    public List<Transaction> transactionListInit() {
        List<Transaction> list = new ArrayList<>();
        for (int i = 0; i < threadVal; i++)
            list.add(new Transaction("Thread #" + (i + 1)));
        return list;
    }
}
