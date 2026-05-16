package com.empyrean.disruptor;

import org.apache.log4j.Logger;

import com.lmax.disruptor.EventHandler;

public class QuoteEventHandler implements EventHandler<Quote> {

    Logger logger = Logger.getLogger(QuoteEventHandler.class.getName());

    @Override
    public void onEvent(Quote event, long sequence, boolean endOfBatch) throws Exception {
        logger.info("Instrument: " + event.getInstrument() + ", Price: " + event.getValue());
    }

}