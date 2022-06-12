package com.example.demo.rest;

public record LifeBalanceDto(Boolean result) {
    public static LifeBalanceDto of(boolean result) {
        return new LifeBalanceDto(result);
    }
}
