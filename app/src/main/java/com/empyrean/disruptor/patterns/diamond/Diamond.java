package com.empyrean.disruptor.patterns.diamond;

import java.util.Random;

import com.empyrean.disruptor.Instrument;
import com.empyrean.disruptor.Quote;
import com.empyrean.disruptor.handlers.QuoteLifetimeEnricher;
import com.empyrean.disruptor.handlers.QuotePublisher;
import com.empyrean.disruptor.handlers.QuoteValidityEnricher;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

/* Diamond Pattern Implementation */
public class Diamond {

    public void run(Disruptor<Quote> disruptor) throws Exception {
        // Fan-out: both enrichers process each event concurrently.
        // Fan-in: QuotePublisher only fires after both enrichers have finished.
        disruptor.handleEventsWith(new QuoteLifetimeEnricher(), new QuoteValidityEnricher())
                .then(new QuotePublisher());
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
