package com.webcerebrium.binance.datatype;
/* ============================================================
 * java-binance-api
 * https://github.com/popescusilviun/java-binance-api
 * ============================================================
 * Copyright 2020-, Silviu Popescu
 * Released under the MIT License
 * ============================================================ */

import com.google.gson.JsonObject;
import com.webcerebrium.binance.api.BinanceApiException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.BigInteger;
/*
    {
      "e": "24hrTicker",  // Event type
      X"E": 123456789,     // Event time
      X"s": "BNBBTC",      // Symbol
      X"p": "0.0015",      // Price change
      "P": "250.00",      // Price change percent
      "w": "0.0018",      // Weighted average price
      "x": "0.0009",      // First trade(F)-1 price (first trade before the 24hr rolling window)
      X"c": "0.0025",      // Last price
      X"Q": "10",          // Last quantity
      X"b": "0.0024",      // Best bid price
      X"B": "10",          // Best bid quantity
      X"a": "0.0026",      // Best ask price
      X"A": "100",         // Best ask quantity
      X"o": "0.0010",      // Open price
      X"h": "0.0025",      // High price
      X"l": "0.0010",      // Low price
      X"v": "10000",       // Total traded base asset volume
      X"q": "18",          // Total traded quote asset volume
      X"O": 0,             // Statistics open time
      X"C": 86400000,      // Statistics close time
      X"F": 0,             // First trade ID
      X"L": 18150,         // Last trade Id
      X"n": 18151          // Total number of trades
    }
*/

@Data
@Slf4j
public class BinanceEventTicker {
    public Long eventTime;
    public BinanceSymbol symbol;

    public Long openTime;
    public Long closeTime;


    public Long firstTradeId;
    public Long lastTradeId;

    public BigDecimal openPrice;
    public BigDecimal highPrice;
    public BigDecimal lowPrice;
    public BigDecimal volume;
    public BigDecimal lastPrice;
    public BigDecimal bestBid;
    public BigDecimal bestAsk;
    public BigDecimal priceChange;
    public Double priceChangePerc;
    public BigDecimal lastQty;
    public BigDecimal bestBidQty;
    public BigDecimal bestAskQty;


    public Long numberOfTrades;
    public BigDecimal quoteVolume;


    public BinanceEventTicker(JsonObject event) throws BinanceApiException {
        eventTime = event.get("E").getAsLong();
        symbol = BinanceSymbol.valueOf(event.get("s").getAsString());

        openTime = event.get("O").getAsLong();
        closeTime  = event.get("C").getAsLong();

        firstTradeId  = event.get("F").getAsLong();
        lastTradeId  = event.get("L").getAsLong();

        openPrice = event.get("o").getAsBigDecimal();
        highPrice = event.get("h").getAsBigDecimal();
        lowPrice = event.get("l").getAsBigDecimal();
        lastPrice = event.get("c").getAsBigDecimal();
        volume = event.get("v").getAsBigDecimal();

        numberOfTrades  = event.get("n").getAsLong();

        quoteVolume = event.get("q").getAsBigDecimal();
        bestBidQty = event.get("B").getAsBigDecimal();
        bestAskQty = event.get("A").getAsBigDecimal();
        lastQty = event.get("Q").getAsBigDecimal();
        bestBid = event.get("b").getAsBigDecimal();
        bestAsk = event.get("a").getAsBigDecimal();
        priceChange = event.get("p").getAsBigDecimal();
        priceChangePerc = event.get("P").getAsDouble();
    }
}
