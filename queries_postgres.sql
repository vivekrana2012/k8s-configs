CREATE TABLE merchant(
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

INSERT INTO merchant (name) VALUES ('google');
INSERT INTO merchant (name) VALUES ('paytm');
INSERT INTO merchant (name) VALUES ('amazon');
INSERT INTO merchant (name) VALUES ('e-bay');

CREATE TABLE payment_method(
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

INSERT INTO payment_method (name) VALUES ('UPI');
INSERT INTO payment_method (name) VALUES ('NET Banking');
INSERT INTO payment_method (name) VALUES ('Debit Card');
INSERT INTO payment_method (name) VALUES ('Credit card');

CREATE TABLE transaction(
    id SERIAL PRIMARY KEY,
    status VARCHAR(1) NOT NULL,
    amount real NOT NULL,
    m_id int,
    pay_id int,
    timestamp timestamp,
    CONSTRAINT fk_merchant
      FOREIGN KEY(m_id) 
	  REFERENCES merchant(id),
    CONSTRAINT fk_payment_method
      FOREIGN KEY(pay_id) 
	  REFERENCES payment_method(id)
);

ALTER TABLE transaction
ADD COLUMN timestamp timestamp;

\timing on

// Query with timestamp bucket -- 3828.365 ms
select count(*) as total_count, sum(amount) as total_amount, m.name as merchant, p.name as pay_meth 
from transaction t 
inner join 
    merchant m ON t.m_id = m.id 
inner join 
    payment_method p ON t.pay_id = p.id 
where t.timestamp between '2022-02-22 15:49:36.043858' AND '2022-02-22 15:59:59.999999'
group by m.name, p.name;

// Query with timestamp bucket but no joins -- 2091.227 ms
select count(*) as total_count, sum(amount) as total_amount, m_id as merchant, pay_id as pay_meth 
from transaction t 
where t.timestamp between '2022-02-22 15:49:36.043858' AND '2022-02-22 15:59:59.999999'
group by m_id, pay_id;

// Query with inline view -- 1942.802 ms
WITH transactions as (select count(*) as total_count, sum(amount) as total_amount, m_id, pay_id
from transaction t 
where t.timestamp between '2022-02-22 15:49:36.043858' AND '2022-02-22 15:59:59.999999'
group by m_id, pay_id)
select t.total_count, t.total_amount, m.name as merchant, p.name as payment_method from transactions t
inner join 
    merchant m ON t.m_id = m.id 
inner join 
    payment_method p ON t.pay_id = p.id ;

// BRIN on timestamp
CREATE INDEX transaction_tm_brin ON transaction USING brin(timestamp);

// Index Size check
SELECT pg_size_pretty(pg_relation_size('transaction_tm_brin'));