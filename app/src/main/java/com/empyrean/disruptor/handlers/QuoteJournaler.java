package com.empyrean.disruptor.handlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.empyrean.disruptor.Quote;
import com.lmax.disruptor.EventHandler;

public class QuoteJournaler implements EventHandler<Quote> {

    Logger logger = LogManager.getLogger(QuoteJournaler.class);

    @Override
    public void onEvent(Quote event, long sequence, boolean endOfBatch) throws Exception {
        logger.info("Log: {}", event);
    }

}
