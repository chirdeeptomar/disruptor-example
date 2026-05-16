package com.empyrean.disruptor;

import com.lmax.disruptor.EventFactory;

public class QuoteEventFactory implements EventFactory<Quote> {
    @Override
    public Quote newInstance() {
        return new Quote();
    }
}