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

select count(*) as total_count, sum(amount) as total_amount, m.name as merchant, p.name as pay_meth 
from transaction t 
inner join 
    merchant m ON t.m_id = m.id 
inner join 
    payment_method p ON t.pay_id = p.id 
where t.pay_id = 1
group by m.name, p.name;