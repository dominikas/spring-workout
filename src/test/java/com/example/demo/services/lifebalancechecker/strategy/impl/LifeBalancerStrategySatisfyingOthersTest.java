package com.example.demo.services.lifebalancechecker.strategy.impl;

import com.example.demo.model.LifeBalance;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

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
        LifeBalance expected = LifeBalance.of(false, 102316, 101316, 1000, 0.009773642441064935);
        LifeBalance result = lifeBalancer.balanceLife(desiredSelfCareRatio);
        assertThat(result).isEqualTo(expected);
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
        LifeBalance expected = LifeBalance.of(true, 247, 47, 200, 0.8097165991902834);
        LifeBalance result = lifeBalancer.balanceLife(desiredSelfCareRatio);
        assertThat(result).isEqualTo(expected);
    }
}
