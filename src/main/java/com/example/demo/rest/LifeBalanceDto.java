package com.example.demo.rest;

import com.example.demo.model.LifeBalance;

public record LifeBalanceDto(
        Boolean isLifeBalanced,
        Long allSatisfiedCount,
        Double satisfactionRatio
) {
    public static LifeBalanceDto of(LifeBalance lifeBalance) {
        return new LifeBalanceDto(
                lifeBalance.isLifeBalanced(),
                lifeBalance.getAllSatisfiedCount(),
                lifeBalance.getSatisfactionRatio()
        );
    }
}
