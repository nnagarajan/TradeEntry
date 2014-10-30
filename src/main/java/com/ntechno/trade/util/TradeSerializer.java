package com.ntechno.trade.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntechno.trade.Entity.Trade;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Created by nanagarajan on 8/16/2014.
 */
@Component
public class TradeSerializer {


    public int serializeTradeEntity(Trade trade) {
        try {
            File file = new File("tradeResults.dat");
            BufferedWriter bufferedWriter = null;
            boolean newFile = false;
            if (!file.exists()) {

                file.createNewFile();
                newFile = true;

            }
            ObjectMapper mapper = new ObjectMapper();

            String json = mapper.writeValueAsString(trade);

            FileWriter fileWriter = new FileWriter(file, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            if (!newFile)
                bufferedWriter.newLine();
            bufferedWriter.append(json);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
