package com.happiness.springboot.transferobject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GlobalQuote {

    @JsonProperty("Global Quote")
    private Quote quote;

    public GlobalQuote() {
    }

    public GlobalQuote(Quote quote) {
        this.quote = quote;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "GlobalQuote{" +
                "quote=" + quote +
                '}';
    }
}
