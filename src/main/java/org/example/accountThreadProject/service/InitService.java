package org.example.accountThreadProject.service;

import org.apache.log4j.Logger;
import org.example.accountThreadProject.model.Account;
import org.example.accountThreadProject.model.Transaction;

import java.util.*;

public class InitService {
    private static final Logger log = Logger.getLogger(InitService.class);
    private int accountVal;
    private int threadVal;


    public InitService() {
        try (Scanner sc = new Scanner(System.in);) {
            System.out.println("Введите желаемое количество счетов(целое число):");
            accountVal = sc.nextInt();
            System.out.println("Введите желаемое количество потоков(целое число):");
            threadVal = sc.nextInt();
        } catch (InputMismatchException e) {
            log.error("Не валидный парамметр");
        }
    }


    public Set<Long> initSetID(int numberAccount) {
        Set<Long> idGenerate = new HashSet<>();

        while (idGenerate.size() < numberAccount)
            idGenerate.add((long) (0 + Math.random() * 10000));


        return idGenerate;
    }

    public List<Account> initAccountList() {
        List<Account> accountList = new ArrayList<>();
        for (Long el : initSetID(accountVal))
            accountList.add(new Account(el));
        return accountList;
    }

    public List<Transaction> transactionListInit() {
        List<Transaction> list = new ArrayList<>();
        for (int i = 0; i < threadVal; i++)
            list.add(new Transaction());
        return list;
    }
}
