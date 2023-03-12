--Add initial data for account_balance
insert into account_balance (balance, account_balance_id) values (0, 1);

--Add initial data for crypto_balance
insert into crypto_balance (balance, cryptocurrency_code, crypto_balance_id) values (0, 'BTC', 1);
insert into crypto_balance (balance, cryptocurrency_code, crypto_balance_id) values (0, 'ETC', 2);
insert into crypto_balance (balance, cryptocurrency_code, crypto_balance_id) values (0, 'LTC', 3);
insert into crypto_balance (balance, cryptocurrency_code, crypto_balance_id) values (0, 'SOL', 4);
insert into crypto_balance (balance, cryptocurrency_code, crypto_balance_id) values (0, 'DOGE', 5);

--Add initial data for currency_balance
insert into currency_balance (balance, currency_code, currency_balance_id) values (0, 'EUR', 1);
insert into currency_balance (balance, currency_code, currency_balance_id) values (0, 'USD', 2);
insert into currency_balance (balance, currency_code, currency_balance_id) values (0, 'GBP', 3);
insert into currency_balance (balance, currency_code, currency_balance_id) values (0, 'CHF', 4);
insert into currency_balance (balance, currency_code, currency_balance_id) values (0, 'CNY', 5);

--Add initial data for crypto_rates
insert into crypto_rates (cryptocurrency_code, last_rate, rate_change, crypto_rate_id) values ('BTC', 0, 0, 1);
insert into crypto_rates (cryptocurrency_code, last_rate, rate_change, crypto_rate_id) values ('ETC', 0, 0, 2);
insert into crypto_rates (cryptocurrency_code, last_rate, rate_change, crypto_rate_id) values ('LTC', 0, 0, 3);
insert into crypto_rates (cryptocurrency_code, last_rate, rate_change, crypto_rate_id) values ('SOL', 0, 0, 4);
insert into crypto_rates (cryptocurrency_code, last_rate, rate_change, crypto_rate_id) values ('DOGE', 0, 0, 5);

--Add initial data for currency_rates
insert into currency_rates (currency_code, last_rate, rate_change, currency_rate_id) values ('EUR', 0, 0, 1);
insert into currency_rates (currency_code, last_rate, rate_change, currency_rate_id) values ('USD', 0, 0, 2);
insert into currency_rates (currency_code, last_rate, rate_change, currency_rate_id) values ('GBP', 0, 0, 3);
insert into currency_rates (currency_code, last_rate, rate_change, currency_rate_id) values ('CHF', 0, 0, 4);
insert into currency_rates (currency_code, last_rate, rate_change, currency_rate_id) values ('CNY', 0, 0, 5);
