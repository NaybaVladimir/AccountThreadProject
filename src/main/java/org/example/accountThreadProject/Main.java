package org.example.accountThreadProject;

import org.example.accountThreadProject.model.Account;
import org.example.accountThreadProject.model.Transaction;
import org.example.accountThreadProject.service.InitService;

import java.util.List;

public class Main {
    public static int counter = 1;
    private static InitService initService = new InitService();
    public static List<Account> accountList = initService.initAccountList();
    public static List<Transaction> listTransaction = initService.transactionListInit();
    public static boolean isActive=false;

    public static void main(String[] args) {
       int sum=0;
       for (Account el:accountList)
           sum+=el.getMoney();

        System.out.println("Общее количество денег в банке: "+sum);

        for (Transaction el : listTransaction) {
            el.start();
            try {
                el.join();
            } catch (InterruptedException e) {
            }
        }

       while(counter<=30)
           isActive=true;

       sum=0;

       for (Account el:accountList)
            sum+=el.getMoney();

        System.out.println("Общее количество денег в банке: "+sum);


    }


}