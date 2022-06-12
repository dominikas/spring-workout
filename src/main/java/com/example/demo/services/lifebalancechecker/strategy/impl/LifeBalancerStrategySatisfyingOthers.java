package com.example.demo.services.lifebalancechecker.strategy.impl;

import com.example.demo.model.LifeBalance;
import com.example.demo.services.lifebalancechecker.strategy.LifeBalancerStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Slf4j
public class LifeBalancerStrategySatisfyingOthers implements LifeBalancerStrategy {

    private final double normalizedThresholdToSatisfyOthers;
    private final int tasksCountToCompleteBalance;
    private final Random random;

    private final ThreadedExecutor threadedExecutor;
    private final AtomicLong othersSatisfiedCounter = new AtomicLong(0);
    private final AtomicLong selfSatisfiedCounter = new AtomicLong(0);

    @Override
    public LifeBalance balanceLife(double desiredSelfCareRatio) {
        LifeBalance lifeBalance;
        try {
            satisfyAll();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lifeBalance = calculateLifeBalance(desiredSelfCareRatio);
            othersSatisfiedCounter.set(0);
            selfSatisfiedCounter.set(0);
        }
        return lifeBalance;
    }

    private void satisfyAll() throws ExecutionException, InterruptedException {
        threadedExecutor.executeTask(() -> IntStream.range(0, tasksCountToCompleteBalance).parallel().forEach(it -> satisfy()));
    }

    private void satisfy() {
        try {
            satisfyOthers();
        } catch (StackOverflowError e) {
            log.warn("Mind overflow");
        }
    }

    private synchronized void satisfyOthers() {
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

    private synchronized void satisfySelf() {
        selfSatisfiedCounter.incrementAndGet();
    }

    private synchronized LifeBalance calculateLifeBalance(double desiredSelfCareRatio) {
        long othersSatisfiedCount = othersSatisfiedCounter.get();
        long selfSatisfiedCount = selfSatisfiedCounter.get();
        long allSatisfiedCount = othersSatisfiedCount + selfSatisfiedCount;
        double satisfactionRatio = (double) selfSatisfiedCounter.get() / allSatisfiedCount;
        boolean isLifeBalanced = satisfactionRatio >= desiredSelfCareRatio;
        LifeBalance result = LifeBalance.of(isLifeBalanced, allSatisfiedCount, othersSatisfiedCount, selfSatisfiedCount, satisfactionRatio);
        log.debug("Life balance result: {}", result);
        return result;
    }
}
