package com.chw.trade.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.chw.trade.model.Trade;

public interface TradeMapper {
	
	@Select("select * from Trade")
	public List<Trade> getAllList();
	
	@Select("select * from Trade where transaction_id = #{id}")
    public Trade getTradeByTransactionId(Integer id);
	
	@Select("select * from Trade where trade_id = #{trade_id} and security_code = #{security_code} order by version desc")
    public List<Trade> getTradeByTradeAndSecurity(@Param("trade_id")Integer trade_id,@Param("security_code")String security_code);
	
	@Select("select * from Trade where security_code = #{security_code} order by version desc")
    public List<Trade> getTradeBySecurity(String security_code);

    @Delete("delete from trade where transaction_id = #{id}")
    public int deleteTradeById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "transaction_id")
    @Insert("insert into trade(transaction_id,trade_id,version,security_code,quantity,buy_or_sell) values (#{transaction_id},#{trade_id},#{version},#{security_code},#{quantity},#{buy_or_sell})")
    public int insertTrade(Trade trade);

    @Update("update trade set security_code = #{security_code},quantity = #{quantity},buy_or_sell = #{buy_or_sell} where transaction_id = #{transaction_id}")
    public int updateTrade(Trade trade);
}
