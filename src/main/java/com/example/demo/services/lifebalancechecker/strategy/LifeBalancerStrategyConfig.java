package com.example.demo.services.lifebalancechecker.strategy;

import com.example.demo.services.lifebalancechecker.strategy.impl.LifeBalancerStrategySatisfyingOthers;
import com.example.demo.services.lifebalancechecker.strategy.impl.ThreadedExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class LifeBalancerStrategyConfig {

    @Value("${normalizedThresholdToSatisfyOthers}")
    double normalizedThresholdToSatisfyOthers;

    @Value("${tasksCountToCompleteBalance}")
    int tasksCountToCompleteBalance;

    @Value("${maxThreadsForBalanceStrategy}")
    int maxThreadsForBalanceStrategy;

    @Bean
    LifeBalancerStrategySatisfyingOthers lifeBalancerStrategy() {
        return new LifeBalancerStrategySatisfyingOthers(
                normalizedThresholdToSatisfyOthers,
                tasksCountToCompleteBalance,
                new Random(),
                new ThreadedExecutor(maxThreadsForBalanceStrategy)
        );
    }
}
