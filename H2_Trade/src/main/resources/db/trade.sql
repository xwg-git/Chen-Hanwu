drop table if exists trade;
create table trade (
transaction_id int IDENTITY primary key,
trade_id int,
version varchar(20),
security_code varchar(20),
quantity int,
buy_or_sell varchar(20)
);
insert into trade (trade_id,version,security_code,quantity,buy_or_sell) values(1,'1','REL',50,'Buy');
insert into trade (trade_id,version,security_code,quantity,buy_or_sell) values(2,'1','ITC',40,'Sell');
insert into trade (trade_id,version,security_code,quantity,buy_or_sell) values(3,'1','INF',70,'Buy');
insert into trade (trade_id,version,security_code,quantity,buy_or_sell) values(1,'2','REL',60,'Buy');
insert into trade (trade_id,version,security_code,quantity,buy_or_sell) values(2,'2','ITC',30,'Buy');
insert into trade (trade_id,version,security_code,quantity,buy_or_sell) values(4,'1','INF',20,'Sell');