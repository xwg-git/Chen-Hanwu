package com.chw.trade.service;

import java.util.List;

import com.chw.trade.model.Trade;

public interface TradeService {

	public void addTrade(Trade trade);
	
	public void updateTrade(Trade trade);
	
	public void cancelTrade(Integer id);
	
	public Trade getTradeById(Integer transactionId);

	List<Trade> getTradeByTrade(Integer trade_id, String security_code);
	
	List<Trade> getTradeByTrade(String security_code);
	
	List<Trade> getAll();
}
