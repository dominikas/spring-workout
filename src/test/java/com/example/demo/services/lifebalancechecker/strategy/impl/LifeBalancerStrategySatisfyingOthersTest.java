package com.example.demo.services.lifebalancechecker.strategy.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

class LifeBalancerStrategySatisfyingOthersTest {

    @Test
    void balanceLife_whenCannotSatisfyOthers_returnsFalse() {
        double normalizedThresholdToSatisfyOthers = 0.01;
        int meaningOfLife = 42;
        double desiredSelfCareRatio = 0.7;
        LifeBalancerStrategySatisfyingOthers lifeBalancer = new LifeBalancerStrategySatisfyingOthers(
                normalizedThresholdToSatisfyOthers,
                new Random(meaningOfLife)
        );
        Assertions.assertThat(lifeBalancer.balanceLife(desiredSelfCareRatio)).isFalse();
    }

    @Test
    void balanceLife_whenOtherSatisfied_returnsTrue() {
        double normalizedThresholdToSatisfyOthers = 0.8;
        int meaningOfLife = 42;
        double desiredSelfCareRatio = 0.7;
        LifeBalancerStrategySatisfyingOthers lifeBalancer = new LifeBalancerStrategySatisfyingOthers(
                normalizedThresholdToSatisfyOthers,
                new Random(meaningOfLife)
        );
        Assertions.assertThat(lifeBalancer.balanceLife(desiredSelfCareRatio)).isTrue();
    }
}