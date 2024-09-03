package com.engine.fakau.stock.exception;

public class StockInsuffisant extends RuntimeException {
    public StockInsuffisant(String message) {
        super(message);
    }
}
