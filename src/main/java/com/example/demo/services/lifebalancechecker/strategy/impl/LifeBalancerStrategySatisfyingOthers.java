package com.example.demo.services.lifebalancechecker.strategy.impl;

import com.example.demo.services.lifebalancechecker.strategy.LifeBalancerStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@RequiredArgsConstructor
@Slf4j
public class LifeBalancerStrategySatisfyingOthers implements LifeBalancerStrategy {

    private final double normalizedThresholdToSatisfyOthers;
    private final Random random;
    private final AtomicLong othersSatisfiedCounter = new AtomicLong(0);
    private final AtomicLong selfSatisfiedCounter = new AtomicLong(0);

    @Override
    public boolean balanceLife(double desiredSelfCareRatio) {
        boolean selfSatisfied;
        try {
            satisfyOthers();
        } catch (StackOverflowError e) {
            log.warn("Mind overflow");
        } finally {
            selfSatisfied = isSelfSatisfiedMoreThanLess(desiredSelfCareRatio);
        }
        return selfSatisfied;
    }

    private synchronized boolean isSelfSatisfiedMoreThanLess(double desiredSelfCareRatio) {
        long othersSatisfiedCount = othersSatisfiedCounter.get();
        long selfSatisfiedCount = selfSatisfiedCounter.get();
        long allSatisfiedCount = othersSatisfiedCount + selfSatisfiedCount;
        double satisfactionRatio = (double) selfSatisfiedCounter.get() / allSatisfiedCount;
        log.debug("""
                        allSatisfiedCount: {}
                        othersSatisfiedCount: {}
                        selfSatisfiedCount: {}
                        satisfaction ratio: {}""",
                allSatisfiedCount, othersSatisfiedCount, selfSatisfiedCount, satisfactionRatio);
        return satisfactionRatio >= desiredSelfCareRatio;
    }

    private void satisfyOthers() {
        if (areOthersSatisfied()) {
            othersSatisfiedCounter.incrementAndGet();
            satisfyOthers();
        } else {
            satisfySelf();
        }
    }

    private boolean areOthersSatisfied() {
        return random.nextDouble() >= normalizedThresholdToSatisfyOthers;
    }

    private void satisfySelf() {
        selfSatisfiedCounter.incrementAndGet();
    }
}

