package com.ntechno.trade.controller;

import com.ntechno.trade.Entity.Trade;
import com.ntechno.trade.exceptions.TradeException;
import com.ntechno.trade.model.TradeForm;
import com.ntechno.trade.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.InvalidParameterException;
import java.util.List;

/**
 * Created by nanagarajan on 8/16/2014.
 */
@Controller
@RequestMapping(value = "/trade")
@SessionAttributes(types = TradeForm.class)
public class TradeAppController {


    @Autowired
    TradeService tradeService;

    @RequestMapping("/home")
    public String home(HttpServletRequest hreq, Model model) {
        return "index";
    }


    @RequestMapping("/tradeEntry")
    public String saveTradeEntity(
            HttpServletRequest hreq,
            Model model,
            @ModelAttribute(value = "tradeForm") TradeForm form, BindingResult result,
            @RequestParam(value = "form_action", required = false) String action) {

        if (action != null
                && (action.equalsIgnoreCase("save"))) {
            // call save trade entry service to save the data
            Trade tradedData = form.getTrade();
            tradeService.calculateMarketValue(tradedData);
            try {
                tradeService.saveTradeEntity(form.getTrade());
                model.addAttribute("message", "Trade entry added");
                form.setTrade(new Trade());
            } catch (TradeException e) {
                model.addAttribute("message", e.getMessage());
            }

        }

        return "tradeEntry";

    }

    @RequestMapping("/tradeBlotter")
    public String tradeBlotter(
            HttpServletRequest hreq,
            Model model,
            @ModelAttribute(value = "tradeForm") TradeForm form) {
        //call trade entry service to blot the trading data
        try {
            List<Trade> tradedCollection = tradeService.retreiveTradeResults();
            form.setTradeList(tradedCollection);
        } catch (TradeException e) {
            model.addAttribute("message", e.getMessage());
        }
        return "tradeBlotter";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/tradeBlotter/getProfitLoss")
    public
    @ResponseBody
    ResponseEntity<List<Trade>> getProfitLoss(@ModelAttribute(value = "tradeForm") TradeForm form) {
        List<Trade> tradeCollection = form.getTradeList();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            tradeService.calculateProfileLoss(tradeCollection);
        } catch (InvalidParameterException e) {
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        ResponseEntity<List<Trade>> wrapper = new ResponseEntity<List<Trade>>(tradeCollection, httpStatus);

        return wrapper;
    }

    @ModelAttribute(value = "tradeForm")
    public TradeForm getTradeForm(ModelMap model, HttpSession session) {
        TradeForm tradeForm = new TradeForm();
        tradeForm.setTrade(new Trade());
        return tradeForm;
    }
}
