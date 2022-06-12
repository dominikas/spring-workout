package com.example.demo.services.lifebalancechecker.impl;

import com.example.demo.services.lifebalancechecker.LifeBalanceCheckerService;
import com.example.demo.services.lifebalancechecker.strategy.LifeBalancerStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LifeBalanceCheckerServiceImpl implements LifeBalanceCheckerService {

    private final LifeBalancerStrategy lifeBalancerStrategy;

    @Override
    public boolean isLifeBalanced(double desiredSelfCareRatio) {
        return lifeBalancerStrategy.balanceLife(desiredSelfCareRatio);
    }
}
