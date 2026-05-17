package com.empyrean.disruptor.patterns.batching;

import java.util.Random;

import com.empyrean.disruptor.Instrument;
import com.empyrean.disruptor.Quote;
import com.empyrean.disruptor.handlers.QuoteBatchHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

/* Batching Pattern Implementation */
public class Batching {

    public void run(Disruptor<Quote> disruptor) throws Exception {
        disruptor.handleEventsWith(new QuoteBatchHandler());
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
