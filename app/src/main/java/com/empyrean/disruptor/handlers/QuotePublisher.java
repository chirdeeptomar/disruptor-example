package com.empyrean.disruptor.handlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.empyrean.disruptor.Quote;
import com.lmax.disruptor.EventHandler;

public class QuotePublisher implements EventHandler<Quote> {

    Logger logger = LogManager.getLogger(QuotePublisher.class);

    @Override
    public void onEvent(Quote event, long sequence, boolean endOfBatch) throws Exception {
        logger.info("Quote: { Instrument: {}, Price: {}, Lifetime: {} }", event.getInstrument(), event.getValue(),
                event.getLifetime());
    }

}