package com.empyrean.disruptor.handlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.empyrean.disruptor.Quote;
import com.lmax.disruptor.EventHandler;

// Receives every event but only processes those whose sequence falls in this
// worker's partition — sequence % workerCount == workerId — giving load balancing
// without WorkerPool, which was removed in Disruptor 4.x.
public class QuoteWorkerPublisher implements EventHandler<Quote> {

    private static final Logger logger = LogManager.getLogger(QuoteWorkerPublisher.class);

    private final int workerId;
    private final int workerCount;

    public QuoteWorkerPublisher(int workerId, int workerCount) {
        this.workerId = workerId;
        this.workerCount = workerCount;
    }

    @Override
    public void onEvent(Quote event, long sequence, boolean endOfBatch) throws Exception {
        if (sequence % workerCount == workerId) {
            logger.info("[worker-{}] instrument={}, value={}, lifetime={}",
                    workerId, event.getInstrument(), event.getValue(), event.getLifetime());
        }
    }
}
