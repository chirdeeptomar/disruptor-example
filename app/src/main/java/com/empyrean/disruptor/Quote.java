package com.empyrean.disruptor;

/**
 * A quote with an instrument and a value.
 */
public class Quote {
    private Instrument instrument;
    private double value;
    private int lifetime;

    public void set(Instrument instrument, double value) {
        this.instrument = instrument;
        this.value = value;
    }

    public void set(Instrument instrument, double value, int lifetime) {
        this.instrument = instrument;
        this.value = value;
        this.lifetime = lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }

    public int getLifetime() {
        return lifetime;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public double getValue() {
        return value;
    }

    public String toString() {
        return "Quote{" +
                "instrument=" + instrument + ',' +
                ", value=" + value + ',' +
                ", lifetime=" + lifetime +
                '}';
    }
}
