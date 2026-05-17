package com.empyrean.disruptor.handlers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.empyrean.disruptor.Quote;
import com.lmax.disruptor.EventHandler;

/* QuoteBatchHandler for Batching Pattern */
public class QuoteBatchHandler implements EventHandler<Quote> {

    private static final Logger logger = LogManager.getLogger(QuoteBatchHandler.class);

    private final List<String> batch = new ArrayList<>();

    @Override
    public void onEvent(Quote event, long sequence, boolean endOfBatch) throws Exception {
        batch.add(event.getInstrument() + "@" + event.getValue());

        if (endOfBatch) {
            flush();
        }
    }

    private void flush() {
        logger.info("Flushing batch of {} quotes: {}", batch.size(), batch);
        batch.clear();
    }
}
