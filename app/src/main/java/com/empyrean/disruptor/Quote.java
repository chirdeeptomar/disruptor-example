package com.empyrean.disruptor;

/**
 * A quote with an instrument and a value.
 */
public class Quote {
    private Instrument instrument;
    private double value;
    private int lifetime;
    private boolean valid;
    private long creationTime;

    public void set(Instrument instrument, double value) {
        this.instrument = instrument;
        this.value = value;
        this.valid = true;
        this.creationTime = System.currentTimeMillis();
    }

    public void set(Instrument instrument, double value, int lifetime) {
        this.instrument = instrument;
        this.value = value;
        this.lifetime = lifetime;
        this.valid = true;
        this.creationTime = System.currentTimeMillis();
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }

    public int getLifetime() {
        return lifetime;
    }

    public void setValid(boolean isValid) {
        this.valid = isValid;
    }

    public boolean isValid() {
        return valid;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public double getValue() {
        return value;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public String toString() {
        return "Quote{" +
                "instrument=" + instrument + ',' +
                ", value=" + value + ',' +
                ", lifetime=" + lifetime +
                '}';
    }
}
