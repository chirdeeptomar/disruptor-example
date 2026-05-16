package com.empyrean.disruptor;

import org.apache.log4j.Logger;

import com.lmax.disruptor.EventHandler;

public class LoggingEventHandler implements EventHandler<Quote> {

    Logger logger = Logger.getLogger(LoggingEventHandler.class.getName());

    @Override
    public void onEvent(Quote event, long sequence, boolean endOfBatch) throws Exception {
        logger.info("LoggingEventHandler: " + event);
    }

}
