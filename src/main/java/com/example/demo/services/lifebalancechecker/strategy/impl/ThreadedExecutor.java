package com.example.demo.services.lifebalancechecker.strategy.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

@RequiredArgsConstructor
@Slf4j
public class ThreadedExecutor {

    private final int maxThreadsForBalanceStrategy;

    private final ExecutorService executorService;

    public ThreadedExecutor(int maxThreadsForBalanceStrategy) {
        this.maxThreadsForBalanceStrategy = maxThreadsForBalanceStrategy;
        executorService = createForkJoinPool();
    }

    void executeTask(Runnable task) throws ExecutionException, InterruptedException {
        executorService.submit(task).get();
    }

    private ExecutorService createForkJoinPool() {
        final ForkJoinPool.ForkJoinWorkerThreadFactory factory = pool -> {
            final ForkJoinWorkerThread worker = ForkJoinPool.defaultForkJoinWorkerThreadFactory.newThread(pool);
            worker.setName("WorkerThread-" + worker.getPoolIndex());
            return worker;
        };
        return new ForkJoinPool(maxThreadsForBalanceStrategy, factory, (t, e) -> log.error("Uncaught exception", e), true);
    }
}
