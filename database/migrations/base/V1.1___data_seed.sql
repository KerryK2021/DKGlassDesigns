INSERT INTO system_users
(first_name, last_name, email, phone, "address", username, "password", role_id)
VALUES('Kerry', 'Kennedy', 'kerry.kennedy@unosquare.com', '07596415092', null, 'kerryk0517', 'password123', 1);

INSERT INTO admins
(user_id)
VALUES(1);

INSERT INTO  supplier
(company_name, website, phone, email, notes)
VALUES('Glasses Direct', 'www.glasses.com', 05164784301, 'glasses@hotmail.com', 'test note');

INSERT INTO category
("name", "description")
values('Red Wine Glass', 'Tall Stem Glass');

INSERT INTO products
("name", "description", price, stock_count, "status", notes, supplier_id, category_id)
VALUES('wine glass', 'long stem glass', 10.00, 18, 'in stock', 'test note', 1, 1);

INSERT INTO products_supplier
(product_id, supplier_id)
VALUES(1,1);