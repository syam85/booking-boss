DROP TABLE IF EXISTS PRODUCT;
 
CREATE TABLE PRODUCT (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  quantity INT NOT NULL,
  sale_amount float,
  event_id VARCHAR(250),
  event_timestamp TIMESTAMP
);