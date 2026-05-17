package com.empyrean.disruptor.handlers;

import com.empyrean.disruptor.Quote;
import com.lmax.disruptor.EventHandler;

/**
 * QuoteValidityEnricher that enriches Quote events with validity information.
 */
public class QuoteValidityEnricher implements EventHandler<Quote> {

    @Override
    public void onEvent(Quote event, long sequence, boolean endOfBatch) throws Exception {
        if (event.getCreationTime() + event.getLifetime() > System.currentTimeMillis()) {
            event.setValid(true);
        } else {
            event.setValid(false);
        }
    }

}
