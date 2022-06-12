package com.example.demo.services.lifebalancechecker.strategy.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

class LifeBalancerStrategySatisfyingOthersTest {

    @Test
    void balanceLife_whenCannotSatisfyOthers_returnsFalse() {
        int meaningOfLife = 42;
        double normalizedThresholdToSatisfyOthers = 0.01;
        int tasksCountToCompleteBalance = 1000;
        double desiredSelfCareRatio = 0.7;
        LifeBalancerStrategySatisfyingOthers lifeBalancer = new LifeBalancerStrategySatisfyingOthers(
                normalizedThresholdToSatisfyOthers,
                tasksCountToCompleteBalance,
                new Random(meaningOfLife),
                new ThreadedExecutor(10)
        );
        Assertions.assertThat(lifeBalancer.balanceLife(desiredSelfCareRatio)).isFalse();
    }

    @Test
    void balanceLife_whenOtherSatisfied_returnsTrue() {
        int meaningOfLife = 42;
        double normalizedThresholdToSatisfyOthers = 0.8;
        int tasksCountToCompleteBalance = 200;
        double desiredSelfCareRatio = 0.7;
        LifeBalancerStrategySatisfyingOthers lifeBalancer = new LifeBalancerStrategySatisfyingOthers(
                normalizedThresholdToSatisfyOthers,
                tasksCountToCompleteBalance,
                new Random(meaningOfLife),
                new ThreadedExecutor(7)
        );
        Assertions.assertThat(lifeBalancer.balanceLife(desiredSelfCareRatio)).isTrue();
    }
}