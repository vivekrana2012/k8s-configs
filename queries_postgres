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
    CONSTRAINT fk_merchant
      FOREIGN KEY(m_id) 
	  REFERENCES merchant(id),
    CONSTRAINT fk_payment_method
      FOREIGN KEY(pay_id) 
	  REFERENCES payment_method(id)
);