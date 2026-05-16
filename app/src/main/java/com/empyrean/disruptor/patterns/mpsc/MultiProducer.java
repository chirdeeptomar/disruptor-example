package com.empyrean.disruptor.patterns.mpsc;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.empyrean.disruptor.Instrument;
import com.empyrean.disruptor.Quote;
import com.empyrean.disruptor.handlers.QuotePublisher;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

/**
 * Multiple producers publish to the same ring buffer, but only a single
 * consumer processes the events.
 */
public class MultiProducer {

    private static final int PRODUCER_COUNT = 3;

    public void run(Disruptor<Quote> disruptor) throws Exception {
        disruptor.handleEventsWith(new QuotePublisher());
        disruptor.start();

        RingBuffer<Quote> ringBuffer = disruptor.getRingBuffer();

        ExecutorService producers = Executors.newFixedThreadPool(PRODUCER_COUNT);
        for (int i = 0; i < PRODUCER_COUNT; i++) {
            final String producerId = "P" + i;
            producers.submit(() -> publish(ringBuffer, producerId));
        }

        producers.awaitTermination(Long.MAX_VALUE, java.util.concurrent.TimeUnit.NANOSECONDS);
    }

    private void publish(RingBuffer<Quote> ringBuffer, String producerId) {
        Random random = new Random();
        for (;;) {
            ringBuffer.publishEvent(
                    (event, sequence, buffer) -> event.set(Instrument.random(), random.nextDouble(1, 10000)));
        }
    }
}
