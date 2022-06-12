package com.example.demo.services.lifebalancechecker;

import com.example.demo.rest.LifeBalanceDto;

public interface LifeBalanceCheckerService {
    LifeBalanceDto balanceLife(double desiredSelfCareRatio);
}
