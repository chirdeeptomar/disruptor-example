package com.empyrean.disruptor;

public enum Instrument {
    APPL, GOOG, MSFT, AMZN, META, TSLA, NFLX, NVDA, INTC, AMD;

    public static Instrument random() {
        return values()[(int) (Math.random() * values().length)];
    }
}
