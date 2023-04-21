INSERT INTO customer
(user_id)
VALUES(1);

INSERT INTO orders
(order_date, "status", notes, customer_id)
VALUES(CURRENT_TIMESTAMP, 'In Progress', 'test note', 1);

INSERT INTO order_details
(price, quantity, product_id, order_id)
VALUES(10.00, 1, 1, 1);

INSERT INTO orders
(order_date, "status", notes, customer_id)
VALUES(CURRENT_TIMESTAMP, 'Ready to Collect', 'test notes', 1);

INSERT INTO order_details
(price, quantity, product_id, order_id)
VALUES(20.00, 2, 1, 2);