package com.ntechno.trade.model;

import com.ntechno.trade.Entity.Trade;

import java.util.List;

/**
 * Created by nanagarajan on 8/16/2014.
 */
public class TradeForm {

    private Trade trade;
    private List<Trade> tradeList;

    public Trade getTrade() {
        return trade;
    }

    public void setTrade(Trade trade) {
        this.trade = trade;
    }

    public List<Trade> getTradeList() {
        return tradeList;
    }

    public void setTradeList(List<Trade> tradeList) {
        this.tradeList = tradeList;

    }
}
