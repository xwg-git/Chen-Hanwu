package com.chw.trade;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.chw.trade.mapper")
@SpringBootApplication
public class H2TradeApplication {

	public static void main(String[] args) {
		SpringApplication.run(H2TradeApplication.class, args);
	}

}
