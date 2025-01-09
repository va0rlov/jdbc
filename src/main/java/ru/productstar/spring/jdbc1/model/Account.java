package ru.productstar.spring.jdbc1.model;

import lombok.Getter;

//@Getter
public record Account(long id, long amount) {

    public Account {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }
}