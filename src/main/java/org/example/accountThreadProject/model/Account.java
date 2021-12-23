package org.example.accountThreadProject.model;

import org.apache.log4j.Logger;

public class Account {
    private static final Logger log = Logger.getLogger(Transaction.class);
    private Long id;
    private int money;

    /**
     * Операция списания. Принимает сумму транзакции и производит списание
     *
     * @param sum -сумма транзакции
     */
    public synchronized void writeOffOperation(int sum) {
        int beforeTransaction = getMoney();
        money = money - sum;
        log.info(id + " Сумма списания " + sum + " до списания-после списания " + beforeTransaction + ":" + getMoney());
    }

    /**
     * Операция поплнения. Принимает сумму траназкции и производит пополнени
     *
     * @param sum - сумма транзакции
     */
    public synchronized void replenishmentOperation(int sum) {
        int beforeTransaction = getMoney();
        money = money + sum;
        log.info(id + " Сумма пополнения " + sum + " до пополнения-после пополнения " + beforeTransaction + ":" + getMoney());
    }

    /**
     * Конструктор класса. Принимает уникальный id назанчает дефолтное значение суммы счета
     *
     * @param id - уникальный id
     */
    public Account(Long id) {
        this.id = id;
        this.money = 10000;
    }

    public Long getId() {
        return id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Acсount{" +
                "id=" + id +
                ", money=" + money +
                '}';
    }
}
