package com.ntechno.trade.repository;

import com.ntechno.trade.Entity.Trade;
import com.ntechno.trade.util.TradeDeserializer;
import com.ntechno.trade.util.TradeSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by nanagarajan on 8/16/2014.
 */
@Repository
public class TradeRepository {
    @Autowired
    TradeSerializer tradeSerializer;

    @Autowired
    TradeDeserializer tradeDeserializer;

    public int saveTradeEntity(Trade trade) {

        return tradeSerializer.serializeTradeEntity(trade);
    }

    public List<Trade> retrieveTradeResults() {
        List<Trade> tradeList =
                tradeDeserializer.deserializeTradeResults();
        return tradeList;
    }
}
