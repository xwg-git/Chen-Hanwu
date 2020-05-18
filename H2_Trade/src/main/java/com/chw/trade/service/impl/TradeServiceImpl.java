package com.chw.trade.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chw.trade.mapper.TradeMapper;
import com.chw.trade.model.Trade;
import com.chw.trade.service.TradeService;

@Service
public class TradeServiceImpl implements TradeService {
	
	@Autowired
	private TradeMapper tradeMapper;

	@Override
	public void addTrade(Trade trade) {
		tradeMapper.insertTrade(trade);
	}

	@Override
	public void cancelTrade(Integer id) {
		tradeMapper.deleteTradeById(id);
	}

	@Override
	public Trade getTradeById(Integer transactionId) {
		return tradeMapper.getTradeByTransactionId(transactionId);
	}

	@Override
	public List<Trade> getTradeByTrade(Integer trade_id,String security_code) {
		return tradeMapper.getTradeByTradeAndSecurity(trade_id, security_code);
	}

	@Override
	public void updateTrade(Trade trade) {
		tradeMapper.updateTrade(trade);
	}

	@Override
	public List<Trade> getAll() {
		// TODO Auto-generated method stub
		return tradeMapper.getAllList();
	}

	@Override
	public List<Trade> getTradeByTrade(String security_code) {
		// TODO Auto-generated method stub
		return tradeMapper.getTradeBySecurity(security_code);
	}
	
	

}
