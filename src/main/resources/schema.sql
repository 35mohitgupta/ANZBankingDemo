--create schema test;
--use test;

DROP TABLE transaction if exists;
DROP TABLE account if exists;

CREATE TABLE account(
account_no BIGINT AUTO_INCREMENT PRIMARY KEY,
account_name VARCHAR(33),
account_type VARCHAR(10),
balance_date DATE,
currency VARCHAR(3) CHECK LENGTH(CURRENCY) = 3,
opening_available_bal DOUBLE,
CHECK (account_no >=1000000000 AND account_no <=99999999999)
);



CREATE TABLE transaction(
transaction_id INTEGER AUTO_INCREMENT PRIMARY KEY,
account_no BIGINT(33) REFERENCES account(account_no),
value_date DATE,
currency VARCHAR(3),
debit_amount DOUBLE,
credit_amount DOUBLE,
transaction_type VARCHAR(10),
tran_narrative VARCHAR(50),
CHECK (transaction_type in ('CREDIT','DEBIT')),
CHECK (account_no >=1000000000 AND account_no <=99999999999)
);


insert into account(account_no,account_name,account_type,balance_date,currency,opening_available_bal) values(1000000000,'mohit','SAVINGS','2020-10-10','IND',3333333);
--insert into account(account_no,account_name,account_type,balance_date,currency,opening_available_bal) values(1000000005,'mohit','SAVINGS','2020-10-10','IND',3333333);
--
--insert into transaction(account_no,value_date,currency,debit_amount,credit_amount,transaction_type,tran_narrative) values(1000000000,'2020-10-11','IND',null,55225.555,'CREDIT','credited 55225.555');