package org.example.accountThreadProject.model;

import java.util.UUID;

public class Account {

    private Long id;
    private int money;

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
