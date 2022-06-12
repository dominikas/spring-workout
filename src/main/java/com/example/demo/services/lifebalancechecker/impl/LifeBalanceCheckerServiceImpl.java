package com.example.demo.services.lifebalancechecker.impl;

import com.example.demo.rest.LifeBalanceDto;
import com.example.demo.services.lifebalancechecker.LifeBalanceCheckerService;
import com.example.demo.services.lifebalancechecker.strategy.LifeBalancerStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LifeBalanceCheckerServiceImpl implements LifeBalanceCheckerService {

    private final LifeBalancerStrategy lifeBalancerStrategy;

    @Override
    public LifeBalanceDto balanceLife(double desiredSelfCareRatio) {
        return LifeBalanceDto.of(lifeBalancerStrategy.balanceLife(desiredSelfCareRatio));
    }
}
