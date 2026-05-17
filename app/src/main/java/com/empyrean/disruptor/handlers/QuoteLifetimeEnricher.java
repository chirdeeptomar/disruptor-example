package com.empyrean.disruptor.handlers;

import com.empyrean.disruptor.Quote;
import com.lmax.disruptor.EventHandler;

/**
 * QuoteLifetimeEnricher that enriches Quote events with lifetime information.
 */
public class QuoteLifetimeEnricher implements EventHandler<Quote> {

    @Override
    public void onEvent(Quote event, long sequence, boolean endOfBatch) throws Exception {
        if (event.getValue() > 1000) {
            event.setLifetime(200);
        } else {
            event.setLifetime(50);
        }
    }

}
