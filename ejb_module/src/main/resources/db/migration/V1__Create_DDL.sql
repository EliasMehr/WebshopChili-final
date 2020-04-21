SET GLOBAL TIME_ZONE = '+01:00';

CREATE TABLE role
(
  role_id INT NOT NULL AUTO_INCREMENT,
  type VARCHAR(255) NOT NULL,
  discount_multiplier DECIMAL(3,2) NOT NULL,
  PRIMARY KEY (role_id)
);

CREATE TABLE product
(
  product_id INT NOT NULL AUTO_INCREMENT,
  scoville_strength VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  image_url VARCHAR(255) NOT NULL,
  price VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (product_id)
);

CREATE TABLE user
(
  user_id INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL,
  city VARCHAR(255) NOT NULL,
  phone VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  role_id INT NOT NULL,
  PRIMARY KEY (user_id),
  FOREIGN KEY (role_id) REFERENCES role(role_id),
  UNIQUE (email)
);

CREATE TABLE orders
(
  order_id INT NOT NULL AUTO_INCREMENT,
  date DATE NOT NULL,
  user_id INT NOT NULL,
  PRIMARY KEY (order_id),
  FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE order_item
(
  order_item_id INT NOT NULL AUTO_INCREMENT,
  quantity INT NOT NULL,
  price INT NOT NULL,
  order_id INT NOT NULL,
  product_id INT NOT NULL,
  PRIMARY KEY (order_item_id),
  FOREIGN KEY (order_id) REFERENCES orders(order_id),
  FOREIGN KEY (product_id) REFERENCES product(product_id)
);

INSERT INTO role (type, discount_multiplier) VALUES ('REGULAR_USER', 1);
INSERT INTO role (type, discount_multiplier) VALUES ('PREMIUM_USER', 0.9);
INSERT INTO role (type, discount_multiplier) VALUES ('ADMIN_USER', 0.7);