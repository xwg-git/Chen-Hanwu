package com.chw.trade.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.chw.trade.model.Trade;
import com.chw.trade.service.TradeService;

@Controller
public class TradeController {

	@Autowired
	private TradeService tradeService;
	
	@GetMapping("/list")
	public String list(Model model){
		Collection<Trade> list = tradeService.getAll();
		model.addAttribute("trades",list);
		
		return "list";
	}
	
	@GetMapping("/add")
    public String toAddPage(Model model){
        
        return "add";
    }
	
	@PostMapping("/addData")
	public String saveData(Trade trade){
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
		
		return "redirect:/list";
	}
	
	@GetMapping("/edit/{transaction_id}")
    public String toEditPage(@PathVariable("transaction_id") Integer id, Model model){
        Trade trade = tradeService.getTradeById(id);
        model.addAttribute("trade", trade);

        return "edit";
    }
	
	
	@PostMapping("/editData")
	public String saveEditData(Trade trade){
		tradeService.updateTrade(trade);
		
		return "redirect:/list";
	}
	
	@DeleteMapping("/delete/{security_code}")
    public String deleteEmployee(@PathVariable("security_code") String security_code){
		
        List<Trade> list = tradeService.getTradeByTrade(security_code);
        
        if(list.size() > 0){
			//取出version最大的数据
			Trade tradeCancel = list.get(0);
			//根据version最大的数据的主键id删除数据
			tradeService.cancelTrade(tradeCancel.getTransaction_id());
		}
        
        return "redirect:/list";
    }
}
