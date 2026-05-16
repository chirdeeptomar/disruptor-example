package com.empyrean.disruptor.patterns.spsc;

import java.nio.ByteBuffer;
import java.util.Random;

import com.empyrean.disruptor.Instrument;
import com.empyrean.disruptor.Quote;
import com.empyrean.disruptor.handlers.QuotePublisher;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

/**
 * A single producer publishes to the ring buffer, and a single consumer
 * processes the events.
 */
public class SingleProducer {

    public void run(Disruptor<Quote> disruptor) throws Exception {
        disruptor.handleEventsWith(new QuotePublisher());
        disruptor.start();

        publish(disruptor.getRingBuffer());
    }

    private void publish(RingBuffer<Quote> ringBuffer) {
        ByteBuffer bb = ByteBuffer.allocate(8);
        Random random = new Random();
        for (long l = 0; true; l++) {
            bb.putLong(0, l);
            ringBuffer.publishEvent(
                    (event, sequence, buffer) -> event.set(Instrument.random(), random.nextDouble(1, 10000)), bb);
        }
    }
}
