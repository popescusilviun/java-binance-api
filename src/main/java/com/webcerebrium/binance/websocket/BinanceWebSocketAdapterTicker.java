package com.webcerebrium.binance.websocket;

/* ============================================================
 * java-binance-api
 * https://github.com/popescusilviun/java-binance-api
 * ============================================================
 * Copyright 2020-, Silviu Popescu
 * Released under the MIT License
 * ============================================================ */

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.webcerebrium.binance.api.BinanceApiException;
import com.webcerebrium.binance.datatype.BinanceEventTicker;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;


@Slf4j
public abstract class BinanceWebSocketAdapterTicker extends WebSocketAdapter {
    @Override
    public void onWebSocketConnect(Session sess) {
        log.debug("onWebSocketConnect: {}", sess);
    }

    @Override
    public void onWebSocketError(Throwable cause) {
        if (cause == null) throw new AssertionError();
        log.error("onWebSocketError: {}", cause.getMessage());
    }

    @Override
    public void onWebSocketText(String message) {
        log.debug("onWebSocketText message={}", message);
        JsonObject operation = (new Gson()).fromJson(message, JsonObject.class);
        try {
            onMessage(new BinanceEventTicker(operation));
        } catch (BinanceApiException e) {
            log.error("Error in websocket message {}", e.getMessage());
        }
    }

    public abstract void onMessage(BinanceEventTicker event) throws BinanceApiException;

}
