package com.ntechno.trade.service;

import com.ntechno.trade.Entity.Trade;
import com.ntechno.trade.exceptions.TradeException;
import com.ntechno.trade.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nanagarajan on 8/16/2014.
 */
@Service
public class TradeService {

    @Autowired
    TradeRepository tradeRepository;

    public void calculateMarketValue(Trade trade
    ) {
        int qty = trade.getQuantity();
        double price = trade.getPrice();
        int factor = ("BUY".equalsIgnoreCase(trade.getTradeAction())) ? -1 : 1;
        double marketValue = qty * price * factor;
        trade.setMarketValue(marketValue);
    }

    public void calculateProfileLoss(List<Trade> tradeCollections) {
        double cumProfitLoss = 0;
        for (Trade trade : tradeCollections) {
            if ("BUY".equalsIgnoreCase(trade.getTradeAction())) {
                trade.setProfitLoss(0);
            } else if ("SELL".equalsIgnoreCase(trade.getTradeAction())) {
                calculateProfitLossSell(tradeCollections, trade);
                cumProfitLoss += trade.getProfitLoss();
                trade.setCumProfitLoss(cumProfitLoss);
                if (trade.getOutstandingQty() > 0)
                    throw new TradeException("SELL Qty is more than whats bought for" + trade.getSymbol());
            }
        }

    }

    public void calculateProfitLossSell(List<Trade> tradeCollections, Trade tradedSell) {

        for (Trade tradedBuy : tradeCollections) {
            int diffQty = tradedBuy.getOutstandingQty() - tradedSell.getOutstandingQty();
            if (tradedBuy.getOutstandingQty() > 0 && diffQty == 0 && (tradedSell.getSymbol() != null && tradedSell.getSymbol().equalsIgnoreCase(tradedBuy.getSymbol()))
                    ) {
                double profitLoss = (tradedSell.getPrice() - tradedBuy.getPrice()) * tradedBuy.getOutstandingQty();
                tradedSell.setProfitLoss(profitLoss + tradedSell.getProfitLoss());
                tradedBuy.setOutstandingQty(0);
                tradedSell.setOutstandingQty(0);
                break;
            } else if (tradedBuy.getOutstandingQty() > 0 && diffQty < 0 && (tradedSell.getSymbol() != null && tradedSell.getSymbol().equalsIgnoreCase(tradedBuy.getSymbol()))) {
                double profitLoss = (tradedSell.getPrice() - tradedBuy.getPrice()) * tradedBuy.getOutstandingQty();
                int sellOutstandingQty = tradedSell.getOutstandingQty() - tradedBuy.getOutstandingQty();
                tradedSell.setProfitLoss(profitLoss + tradedSell.getProfitLoss());
                tradedBuy.setOutstandingQty(0);
                tradedSell.setOutstandingQty(sellOutstandingQty);
            } else if (tradedBuy.getOutstandingQty() > 0 && diffQty > 0 && (tradedSell.getSymbol() != null && tradedSell.getSymbol().equalsIgnoreCase(tradedBuy.getSymbol()))) {
                double profitLoss = (tradedSell.getPrice() - tradedBuy.getPrice()) * tradedSell.getOutstandingQty();
                int buyOutstandingQty = tradedBuy.getOutstandingQty() - tradedSell.getOutstandingQty();
                tradedSell.setProfitLoss(profitLoss + tradedSell.getProfitLoss());
                tradedBuy.setOutstandingQty(buyOutstandingQty);
                tradedSell.setOutstandingQty(0);
                break;
            }


        }
    }

    public int saveTradeEntity(Trade tradeEntity) {
        return tradeRepository.saveTradeEntity(tradeEntity);
    }

    public List<Trade> retreiveTradeResults() {
        return tradeRepository.retrieveTradeResults();
    }

}
