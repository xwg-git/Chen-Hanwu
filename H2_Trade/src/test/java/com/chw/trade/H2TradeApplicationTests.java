package com.chw.trade;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.chw.trade.model.Trade;
import com.chw.trade.service.TradeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class H2TradeApplicationTests {

	@Autowired
	private TradeService tradeService;
	
	@Test
	public void contextLoads() {
		Trade trade = new Trade();
		trade.setTrade_id(1);
		trade.setVersion("1");
		trade.setSecurity_code("REL");
		trade.setQuantity(50);
		
		List<Trade> list = tradeService.getTradeByTrade(trade.getTrade_id(), trade.getSecurity_code());
		//插入操作
		if(list.size() > 0){ // 数据存在的情况下，插入一条版本号version+1的数据
			// 数据在查询的时候根据version降序排序过，取第一条既是version最大的一条
			Trade existTrade = list.get(0);
			//将存在的trade数据的版本号增加1插入到数据库中
			trade.setVersion(String.valueOf(Integer.valueOf(existTrade.getVersion()) + 1));
		} else { //数据不存在的情况下进行插入操作，新增的版本号从1开始
			tradeService.addTrade(trade);
		}
		
		//update操作
		Trade tradeUpdate = tradeService.getTradeById(1);
		if(null != tradeUpdate){
			tradeUpdate.setSecurity_code("ITC");
			tradeUpdate.setQuantity(60);
			
			tradeService.updateTrade(tradeUpdate);
		}
		
		//cancel操作
		//根据tradeid和securityCode查出数据
		List<Trade> list2 = tradeService.getTradeByTrade(1, "REL");
		if(list2.size() > 0){
			//取出version最大的数据
			Trade tradeCancel = list.get(0);
			//根据version最大的数据的主键id删除数据
			tradeService.cancelTrade(tradeCancel.getTransaction_id());
		}
	}

}
