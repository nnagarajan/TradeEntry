package com.ntechno.trade.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.ntechno.trade.Entity.Trade;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by nanagarajan on 8/16/2014.
 */
@Component
public class TradeDeserializer {

    public List<Trade> deserializeTradeResults()  {
        List<Trade> tradeCollection = new LinkedList<Trade>();
        try {
            File file = new File("tradeResults.dat");
            BufferedReader bufferedReader = null;
            ObjectMapper mapper = new ObjectMapper();
            if (!file.exists()) {
                throw new RuntimeException();
            }
            bufferedReader=new BufferedReader(new FileReader(file));
            String strLine;
            while ((strLine = bufferedReader.readLine()) != null) {
                Trade trade=mapper.readValue(strLine, Trade.class);
                trade.setOutstandingQty(trade.getQuantity());
                tradeCollection.add(trade);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return tradeCollection;

    }
}
