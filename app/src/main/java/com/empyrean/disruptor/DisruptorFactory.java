package com.empyrean.disruptor;

import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

/**
 * Factory for creating Disruptor instances with a common configuration.
 */
public class DisruptorFactory {

    private final int bufferSize;

    public DisruptorFactory(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public Disruptor<Quote> create(ProducerType producerType) {
        return new Disruptor<>(
                Quote::new,
                bufferSize,
                DaemonThreadFactory.INSTANCE,
                producerType,
                new YieldingWaitStrategy());
    }
}
