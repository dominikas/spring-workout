package com.example.demo.services.lifebalancechecker.strategy;

import com.example.demo.services.lifebalancechecker.strategy.impl.LifeBalancerStrategySatisfyingOthers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class LifeBalancerStrategyConfig {

    @Value("${normalizedThresholdToSatisfyOthers}")
    double normalizedThresholdToSatisfyOthers;

    @Bean
    LifeBalancerStrategySatisfyingOthers lifeBalancerStrategy() {
        return new LifeBalancerStrategySatisfyingOthers(
                normalizedThresholdToSatisfyOthers,
                new Random());
    }
}
