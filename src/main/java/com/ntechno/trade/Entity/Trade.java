package com.ntechno.trade.Entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ntechno.trade.util.JsonDateDeserializer;
import com.ntechno.trade.util.JsonDateSerializer;

import java.util.Date;
 
/**
 * Created by nanagarajan on 8/16/2014.
 */
@JsonAutoDetect
public class Trade {
    private int id;
    private String symbol;
    private String tradeAction;
    private int quantity;
    private double price;
    private double marketValue;

    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date transactionDate;


    private transient double profitLoss;

    private transient double cumProfitLoss;

    @JsonIgnore
    private int outstandingQty;

    public Trade() {

    }

    public Trade(String symbol, String tradeAction, int quantity, double price, double marketValue, Date transactionDate) {
        this.symbol = symbol;
        this.tradeAction = tradeAction;
        this.quantity = quantity;
        this.price = price;
        this.marketValue = marketValue;
        this.transactionDate = transactionDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTradeAction() {
        return tradeAction;
    }

    public void setTradeAction(String tradeAction) {
        this.tradeAction = tradeAction;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
    }

    public double getProfitLoss() {
        return profitLoss;
    }

    public void setProfitLoss(double profitLoss) {
        this.profitLoss = profitLoss;
    }

    public double getCumProfitLoss() {
        return cumProfitLoss;
    }

    public void setCumProfitLoss(double cumProfitLoss) {
        this.cumProfitLoss = cumProfitLoss;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getOutstandingQty() {
        return outstandingQty;
    }

    public void setOutstandingQty(int outstandingQty) {
        this.outstandingQty = outstandingQty;
    }


}
