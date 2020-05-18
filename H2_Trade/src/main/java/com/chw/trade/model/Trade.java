package com.chw.trade.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="trade")
@Data
public class Trade {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer transaction_id;
	@Column
	private Integer trade_id;
	@Column
	private String version;
	@Column
	private String security_code;
	@Column
	private int quantity;
	@Column
	private String buy_or_sell;

}
