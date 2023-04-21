CREATE TABLE system_users (
    id serial PRIMARY KEY,
    first_name varchar NOT NULL,
    last_name varchar NOT NULL,
    email varchar NOT NULL,
    phone varchar NOT NULL,
    address varchar,
    username varchar NOT NULL,
    password varchar NOT NULL,
    role_id int NOT NULL

);

CREATE TABLE admins (
    id serial PRIMARY KEY,
    user_id int NOT NULL,
     CONSTRAINT fk_admins FOREIGN KEY (user_id)
        REFERENCES system_users(id)
);

CREATE TABLE customer (
    id serial PRIMARY KEY,
    user_id int NOT NULL,
     CONSTRAINT fk_customer FOREIGN KEY (user_id)
        REFERENCES system_users(id)
);

CREATE TABLE orders (
    id serial PRIMARY KEY,
    order_date date NOT NULL,
    status varchar,
    notes varchar,
    customer_id int NOT NULL,
     CONSTRAINT fk_order FOREIGN KEY (customer_id)
        REFERENCES customer(id)

);

CREATE TABLE category (
    id serial PRIMARY KEY,
    name varchar NOT NULL,
    description varchar NOT NULL

);

CREATE TABLE supplier (
    id serial PRIMARY KEY,
    company_name varchar NOT NULL,
    website varchar,
    phone varchar,
    email varchar,
    notes varchar

);

CREATE TABLE products (
    id serial PRIMARY KEY,
    name varchar NOT NULL,
    description varchar,
    price decimal NOT NULL,
    stock_count int NOT NULL,
    status varchar,
    notes varchar,
    supplier_id int NOT NULL,
    category_id int NOT NULL,
    CONSTRAINT fk_supplier FOREIGN KEY (supplier_id)
        REFERENCES supplier(id),
    CONSTRAINT fk_category FOREIGN KEY (category_id)
        REFERENCES category(id)

);

CREATE TABLE products_supplier (
    id serial PRIMARY KEY,
    product_id int NOT NULL,
    supplier_id int NOT NULL,
     CONSTRAINT fk_product FOREIGN KEY (product_id)
        REFERENCES products(id),
     CONSTRAINT fk_suppliers FOREIGN KEY (supplier_id)
        REFERENCES supplier(id)
);

CREATE TABLE order_details (
    id serial PRIMARY KEY,
    price decimal NOT NULL,
    quantity int NOT NULL,
    product_id int NOT NULL,
    order_id int NOT NULL,
     CONSTRAINT fk_products FOREIGN KEY (product_id)
        REFERENCES products(id),
     CONSTRAINT fk_orders FOREIGN KEY (order_id)
        REFERENCES orders(id)

);