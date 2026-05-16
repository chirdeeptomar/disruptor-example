package com.empyrean.disruptor.patterns.loadbalancer;

import java.util.Random;

import com.empyrean.disruptor.Instrument;
import com.empyrean.disruptor.Quote;
import com.empyrean.disruptor.handlers.QuoteWorkerPublisher;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

/**
 * A single producer publishes to the ring buffer, and multiple consumers
 * process the events in a round-robin fashion.
 */
public class LoadBalancer {

    private static final int WORKER_COUNT = 3;

    public void run(Disruptor<Quote> disruptor) throws Exception {
        QuoteWorkerPublisher[] workers = new QuoteWorkerPublisher[WORKER_COUNT];
        for (int i = 0; i < WORKER_COUNT; i++) {
            workers[i] = new QuoteWorkerPublisher(i, WORKER_COUNT);
        }

        disruptor.handleEventsWith(workers);
        disruptor.start();

        publish(disruptor.getRingBuffer());
    }

    private void publish(RingBuffer<Quote> ringBuffer) {
        Random random = new Random();
        for (;;) {
            ringBuffer.publishEvent(
                    (event, sequence, buffer) -> event.set(Instrument.random(), random.nextDouble(1, 10000)));
        }
    }
}
