CREATE TABLE company (
id varchar(20) PRIMARY KEY,
name varchar(50),
totalshares number(10),
availableshares number(10),
currentprice number(10)
);

CREATE TABLE users (id varchar(20) PRIMARY KEY,
name varchar(50),
password varchar(50),
location varchar(50),
phone number(10),
email varchar(100),
is_admin int);

INSERT INTO users (id, name, password, location, phone, email, is_admin)
VALUES ('admin','Administrator','password','Bangalore','9876543210','admin@admin.com','1');

CREATE TABLE transactions (id number(10),
seller_id varchar(11),
buyer_id varchar(11),
shares_purchased number(11),
total_amount number(11));

CREATE TABLE userstocks (
id number(10),
company_id number(20),
user_id number(11),
shares_acquired number(10)
);

COMMIT;