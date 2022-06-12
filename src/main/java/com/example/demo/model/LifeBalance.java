package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LifeBalance {

    private boolean isLifeBalanced;
    private long allSatisfiedCount;
    private long othersSatisfiedCount;
    private long selfSatisfiedCount;
    private double satisfactionRatio;

    public static LifeBalance of(
            boolean isLifeBalanced,
            long allSatisfiedCount,
            long othersSatisfiedCount,
            long selfSatisfiedCount,
            double satisfactionRatio
    ) {
        return new LifeBalance(
                isLifeBalanced,
                allSatisfiedCount,
                othersSatisfiedCount,
                selfSatisfiedCount,
                satisfactionRatio
        );
    }
}
