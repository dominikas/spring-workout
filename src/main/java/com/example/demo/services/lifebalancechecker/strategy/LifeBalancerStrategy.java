package com.example.demo.services.lifebalancechecker.strategy;

import com.example.demo.model.LifeBalance;

public interface LifeBalancerStrategy {
    LifeBalance balanceLife(double desiredSelfCareRatio);
}
