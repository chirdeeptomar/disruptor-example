package com.empyrean.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Factory for creating Quote events for the Disruptor.
 */
public class QuoteEventFactory implements EventFactory<Quote> {
    @Override
    public Quote newInstance() {
        return new Quote();
    }
}