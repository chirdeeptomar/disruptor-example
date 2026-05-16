package com.empyrean.disruptor;

/**
 * A quote with an instrument and a value.
 */
public class Quote {
    private String instrument;
    private float value;

    public void set(String instrument, float value) {
        this.instrument = instrument;
        this.value = value;
    }

    public String getInstrument() {
        return instrument;
    }

    public float getValue() {
        return value;
    }

    public String toString() {
        return "Quote{" +
                "instrument='" + instrument + '\'' +
                ", value=" + value +
                '}';
    }
}
