package com.webcerebrium.binance.api;

import com.webcerebrium.binance.datatype.BinanceEventTicker;
import com.webcerebrium.binance.datatype.BinanceSymbol;
import com.webcerebrium.binance.websocket.BinanceWebSocketAdapterTicker;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.websocket.api.Session;
import org.junit.Before;
import org.junit.Test;

@Slf4j
public class TickerStreamTest {

    private BinanceApi binanceApi = null;
    private BinanceSymbol symbol = null;

    @Before
    public void setUp() throws Exception, BinanceApiException {
        BinanceConfig config = new BinanceConfig();
        String apiKey = config.getVariable("BINANCE_API_KEY");
        String secretKey = config.getVariable("BINANCE_SECRET_KEY");

        binanceApi = new BinanceApi(apiKey, secretKey);
        symbol = BinanceSymbol.valueOf("SNGLSBTC");
    }

    @Test
    public void testTickerStreamWatcher() throws Exception, BinanceApiException {
        Session session = binanceApi.websocketTicker(symbol, new BinanceWebSocketAdapterTicker() {
            @Override
            public void onMessage(BinanceEventTicker message) {
                log.info(message.toString());
            }
        });
        Thread.sleep(3000);
        session.close();
    }

}
