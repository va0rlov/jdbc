package ru.productstar.spring.jdbc1.manager;

import lombok.Getter;

@Getter
public class Account {

    private final long id;
    private long amount;

    public Account(long id, long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.id = id;
        this.amount = amount;
    }

    public void setAmount(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }
}